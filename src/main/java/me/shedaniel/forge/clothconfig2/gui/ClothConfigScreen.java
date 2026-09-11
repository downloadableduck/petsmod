package me.shedaniel.forge.clothconfig2.gui;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.AtomicDouble;
import me.shedaniel.forge.clothconfig2.api.*;
import me.shedaniel.forge.clothconfig2.gui.entries.KeyCodeEntry;
import me.shedaniel.forge.clothconfig2.gui.entries.TextFieldListEntry;
import me.shedaniel.forge.clothconfig2.gui.widget.DynamicElementListWidget;
import me.shedaniel.forge.clothconfig2.impl.KeyInput;
import me.shedaniel.forge.math.Rectangle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;

import org.lwjgl.input.Mouse;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings({"deprecation", "rawtypes", "unchecked", "DuplicatedCode"})

public abstract class ClothConfigScreen extends GuiScreen {

    private static final ResourceLocation CONFIG_TEX = new ResourceLocation("cloth-config2", "textures/gui/cloth_config.png");
    private static final int ID_QUIT = 100;
    private static final int ID_SAVE = 101;
    private static final int ID_APPLY = 102;
    private final List<QueuedTooltip> queuedTooltips = Lists.newArrayList();
    private final GuiScreen parent;
    private final LinkedHashMap<String, List<AbstractConfigEntry>> tabbedEntries;
    private final List<Pair<String, Integer>> tabs;
    private final boolean confirmSave;
    private final String title;
    private final boolean displayErrors;
    private final List<ClothConfigTabButton> tabButtons;
    private final ResourceLocation defaultBackgroundLocation;
    private final Map<String, ResourceLocation> categoryBackgroundLocation;
    public int nextTabIndex;
    public int selectedTabIndex;
    public double tabsScrollVelocity = 0d;
    public double tabsScrollProgress;
    public ListWidget<AbstractConfigEntry<AbstractConfigEntry>> listWidget;
    private KeyCodeEntry focusedBinding;
    private boolean edited;
    private boolean requiresRestart;
    private GuiButton quitButton, saveButton, applyButton, buttonLeftTab, buttonRightTab;
    private Rectangle tabsBounds, tabsLeftBounds, tabsRightBounds;
    private double tabsMaximumScrolled = -1d;
    private boolean smoothScrollingTabs = true;
    private boolean smoothScrollingList;
    private boolean transparentBackground = false;
    private boolean editable = true;
    @Nullable
    private String defaultFallbackCategory = null;
    private boolean alwaysShowTabs = false;
    private ModifierKeyCode startedKeyCode = null;
    private final Minecraft minecraft = Minecraft.getInstance();

    @Deprecated
    public ClothConfigScreen(GuiScreen parent, String title, Map<String, List<Pair<String, Object>>> o, boolean confirmSave, boolean displayErrors, boolean smoothScrollingList, ResourceLocation defaultBackgroundLocation, Map<String, ResourceLocation> categoryBackgroundLocation) {
        super();
        this.parent = parent;
        this.title = title;
        this.tabbedEntries = Maps.newLinkedHashMap();
        this.smoothScrollingList = smoothScrollingList;
        this.defaultBackgroundLocation = defaultBackgroundLocation;
        o.forEach((tab, pairs) -> {
            List<AbstractConfigEntry> list = Lists.newArrayList();
            for (Pair<String, Object> pair : pairs) {
                if (pair.getRight() instanceof AbstractConfigListEntry) {
                    list.add((AbstractConfigListEntry) pair.getRight());
                } else {
                    throw new IllegalArgumentException("Unsupported Type (" + pair.getLeft() + "): " + pair.getRight().getClass().getSimpleName());
                }
            }
            list.forEach(entry -> entry.setScreen(this));
            tabbedEntries.put(tab, list);
        });
        FontRenderer font = Minecraft.getInstance().fontRenderer;
        this.tabs = tabbedEntries.keySet().stream().map(s -> new Pair<>(s, font.getStringWidth(I18n.format(s)) + 8)).collect(Collectors.toList());
        this.nextTabIndex = 0;
        this.selectedTabIndex = 0;
        for (int i = 0; i < tabs.size(); i++) {
            Pair<String, Integer> pair = tabs.get(i);
            if (pair.getLeft().equals(getFallbackCategory())) {
                this.nextTabIndex = i;
                this.selectedTabIndex = i;
                break;
            }
        }
        this.confirmSave = confirmSave;
        this.edited = false;
        this.requiresRestart = false;
        this.tabsScrollProgress = 0d;
        this.tabButtons = Lists.newArrayList();
        this.displayErrors = displayErrors;
        this.categoryBackgroundLocation = categoryBackgroundLocation;
    }

