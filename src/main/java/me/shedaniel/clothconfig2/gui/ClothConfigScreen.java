package me.shedaniel.clothconfig2.gui;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.AtomicDouble;
import me.shedaniel.clothconfig2.api.*;
import me.shedaniel.clothconfig2.gui.entries.KeyCodeEntry;
import me.shedaniel.clothconfig2.gui.entries.TextFieldListEntry;
import me.shedaniel.clothconfig2.gui.widget.DynamicElementListWidget;
import me.shedaniel.clothconfig2.impl.KeyInput;
import me.shedaniel.math.Rectangle;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings({"deprecation", "rawtypes", "unchecked", "DuplicatedCode"})
public abstract class ClothConfigScreen extends Screen {

    private static final Identifier CONFIG_TEX = new Identifier("cloth-config2", "textures/gui/cloth_config.png");
    private static final int ID_QUIT = 100;
    private static final int ID_SAVE = 101;
    private static final int ID_APPLY = 102;
    private final List<QueuedTooltip> queuedTooltips = Lists.newArrayList();
    private final Screen parent;
    private final LinkedHashMap<String, List<AbstractConfigEntry>> tabbedEntries;
    private final List<Pair<String, Integer>> tabs;
    private final boolean confirmSave;
    private final String title;
    private final boolean displayErrors;
    private final List<ClothConfigTabButton> tabButtons;
    private final Identifier defaultBackgroundLocation;
    private final Map<String, Identifier> categoryBackgroundLocation;
    public int nextTabIndex;
    public int selectedTabIndex;
    public double tabsScrollVelocity = 0d;
    public double tabsScrollProgress;
    public ListWidget<AbstractConfigEntry<AbstractConfigEntry>> listWidget;
    private KeyCodeEntry focusedBinding;
    private boolean edited;
    private boolean requiresRestart;
    private ButtonWidget quitButton, saveButton, applyButton, buttonLeftTab, buttonRightTab;
    private Rectangle tabsBounds, tabsLeftBounds, tabsRightBounds;
    private double tabsMaximumScrolled = -1d;
    private boolean smoothScrollingTabs = true;
    private boolean smoothScrollingList;
    private boolean transparentBackground = false;
    private boolean editable = true;
    private String defaultFallbackCategory = null;
    private boolean alwaysShowTabs = false;
    private ModifierKeyCode startedKeyCode = null;
    private final MinecraftClient minecraft = MinecraftClient.getInstance();

    @Deprecated
    public ClothConfigScreen(Screen parent, String title, Map<String, List<Pair<String, Object>>> o, boolean confirmSave, boolean displayErrors, boolean smoothScrollingList, Identifier defaultBackgroundLocation, Map<String, Identifier> categoryBackgroundLocation) {
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
        TextRenderer font = MinecraftClient.getInstance().textRenderer;
        this.tabs = tabbedEntries.keySet().stream().map(s -> new Pair<>(s, font.getStringWidth(I18n.translate(s)) + 8)).collect(Collectors.toList());
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
        return transparentBackground && MinecraftClient.getInstance().world != null;
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
    public void setFallbackCategory(String defaultFallbackCategory) {
        this.defaultFallbackCategory = defaultFallbackCategory;
    }

    @Override
    public void method_21936() {
        if (listWidget != null) {
            listWidget.tick();
            for (AbstractConfigEntry child : listWidget.children())
                child.tick();
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
            saveButton.field_22511 = edited && !hasErrors;
            saveButton.field_22510 = (displayErrors && hasErrors ? I18n.translate("text.cloth-config.error_cannot_save") : I18n.translate("text.cloth-config.save_and_done"));
        }
        if (applyButton != null)
            applyButton.field_22511 = saveButton.field_22511;
        if (quitButton != null)
            quitButton.field_22510 = (edited ? I18n.translate("text.cloth-config.cancel_discard") : I18n.translate("gui.cancel"));
    }

    public Identifier getBackgroundLocation() {
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
            quitButton.field_22510 = (edited ? I18n.translate("text.cloth-config.cancel_discard") : I18n.translate("gui.cancel"));
        if (saveButton != null)
            saveButton.field_22511 = edited;
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
                MinecraftClient.getInstance().setScreen(new ClothRequiresRestartScreen(parent));
            else
                MinecraftClient.getInstance().setScreen(parent);
        }
    }