    public boolean isShowingTabs() {
        return isAlwaysShowTabs() || tabs.size() > 1;
    }

    public boolean isAlwaysShowTabs() {
        return alwaysShowTabs;
    }

    @Deprecated
    public void setAlwaysShowTabs(boolean alwaysShowTabs) {
        this.alwaysShowTabs = alwaysShowTabs;
    }

    public boolean isTransparentBackground() {
        return transparentBackground && Minecraft.getInstance().world != null;
    }

    @Deprecated
    public void setTransparentBackground(boolean transparentBackground) {
        this.transparentBackground = transparentBackground;
    }

    public String getFallbackCategory() {
        if (defaultFallbackCategory != null)
            return defaultFallbackCategory;
        return tabs.get(0).getLeft();
    }

    @Deprecated
    public void setFallbackCategory(@Nullable String defaultFallbackCategory) {
        this.defaultFallbackCategory = defaultFallbackCategory;
    }

    @Override
    public void tick() {
        super.tick();
        if (listWidget != null) {
            listWidget.tick();
            for (AbstractConfigEntry child : listWidget.children())
                child.updateScreen();
        }
        if (saveButton != null) {
            boolean hasErrors = false;
            if (displayErrors)
                for (List<AbstractConfigEntry> entries : Lists.newArrayList(tabbedEntries.values())) {
                    for (AbstractConfigEntry entry : entries)
                        if (entry.getConfigError().isPresent()) {
                            hasErrors = true;
                            break;
                        }
                    if (hasErrors)
                        break;
                }
            saveButton.enabled = edited && !hasErrors;
            saveButton.displayString = (displayErrors && hasErrors ? I18n.format("text.cloth-config.error_cannot_save") : I18n.format("text.cloth-config.save_and_done"));
        }
        if (applyButton != null)
            applyButton.enabled = saveButton.enabled;
        if (quitButton != null)
            quitButton.displayString = (edited ? I18n.format("text.cloth-config.cancel_discard") : I18n.format("gui.cancel"));
    }

    public ResourceLocation getBackgroundLocation() {
        if (categoryBackgroundLocation.containsKey(Lists.newArrayList(tabbedEntries.keySet()).get(selectedTabIndex)))
            return categoryBackgroundLocation.get(Lists.newArrayList(tabbedEntries.keySet()).get(selectedTabIndex));
        return defaultBackgroundLocation;
    }

    public boolean isSmoothScrollingList() {
        return smoothScrollingList;
    }

    @Deprecated
    public void setSmoothScrollingList(boolean smoothScrollingList) {
        this.smoothScrollingList = smoothScrollingList;
    }

    public boolean isSmoothScrollingTabs() {
        return smoothScrollingTabs;
    }

    @Deprecated
    public void setSmoothScrollingTabs(boolean smoothScrolling) {
        this.smoothScrollingTabs = smoothScrolling;
    }

    public boolean isEdited() {
        return edited;
    }

    @Deprecated
    public void setEdited(boolean edited) {
        this.edited = edited;
        if (quitButton != null)
            quitButton.displayString = (edited ? I18n.format("text.cloth-config.cancel_discard") : I18n.format("gui.cancel"));
        if (saveButton != null)
            saveButton.enabled = edited;
    }

    public void setEdited(boolean edited, boolean requiresRestart) {
        setEdited(edited);
        if (!this.requiresRestart && requiresRestart)
            this.requiresRestart = requiresRestart;
    }

    public void saveAll(boolean openOtherScreens) {
        for (List<AbstractConfigEntry> entries : Lists.newArrayList(tabbedEntries.values()))
            for (AbstractConfigEntry entry : entries)
                entry.save();
        save();
        setEdited(false);
        requiresRestart = false;
        if (openOtherScreens) {
            if (requiresRestart)
                Minecraft.getInstance().displayGuiScreen(new ClothRequiresRestartScreen(parent));
            else
                Minecraft.getInstance().displayGuiScreen(parent);
        }
    }

    @Override
    public void initGui() {
        super.initGui();
        this.tabButtons.clear();
        if (listWidget != null)
            tabbedEntries.put(tabs.get(selectedTabIndex).getLeft(), (List) listWidget.children());
        selectedTabIndex = nextTabIndex;
        listWidget = new ListWidget(minecraft, width, height, isShowingTabs() ? 70 : 30, height - 32, getBackgroundLocation());
        listWidget.setSmoothScrolling(this.smoothScrollingList);
        if (tabbedEntries.size() > selectedTabIndex)
            Lists.newArrayList(tabbedEntries.values()).get(selectedTabIndex).forEach(entry -> listWidget.children().add(entry));
        int buttonWidths = (width - 50 - 12) / 3;
        addButton(quitButton = new GuiButton(ID_QUIT, 25, height - 26, buttonWidths, 20, edited ? I18n.format("text.cloth-config.cancel_discard") : I18n.format("gui.cancel")));
        addButton(saveButton = new GuiButton(ID_SAVE, 25 + 6 + buttonWidths, height - 26, buttonWidths, 20, I18n.format("text.cloth-config.save_and_done")));
        addButton(applyButton = new GuiButton(ID_APPLY, 25 + (6 + buttonWidths) * 2, height - 26, buttonWidths, 20, I18n.format("text.cloth-config.apply")));
        saveButton.enabled = edited;
        if (isShowingTabs()) {
            tabsBounds = new Rectangle(0, 41, width, 24);
            tabsLeftBounds = new Rectangle(0, 41, 18, 24);
            tabsRightBounds = new Rectangle(width - 18, 41, 18, 24);
            buttonLeftTab = new GuiButton(new Random().nextInt(), 4, 44, 12, 18, "");
            int j = 0;
            for (Pair<String, Integer> tab : tabs) {
                tabButtons.add(new ClothConfigTabButton(this, j, -100, 43, tab.getRight(), 20, I18n.format(tab.getLeft())));
                j++;
            }
            buttonRightTab = new GuiButton(new Random().nextInt(), width - 16, 44, 12, 18, "");
        } else {
            tabsBounds = tabsLeftBounds = tabsRightBounds = new Rectangle();
        }
    }

    /*@Override
    public void handleMouseInput() {
        super.handleMouseInput();
        int scroll = Mouse.getEventDWheel();
        if (scroll != 0) {
            double mouseX = Mouse.getX() * (double) width / (double) minecraft.displayWidth;
            double mouseY = ((double) minecraft.displayHeight - Mouse.getY()) * (double) height / (double) minecraft.displayHeight;
            if (tabsBounds.contains(mouseX, mouseY) && !tabsLeftBounds.contains(mouseX, mouseY) && !tabsRightBounds.contains(mouseX, mouseY)) {
                if (scroll < 0)
                    tabsScrollVelocity += 16;
                if (scroll > 0)
                    tabsScrollVelocity -= 16;
            } else {
                if (listWidget != null)
                    listWidget.handleMouseInput();
            }
        }
    }*/

    public double getTabsMaximumScrolled() {
        if (tabsMaximumScrolled == -1d) {
            AtomicDouble d = new AtomicDouble();
            tabs.forEach(pair -> d.addAndGet(pair.getRight() + 2));
            tabsMaximumScrolled = d.get();
        }
        return tabsMaximumScrolled + 8;
    }