    public void method_21947() {
        super.method_21947();
        this.tabButtons.clear();
        if (listWidget != null)
            tabbedEntries.put(tabs.get(selectedTabIndex).getLeft(), (List) listWidget.children());
        selectedTabIndex = nextTabIndex;
        listWidget = new ListWidget(minecraft, field_22535, field_22536, isShowingTabs() ? 70 : 30, field_22536 - 32, getBackgroundLocation());
        listWidget.setSmoothScrolling(this.smoothScrollingList);
        if (tabbedEntries.size() > selectedTabIndex)
            Lists.newArrayList(tabbedEntries.values()).get(selectedTabIndex).forEach(entry -> listWidget.children().add(entry));
        int buttonWidths = (field_22535 - 50 - 12) / 3;
        this.field_22537.add(quitButton = new ButtonWidget(ID_QUIT, 25, field_22536 - 26, buttonWidths, 20, edited ? I18n.translate("text.cloth-config.cancel_discard") : I18n.translate("gui.cancel")));
        this.field_22537.add(saveButton = new ButtonWidget(ID_SAVE, 25 + 6 + buttonWidths, field_22536 - 26, buttonWidths, 20, I18n.translate("text.cloth-config.save_and_done")));
        this.field_22537.add(applyButton = new ButtonWidget(ID_APPLY, 25 + (6 + buttonWidths) * 2, field_22536 - 26, buttonWidths, 20, I18n.translate("text.cloth-config.apply")));
        saveButton.field_22511 = edited;
        if (isShowingTabs()) {
            tabsBounds = new Rectangle(0, 41, this.field_22535, 24);
            tabsLeftBounds = new Rectangle(0, 41, 18, 24);
            tabsRightBounds = new Rectangle(field_22535 - 18, 41, 18, 24);
            buttonLeftTab = new ButtonWidget(new Random().nextInt(), 4, 44, 12, 18, "");
            int j = 0;
            for (Pair<String, Integer> tab : tabs) {
                tabButtons.add(new ClothConfigTabButton(this, j, -100, 43, tab.getRight(), 20, I18n.translate(tab.getLeft())));
                j++;
            }
            buttonRightTab = new ButtonWidget(new Random().nextInt(), field_22535 - 16, 44, 12, 18, "");
        } else {
            tabsBounds = tabsLeftBounds = tabsRightBounds = new Rectangle();
        }
    }