    public void resetTabsMaximumScrolled() {
        tabsMaximumScrolled = -1d;
        tabsScrollVelocity = 0f;
    }

    public void clampTabsScrolled() {
        int xx = 0;
        for (ClothConfigTabButton tabButton : tabButtons)
            xx += tabButton.getWidth() + 2;
        if (xx > width - 40)
            tabsScrollProgress = MathHelper.clamp(tabsScrollProgress, 0, getTabsMaximumScrolled() - width + 40);
        else
            tabsScrollProgress = 0d;
    }

    @Override
    public void render(int int_1, int int_2, float float_1) {
        if (isShowingTabs()) {
            if (smoothScrollingTabs) {
                double change = tabsScrollVelocity * 0.2f;
                if (change != 0) {
                    if (change > 0 && change < .2)
                        change = .2;
                    else if (change < 0 && change > -.2)
                        change = -.2;
                    tabsScrollProgress += change;
                    tabsScrollVelocity -= change;
                    if (change > 0 == tabsScrollVelocity < 0)
                        tabsScrollVelocity = 0f;
                    clampTabsScrolled();
                }
            } else {
                tabsScrollProgress += tabsScrollVelocity;
                tabsScrollVelocity = 0d;
                clampTabsScrolled();
            }
            int xx = 24 - (int) tabsScrollProgress;
            for (ClothConfigTabButton tabButton : tabButtons) {
                tabButton.x = xx;
                xx += tabButton.getWidth() + 2;
            }
            if (buttonLeftTab != null)
                buttonLeftTab.enabled = tabsScrollProgress > 0d;
            if (buttonRightTab != null)
                buttonRightTab.enabled = tabsScrollProgress < getTabsMaximumScrolled() - width + 40;
        }
        if (isTransparentBackground()) {
            drawGradientRect(0, 0, this.width, this.height, -1072689136, -804253680);
        } else {
            drawBackground(0);
        }
        listWidget.render(int_1, int_2, float_1);
        ScissorsHandler.INSTANCE.scissor(new Rectangle(listWidget.left, listWidget.top, listWidget.width, listWidget.bottom - listWidget.top));
        for (AbstractConfigEntry child : listWidget.children())
            child.lateRender(int_1, int_2, float_1);
        ScissorsHandler.INSTANCE.removeLastScissor();
        if (isShowingTabs()) {
            drawCenteredString(minecraft.fontRenderer, title, width / 2, 18, -1);
            Rectangle onlyInnerTabBounds = new Rectangle(tabsBounds.x + 20, tabsBounds.y, tabsBounds.width - 40, tabsBounds.height);
            ScissorsHandler.INSTANCE.scissor(onlyInnerTabBounds);
            if (isTransparentBackground())
                drawGradientRect(onlyInnerTabBounds.x, onlyInnerTabBounds.y, onlyInnerTabBounds.getMaxX(), onlyInnerTabBounds.getMaxY(), 0x68000000, 0x68000000);
            else
                overlayBackground(onlyInnerTabBounds, 32, 32, 32, 255, 255);
            for (ClothConfigTabButton widget : tabButtons)
                widget.render(int_1, int_2, float_1);
            drawTabsShades(0, isTransparentBackground() ? 120 : 255);
            ScissorsHandler.INSTANCE.removeLastScissor();
            buttonLeftTab.func_146112_a(minecraft, int_1, int_2);
            buttonRightTab.func_146112_a(minecraft, int_1, int_2);
        } else
            drawCenteredString(minecraft.fontRenderer, title, width / 2, 12, -1);

        if (displayErrors && isEditable()) {
            List<String> errors = Lists.newArrayList();
            for (List<AbstractConfigEntry> entries : Lists.newArrayList(tabbedEntries.values()))
                for (AbstractConfigEntry entry : entries)
                    if (entry.getConfigError().isPresent())
                        errors.add(((Optional<String>) entry.getConfigError()).get());
            if (errors.size() > 0) {
                minecraft.getTextureManager().bindTexture(CONFIG_TEX);
                GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                String text = "§c" + (errors.size() == 1 ? errors.get(0) : I18n.format("text.cloth-config.multi_error"));
                if (isTransparentBackground()) {
                    int stringWidth = minecraft.fontRenderer.getStringWidth(text);
                    drawGradientRect(8, 9, 20 + stringWidth, 14 + minecraft.fontRenderer.FONT_HEIGHT, 0x68000000, 0x68000000);
                }
                drawTexturedModalRect(10, 10, 0, 54, 3, 11);
                drawString(minecraft.fontRenderer, text, 18, 12, -1);
            }
        } else if (!isEditable()) {
            minecraft.getTextureManager().bindTexture(CONFIG_TEX);
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            String text = "§c" + I18n.format("text.cloth-config.not_editable");
            if (isTransparentBackground()) {
                int stringWidth = minecraft.fontRenderer.getStringWidth(text);
                drawGradientRect(8, 9, 20 + stringWidth, 14 + minecraft.fontRenderer.FONT_HEIGHT, 0x68000000, 0x68000000);
            }
            drawTexturedModalRect(10, 10, 0, 54, 3, 11);
            drawString(minecraft.fontRenderer, text, 18, 12, -1);
        }
        super.render(int_1, int_2, float_1);
        queuedTooltips.forEach(queuedTooltip -> drawHoveringText(queuedTooltip.getText(), queuedTooltip.getX(), queuedTooltip.getY()));
        queuedTooltips.clear();
    }

    public void queueTooltip(QueuedTooltip queuedTooltip) {
        queuedTooltips.add(queuedTooltip);
    }

    private void drawTabsShades(int lightColor, int darkColor) {
        GlStateManager.enableBlend();
        GlStateManager.blendFuncSeparate(770, 771, 0, 1);
        GlStateManager.disableAlphaTest();
        GlStateManager.shadeModel(7425);
        GlStateManager.disableTexture2D();
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer buffer = tessellator.getBuffer();
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        buffer.pos(tabsBounds.getMinX() + 20, tabsBounds.getMinY() + 4, 0.0D).tex(0, 1f).color(0, 0, 0, lightColor).endVertex();
        buffer.pos(tabsBounds.getMaxX() - 20, tabsBounds.getMinY() + 4, 0.0D).tex(1f, 1f).color(0, 0, 0, lightColor).endVertex();
        buffer.pos(tabsBounds.getMaxX() - 20, tabsBounds.getMinY(), 0.0D).tex(1f, 0).color(0, 0, 0, darkColor).endVertex();
        buffer.pos(tabsBounds.getMinX() + 20, tabsBounds.getMinY(), 0.0D).tex(0, 0).color(0, 0, 0, darkColor).endVertex();
        tessellator.draw();
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        buffer.pos(tabsBounds.getMinX() + 20, tabsBounds.getMaxY(), 0.0D).tex(0, 1f).color(0, 0, 0, darkColor).endVertex();
        buffer.pos(tabsBounds.getMaxX() - 20, tabsBounds.getMaxY(), 0.0D).tex(1f, 1f).color(0, 0, 0, darkColor).endVertex();
        buffer.pos(tabsBounds.getMaxX() - 20, tabsBounds.getMaxY() - 4, 0.0D).tex(1f, 0).color(0, 0, 0, lightColor).endVertex();
        buffer.pos(tabsBounds.getMinX() + 20, tabsBounds.getMaxY() - 4, 0.0D).tex(0, 0).color(0, 0, 0, lightColor).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.shadeModel(7424);
        GlStateManager.enableAlphaTest();
        GlStateManager.disableBlend();
    }