    /*@Override
    public void handleMouseInput() {
        super.handleMouse();
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
                    listWidget.handleMouse();
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
            xx += tabButton.method_21890() + 2;
        if (xx > field_22535 - 40)
            tabsScrollProgress = MathHelper.clamp(tabsScrollProgress, 0, getTabsMaximumScrolled() - field_22535 + 40);
        else
            tabsScrollProgress = 0d;
    }

    @Override
    public void method_21925(int int_1, int int_2, float float_1) {
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
                xx += tabButton.method_21890() + 2;
            }
            if (buttonLeftTab != null)
                buttonLeftTab.field_22511 = tabsScrollProgress > 0d;
            if (buttonRightTab != null)
                buttonRightTab.field_22511 = tabsScrollProgress < getTabsMaximumScrolled() - field_22535 + 40;
        }
        if (isTransparentBackground()) {
            this.method_21879(0, 0, this.field_22535, this.field_22536, -1072689136, -804253680);
        } else {
            this.method_21934(0);
        }
        listWidget.render(int_1, int_2, float_1);
        ScissorsHandler.INSTANCE.scissor(new Rectangle(listWidget.left, listWidget.top, listWidget.width, listWidget.bottom - listWidget.top));
        for (AbstractConfigEntry child : listWidget.children())
            child.lateRender(int_1, int_2, float_1);
        ScissorsHandler.INSTANCE.removeLastScissor();
        if (isShowingTabs()) {
            method_21881(minecraft.textRenderer, title, field_22535 / 2, 18, -1);
            Rectangle onlyInnerTabBounds = new Rectangle(tabsBounds.x + 20, tabsBounds.y, tabsBounds.width - 40, tabsBounds.height);
            ScissorsHandler.INSTANCE.scissor(onlyInnerTabBounds);
            if (isTransparentBackground())
                this.method_21879(onlyInnerTabBounds.x, onlyInnerTabBounds.y, onlyInnerTabBounds.getMaxX(), onlyInnerTabBounds.getMaxY(), 0x68000000, 0x68000000);
            else
                overlayBackground(onlyInnerTabBounds, 32, 32, 32, 255, 255);
            for (ClothConfigTabButton widget : tabButtons)
                widget.method_21887(minecraft, int_1, int_2);
            drawTabsShades(0, isTransparentBackground() ? 120 : 255);
            ScissorsHandler.INSTANCE.removeLastScissor();
            buttonLeftTab.method_21887(minecraft, int_1, int_2);
            buttonRightTab.method_21887(minecraft, int_1, int_2);
        } else
            method_21881(minecraft.textRenderer, title, field_22535 / 2, 12, -1);

        if (displayErrors && isEditable()) {
            List<String> errors = Lists.newArrayList();
            for (List<AbstractConfigEntry> entries : Lists.newArrayList(tabbedEntries.values()))
                for (AbstractConfigEntry entry : entries)
                    if (entry.getConfigError().isPresent())
                        errors.add(((Optional<String>) entry.getConfigError()).get());
            if (errors.size() > 0) {
                minecraft.getTextureManager().bindTexture(CONFIG_TEX);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                String text = "Â§c" + (errors.size() == 1 ? errors.get(0) : I18n.translate("text.cloth-config.multi_error"));
                if (isTransparentBackground()) {
                    int stringWidth = minecraft.textRenderer.getStringWidth(text);
                    method_21879(8, 9, 20 + stringWidth, 14 + minecraft.textRenderer.fontHeight, 0x68000000, 0x68000000);
                }
                this.method_21879(10, 10, 0, 54, 3, 11);
                this.method_21884(minecraft.textRenderer, text, 18, 12, -1);
            }
        } else if (!isEditable()) {
            minecraft.getTextureManager().bindTexture(CONFIG_TEX);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            String text = "Â§c" + I18n.translate("text.cloth-config.not_editable");
            if (isTransparentBackground()) {
                int stringWidth = minecraft.textRenderer.getStringWidth(text);
                method_21879(8, 9, 20 + stringWidth, 14 + minecraft.textRenderer.fontHeight, 0x68000000, 0x68000000);
            }
            method_21879(10, 10, 0, 54, 3, 11);
            minecraft.textRenderer.draw(text, 18, 12, -1);
        }
        super.method_21925(int_1, int_2, float_1);
        queuedTooltips.forEach(queuedTooltip -> method_21932(queuedTooltip.getText(), queuedTooltip.getX(), queuedTooltip.getY()));
        queuedTooltips.clear();
    }

    public void queueTooltip(QueuedTooltip queuedTooltip) {
        queuedTooltips.add(queuedTooltip);
    }

    private void drawTabsShades(int lightColor, int darkColor) {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glShadeModel(GL11.GL_SMOOTH);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        Tessellator tessellator = Tessellator.INSTANCE;
        tessellator.begin();
        tessellator.color(0, 0, 0, lightColor);
        tessellator.vertex(tabsBounds.getMinX() + 20, tabsBounds.getMinY() + 4, 0.0D);
        tessellator.vertex(tabsBounds.getMaxX() - 20, tabsBounds.getMinY() + 4, 0.0D);
        tessellator.color(0, 0, 0, darkColor);
        tessellator.vertex(tabsBounds.getMaxX() - 20, tabsBounds.getMinY(), 0.0D);
        tessellator.vertex(tabsBounds.getMinX() + 20, tabsBounds.getMinY(), 0.0D);
        tessellator.end();
        tessellator.begin();
        tessellator.color(0, 0, 0, darkColor);
        tessellator.vertex(tabsBounds.getMinX() + 20, tabsBounds.getMaxY(), 0.0D);
        tessellator.vertex(tabsBounds.getMaxX() - 20, tabsBounds.getMaxY(), 0.0D);
        tessellator.color(0, 0, 0, lightColor);
        tessellator.vertex(tabsBounds.getMaxX() - 20, tabsBounds.getMaxY() - 4, 0.0D);
        tessellator.vertex(tabsBounds.getMinX() + 20, tabsBounds.getMaxY() - 4, 0.0D);
        tessellator.end();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_BLEND);
    }

    @SuppressWarnings("deprecation")
    protected void overlayBackground(Rectangle rect, int red, int green, int blue, int startAlpha, int endAlpha) {
        if (isTransparentBackground())
            return;
        Tessellator tessellator = Tessellator.INSTANCE;
        minecraft.getTextureManager().bindTexture(getBackgroundLocation());
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        tessellator.begin();
        tessellator.color(red, green, blue, endAlpha);
        tessellator.texture(rect.getMinX() / 32.0F, rect.getMaxY() / 32.0F);
        tessellator.vertex(rect.getMinX(), rect.getMaxY(), 0.0D);
        tessellator.texture(rect.getMaxX() / 32.0F, rect.getMaxY() / 32.0F);
        tessellator.vertex(rect.getMaxX(), rect.getMaxY(), 0.0D);
        tessellator.color(red, green, blue, startAlpha);
        tessellator.texture(rect.getMaxX() / 32.0F, rect.getMinY() / 32.0F);
        tessellator.vertex(rect.getMaxX(), rect.getMinY(), 0.0D);
        tessellator.texture(rect.getMinX() / 32.0F, rect.getMinY() / 32.0F);
        tessellator.vertex(rect.getMinX(), rect.getMinY(), 0.0D);
        tessellator.end();
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
        super.mouseReleased(mouseX, mouseY, state);
        if (listWidget != null)
            listWidget.mouseReleased(mouseX, mouseY, state);
    }*/