    @SuppressWarnings("deprecation")
    protected void overlayBackground(Rectangle rect, int red, int green, int blue, int startAlpha, int endAlpha) {
        if (isTransparentBackground())
            return;
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer buffer = tessellator.getBuffer();
        minecraft.getTextureManager().bindTexture(getBackgroundLocation());
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        float f = 32.0F;
        buffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        buffer.pos(rect.getMinX(), rect.getMaxY(), 0.0D).tex(rect.getMinX() / 32.0F, rect.getMaxY() / 32.0F).color(red, green, blue, endAlpha).endVertex();
        buffer.pos(rect.getMaxX(), rect.getMaxY(), 0.0D).tex(rect.getMaxX() / 32.0F, rect.getMaxY() / 32.0F).color(red, green, blue, endAlpha).endVertex();
        buffer.pos(rect.getMaxX(), rect.getMinY(), 0.0D).tex(rect.getMaxX() / 32.0F, rect.getMinY() / 32.0F).color(red, green, blue, startAlpha).endVertex();
        buffer.pos(rect.getMinX(), rect.getMinY(), 0.0D).tex(rect.getMinX() / 32.0F, rect.getMinY() / 32.0F).color(red, green, blue, startAlpha).endVertex();
        tessellator.draw();
    }

    public KeyCodeEntry getFocusedBinding() {
        return focusedBinding;
    }

    public void setFocusedBinding(KeyCodeEntry focusedBinding) {
        this.focusedBinding = focusedBinding;
        if (focusedBinding != null) {
            startedKeyCode = this.focusedBinding.getValue();
            startedKeyCode.setKeyCodeAndModifier(KeyInput.INVALID, Modifier.none());
        } else
            startedKeyCode = null;
    }

    /*@Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        if (this.focusedBinding != null && this.startedKeyCode != null && !this.startedKeyCode.isUnknown() && focusedBinding.isAllowMouse()) {
            focusedBinding.setValue(startedKeyCode);
            setFocusedBinding(null);
            return;
        }
        super.func_146286_b(mouseX, mouseY, state);
        if (listWidget != null)
            listWidget.mouseReleased(mouseX, mouseY, state);
    }*/

    @Override
    public void func_73864_a(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (this.focusedBinding != null && this.startedKeyCode != null && focusedBinding.isAllowMouse()) {
            if (startedKeyCode.isUnknown())
                startedKeyCode.setKeyCode(KeyInput.of(KeyInput.Type.MOUSE, mouseButton));
            else if (focusedBinding.isAllowModifiers()) {
                if (startedKeyCode.getType() == KeyInput.Type.KEYSYM) {
                    int code = startedKeyCode.getKeyCode().getKeyCode();
                    if (Minecraft.IS_RUNNING_ON_MAC ? (code == 343 || code == 347) : (code == 341 || code == 345)) {
                        Modifier modifier = startedKeyCode.getModifier();
                        startedKeyCode.setModifier(Modifier.of(modifier.hasAlt(), true, modifier.hasShift()));
                        startedKeyCode.setKeyCode(KeyInput.of(KeyInput.Type.MOUSE, mouseButton));
                        return;
                    } else if (code == 344 || code == 340) {
                        Modifier modifier = startedKeyCode.getModifier();
                        startedKeyCode.setModifier(Modifier.of(modifier.hasAlt(), modifier.hasControl(), true));
                        startedKeyCode.setKeyCode(KeyInput.of(KeyInput.Type.MOUSE, mouseButton));
                        return;
                    } else if (code == 342 || code == 346) {
                        Modifier modifier = startedKeyCode.getModifier();
                        startedKeyCode.setModifier(Modifier.of(true, modifier.hasControl(), modifier.hasShift()));
                        startedKeyCode.setKeyCode(KeyInput.of(KeyInput.Type.MOUSE, mouseButton));
                        return;
                    }
                }
            }
            return;
        } else {
            if (this.focusedBinding != null)
                return;
        }
        super.func_73864_a(mouseX, mouseY, mouseButton);
        if (isShowingTabs()) {
            for (ClothConfigTabButton tabButton : tabButtons) {
                if (tabButton.isMouseOver(mouseX, mouseY)) {
                    tabButton.onClick();
                    return;
                }
            }
            if (buttonLeftTab != null && buttonLeftTab.func_146116_c(minecraft, mouseX, mouseY)) {
                tabsScrollProgress = Integer.MIN_VALUE;
                tabsScrollVelocity = 0d;
                clampTabsScrolled();
                return;
            }
            if (buttonRightTab != null && buttonRightTab.func_146116_c(minecraft, mouseX, mouseY)) {
                tabsScrollProgress = Integer.MAX_VALUE;
                tabsScrollVelocity = 0d;
                clampTabsScrolled();
                return;
            }
        }
        if (listWidget != null)
            listWidget.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void func_73869_a(char typedChar, int keyCode) {
        if (listWidget != null) {
            for (AbstractConfigEntry entry : listWidget.children()) {
                if (entry instanceof TextFieldListEntry) {
                    if (((TextFieldListEntry<?>) entry).keyTyped(typedChar, keyCode)) {
                        return;
                    }
                }
            }
        }
        if (this.focusedBinding != null && (focusedBinding.isAllowKey() || keyCode == 1)) {
            if (keyCode != 1) {
                if (startedKeyCode.isUnknown())
                    startedKeyCode.setKeyCode(KeyInput.of(KeyInput.Type.KEYSYM, keyCode));
                else if (focusedBinding.isAllowModifiers()) {
                    if (startedKeyCode.getType() == KeyInput.Type.KEYSYM) {
                        int code = startedKeyCode.getKeyCode().getKeyCode();
                        if (Minecraft.IS_RUNNING_ON_MAC ? (code == 343 || code == 347) : (code == 341 || code == 345)) {
                            Modifier modifier = startedKeyCode.getModifier();
                            startedKeyCode.setModifier(Modifier.of(modifier.hasAlt(), true, modifier.hasShift()));
                            startedKeyCode.setKeyCode(KeyInput.of(KeyInput.Type.KEYSYM, keyCode));
                            return;
                        } else if (code == 344 || code == 340) {
                            Modifier modifier = startedKeyCode.getModifier();
                            startedKeyCode.setModifier(Modifier.of(modifier.hasAlt(), modifier.hasControl(), true));
                            startedKeyCode.setKeyCode(KeyInput.of(KeyInput.Type.KEYSYM, keyCode));
                            return;
                        } else if (code == 342 || code == 346) {
                            Modifier modifier = startedKeyCode.getModifier();
                            startedKeyCode.setModifier(Modifier.of(true, modifier.hasControl(), modifier.hasShift()));
                            startedKeyCode.setKeyCode(KeyInput.of(KeyInput.Type.KEYSYM, keyCode));
                            return;
                        }
                    }
                    if (Minecraft.IS_RUNNING_ON_MAC ? (keyCode == 343 || keyCode == 347) : (keyCode == 341 || keyCode == 345)) {
                        Modifier modifier = startedKeyCode.getModifier();
                        startedKeyCode.setModifier(Modifier.of(modifier.hasAlt(), true, modifier.hasShift()));
                        return;
                    } else if (keyCode == 344 || keyCode == 340) {
                        Modifier modifier = startedKeyCode.getModifier();
                        startedKeyCode.setModifier(Modifier.of(modifier.hasAlt(), modifier.hasControl(), true));
                        return;
                    } else if (keyCode == 342 || keyCode == 346) {
                        Modifier modifier = startedKeyCode.getModifier();
                        startedKeyCode.setModifier(Modifier.of(true, modifier.hasControl(), modifier.hasShift()));
                        return;
                    }
                }
            } else {
                focusedBinding.setValue(ModifierKeyCode.unknown());
                setFocusedBinding(null);
            }
            return;
        }
        if (this.focusedBinding != null && keyCode != 1)
            return;
        if (keyCode == 1 && this.allowCloseWithEscape()) {
            if (confirmSave && edited)
                minecraft.displayGuiScreen(new GuiYesNo(new QuitSaveConsumer(), new TextComponentTranslation("text.cloth-config.quit_config").toString(), new TextComponentTranslation("text.cloth-config.quit_config_sure").toString(), I18n.format("text.cloth-config.quit_discard"), I18n.format("gui.cancel"), new Random().nextInt()));
            else
                minecraft.displayGuiScreen(parent);
            return;
        }
        try {
            super.func_73869_a(typedChar, keyCode);
        } catch (IOException e) {}
    }

    public boolean allowCloseWithEscape() {
        return true;
    }

    @Override
    protected void func_146284_a(GuiButton button) {
        if (button == quitButton) {
            if (confirmSave && edited)
                minecraft.displayGuiScreen(new GuiYesNo(new QuitSaveConsumer(), new TextComponentTranslation("text.cloth-config.quit_config").toString(), new TextComponentTranslation("text.cloth-config.quit_config_sure").toString(), I18n.format("text.cloth-config.quit_discard"), I18n.format("gui.cancel"), 1));
            else {
                button.func_146118_a(0, 0);
                minecraft.displayGuiScreen(parent);
            }
        } else if (button == saveButton || button == applyButton) {
            saveAll(true);
        }
    }

    public void save() {
    }

    public boolean isEditable() {
        return editable;
    }

    @Deprecated
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    private class QuitSaveConsumer implements GuiYesNoCallback {
        private void handle(boolean t) {
            if (!t)
                minecraft.displayGuiScreen(ClothConfigScreen.this);
            else
                minecraft.displayGuiScreen(parent);
        }

        @Override
        public void func_73878_a(boolean p_confirmResult_1_, int p_confirmResult_2_) {
            this.handle(p_confirmResult_1_);
        }
    }

    public class ListWidget<R extends DynamicElementListWidget.ElementEntry<R>> extends DynamicElementListWidget<R> {
        public ListWidget(Minecraft client, int width, int height, int top, int bottom, ResourceLocation backgroundLocation) {
            super(client, width, height, top, bottom, backgroundLocation);
            visible = false;
        }

        @Override
        public int getItemWidth() {
            return width - 80;
        }

        public ClothConfigScreen getScreen() {
            return ClothConfigScreen.this;
        }

        @Override
        protected int getScrollbarPosition() {
            return width - 36;
        }

        protected final void clearStuff() {
            this.clearItems();
        }

        @Override
        protected void renderItem(R item, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
            if (item instanceof AbstractConfigEntry)
                ((AbstractConfigEntry) item).updateSelected(getFocused() == item);
            super.renderItem(item, index, y, x, entryWidth, entryHeight, mouseX, mouseY, isSelected, delta);
        }

        @Override
        public boolean mouseClicked(double double_1, double double_2, int int_1) {
            this.updateScrollingState(double_1, double_2, int_1);
            if (!this.isMouseOver(double_1, double_2)) {
                return false;
            } else {
                for (R entry : children()) {
                    if (entry.mouseClicked((int) double_1, (int) double_2, int_1)) {
                        this.setFocused(entry);
                        this.setDragging(true);
                        return true;
                    }
                }
                if (int_1 == 0) {
                    this.clickedHeader((int) (double_1 - (double) (this.left + this.width / 2 - this.getItemWidth() / 2)), (int) (double_2 - (double) this.top) + (int) this.getScroll() - 4);
                    return true;
                }

                return this.scrolling;
            }
        }

        @Override
        protected void renderBackBackground(VertexBuffer buffer, Tessellator tessellator) {
            if (!isTransparentBackground())
                super.renderBackBackground(buffer, tessellator);
            else {
                drawGradientRect(left, top, right, bottom, 0x68000000, 0x68000000);
            }
        }

        @Override
        protected void renderHoleBackground(int int_1, int int_2, int int_3, int int_4) {
            if (!isTransparentBackground())
                super.renderHoleBackground(int_1, int_2, int_3, int_4);
        }
    }
}