    @Override
    public void method_21926(int mouseX, int mouseY, int mouseButton) {
        if (this.focusedBinding != null && this.startedKeyCode != null && focusedBinding.isAllowMouse()) {
            if (startedKeyCode.isUnknown())
                startedKeyCode.setKeyCode(KeyInput.of(KeyInput.Type.MOUSE, mouseButton));
            else if (focusedBinding.isAllowModifiers()) {
                if (startedKeyCode.getType() == KeyInput.Type.KEYSYM) {
                    int code = startedKeyCode.getKeyCode().getKeyCode();
                    if (MinecraftClient.IS_MAC ? (code == 343 || code == 347) : (code == 341 || code == 345)) {
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
        super.method_21926(mouseX, mouseY, mouseButton);
        if (isShowingTabs()) {
            for (ClothConfigTabButton tabButton : tabButtons) {
                if (tabButton.method_21885()) {
                    tabButton.onClick();
                    return;
                }
            }
            if (buttonLeftTab != null && buttonLeftTab.method_21885()) {
                tabsScrollProgress = Integer.MIN_VALUE;
                tabsScrollVelocity = 0d;
                clampTabsScrolled();
                return;
            }
            if (buttonRightTab != null && buttonRightTab.method_21885()) {
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
    protected void method_21924(char typedChar, int keyCode) {
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
                        if (MinecraftClient.IS_MAC ? (code == 343 || code == 347) : (code == 341 || code == 345)) {
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
                    if (MinecraftClient.IS_MAC ? (keyCode == 343 || keyCode == 347) : (keyCode == 341 || keyCode == 345)) {
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
            Screen screen = new Screen() {
                @Override
                public void method_22355(boolean bl, int i) {
                    super.method_22355(bl, i);
                    if (bl)
                        minecraft.setScreen(parent);
                    else
                        minecraft.setScreen(ClothConfigScreen.this);
                }
            };
            if (confirmSave && edited)
                minecraft.setScreen(new ConfirmScreen(screen, I18n.translate("text.cloth-config.quit_config"), I18n.translate("text.cloth-config.quit_config_sure"), I18n.translate("text.cloth-config.quit_discard"), I18n.translate("gui.cancel"), new Random().nextInt()));
            else
                minecraft.setScreen(parent);
            return;
        }
        super.method_21924(typedChar, keyCode);
    }

    public boolean allowCloseWithEscape() {
        return true;
    }

    @Override
    protected void method_21930(ButtonWidget button) {
        if (button == quitButton) {
            if (confirmSave && edited)
                minecraft.setScreen(new ConfirmScreen(new Screen(), I18n.translate("text.cloth-config.quit_config"), I18n.translate("text.cloth-config.quit_config_sure"), I18n.translate("text.cloth-config.quit_discard"), I18n.translate("gui.cancel"), 1));
            else {
                button.method_21886(0, 0);
                minecraft.setScreen(parent);
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
    public void setEnabled(boolean editable) {
        this.editable = editable;
    }

    public class ListWidget<R extends DynamicElementListWidget.ElementEntry<R>> extends DynamicElementListWidget<R> {
        public ListWidget(MinecraftClient client, int width, int height, int top, int bottom, Identifier backgroundLocation) {
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
            if (!this.method_21885(double_1, double_2)) {
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
        protected void renderBackBackground(Tessellator tessellator) {
            if (!isTransparentBackground())
                super.renderBackBackground(tessellator);
            else {
                method_21879(left, top, right, bottom, 0x68000000, 0x68000000);
            }
        }

        @Override
        protected void renderHoleBackground(int int_1, int int_2, int int_3, int int_4) {
            if (!isTransparentBackground())
                super.renderHoleBackground(int_1, int_2, int_3, int_4);
        }
    }
}
