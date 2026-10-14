package me.shedaniel.forge.clothconfig2.gui;

import me.shedaniel.forge.clothconfig2.api.*;
import net.minecraft.client.Minecraft;
import net.minecraft.src.*;
import me.shedaniel.forge.clothconfig2.api.Pair;
import me.shedaniel.forge.clothconfig2.gui.entries.KeyCodeEntry;
import me.shedaniel.forge.clothconfig2.gui.entries.TextFieldListEntry;
import me.shedaniel.forge.clothconfig2.gui.widget.DynamicElementListWidget;
import me.shedaniel.forge.clothconfig2.impl.KeyInput;
import me.shedaniel.forge.math.Rectangle;
import org.lwjgl.opengl.GL11;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings({"deprecation", "rawtypes", "unchecked", "DuplicatedCode"})

public abstract class ClothConfigScreen extends GuiScreen {

    private static final String CONFIG_TEX = "/textures/gui/cloth_config.png";
    private static final int ID_QUIT = 100;
    private static final int ID_SAVE = 101;
    private static final int ID_APPLY = 102;
    private final List<QueuedTooltip> queuedTooltips = new ArrayList<>();
    private final GuiScreen parent;
    private final LinkedHashMap<String, List<AbstractConfigEntry>> tabbedEntries;
    private final List<Pair<String, Integer>> tabs;
    private final boolean confirmSave;
    private final String title;
    private final boolean displayErrors;
    private final List<ClothConfigTabButton> tabButtons;
    private final String defaultBackgroundLocation;
    private final Map<String, String> categoryBackgroundLocation;
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
    
    private String defaultFallbackCategory = null;
    private boolean alwaysShowTabs = false;
    private ModifierKeyCode startedKeyCode = null;
    private final Minecraft minecraft = Minecraft.getMinecraft();

    @Deprecated
    public ClothConfigScreen(GuiScreen parent, String title, Map<String, List<Pair<String, Object>>> o, boolean confirmSave, boolean displayErrors, boolean smoothScrollingList, String defaultBackgroundLocation, Map<String, String> categoryBackgroundLocation) {
        super();
        this.parent = parent;
        this.title = title;
        this.tabbedEntries = new LinkedHashMap<>();
        this.smoothScrollingList = smoothScrollingList;
        this.defaultBackgroundLocation = defaultBackgroundLocation;
        o.forEach((tab, pairs) -> {
            List<AbstractConfigEntry> list = new ArrayList<>();
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
        FontRenderer font = Minecraft.getMinecraft().fontRenderer;
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
        this.tabButtons = new ArrayList<>();
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
        return transparentBackground && Minecraft.getMinecraft().theWorld != null;
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
    public void setFallbackCategory( String defaultFallbackCategory) {
        this.defaultFallbackCategory = defaultFallbackCategory;
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        if (listWidget != null) {
            listWidget.tick();
            for (AbstractConfigEntry child : listWidget.children())
                child.updateScreen();
        }
        if (saveButton != null) {
            boolean hasErrors = false;
            if (displayErrors)
                for (List<AbstractConfigEntry> entries : new ArrayList<>(tabbedEntries.values())) {
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

    public String getBackgroundLocation() {
        if (categoryBackgroundLocation.containsKey(new ArrayList<>(tabbedEntries.keySet()).get(selectedTabIndex)))
            return categoryBackgroundLocation.get(new ArrayList<>(tabbedEntries.keySet()).get(selectedTabIndex));
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
        for (List<AbstractConfigEntry> entries : new ArrayList<>(tabbedEntries.values()))
            for (AbstractConfigEntry entry : entries)
                entry.save();
        save();
        setEdited(false);
        requiresRestart = false;
        if (openOtherScreens) {
            if (requiresRestart)
                Minecraft.getMinecraft().displayGuiScreen(new ClothRequiresRestartScreen(parent));
            else
                Minecraft.getMinecraft().displayGuiScreen(parent);
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
            new ArrayList<>(tabbedEntries.values()).get(selectedTabIndex).forEach(entry -> listWidget.children().add(entry));
        int buttonWidths = (width - 50 - 12) / 3;
        this.buttonList.add(quitButton = new GuiButton(ID_QUIT, 25, height - 26, buttonWidths, 20, edited ? I18n.format("text.cloth-config.cancel_discard") : I18n.format("gui.cancel")));
        this.buttonList.add(saveButton = new GuiButton(ID_SAVE, 25 + 6 + buttonWidths, height - 26, buttonWidths, 20, I18n.format("text.cloth-config.save_and_done")));
        this.buttonList.add(applyButton = new GuiButton(ID_APPLY, 25 + (6 + buttonWidths) * 2, height - 26, buttonWidths, 20, I18n.format("text.cloth-config.apply")));
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
            double d = 0;
            for (Pair<String, Integer> pair : tabs)
                d += pair.getRight() + 2;
            tabsMaximumScrolled = d;
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
            xx += tabButton.width + 2;
        if (xx > width - 40)
            tabsScrollProgress = Math.max(0, Math.min(tabsScrollProgress, getTabsMaximumScrolled() - width + 40));
        else
            tabsScrollProgress = 0d;
    }

    @Override
    public void drawScreen(int int_1, int int_2, float float_1) {
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
                tabButton.xPosition = xx;
                xx += tabButton.width + 2;
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
            buttonLeftTab.drawButton(minecraft, int_1, int_2);
            buttonRightTab.drawButton(minecraft, int_1, int_2);
        } else
            drawCenteredString(minecraft.fontRenderer, title, width / 2, 12, -1);

        if (displayErrors && isEditable()) {
            List<String> errors = new ArrayList<>();
            for (List<AbstractConfigEntry> entries : new ArrayList<>(tabbedEntries.values()))
                for (AbstractConfigEntry entry : entries)
                    if (entry.getConfigError().isPresent())
                        errors.add(((Optional<String>) entry.getConfigError()).get());
            if (errors.size() > 0) {
                minecraft.renderEngine.bindTexture(CONFIG_TEX);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                String text = "Â§c" + (errors.size() == 1 ? errors.get(0) : I18n.format("text.cloth-config.multi_error"));
                if (isTransparentBackground()) {
                    int stringWidth = minecraft.fontRenderer.getStringWidth(text);
                    drawGradientRect(8, 9, 20 + stringWidth, 14 + minecraft.fontRenderer.FONT_HEIGHT, 0x68000000, 0x68000000);
                }
                drawTexturedModalRect(10, 10, 0, 54, 3, 11);
                drawString(minecraft.fontRenderer, text, 18, 12, -1);
            }
        } else if (!isEditable()) {
            minecraft.renderEngine.bindTexture(CONFIG_TEX);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            String text = "Â§c" + I18n.format("text.cloth-config.not_editable");
            if (isTransparentBackground()) {
                int stringWidth = minecraft.fontRenderer.getStringWidth(text);
                drawGradientRect(8, 9, 20 + stringWidth, 14 + minecraft.fontRenderer.FONT_HEIGHT, 0x68000000, 0x68000000);
            }
            drawTexturedModalRect(10, 10, 0, 54, 3, 11);
            drawString(minecraft.fontRenderer, text, 18, 12, -1);
        }
        super.drawScreen(int_1, int_2, float_1);
        queuedTooltips.forEach(queuedTooltip -> drawHoveringText(queuedTooltip.getText(), queuedTooltip.getX(), queuedTooltip.getY(), Minecraft.getMinecraft().fontRenderer));
        queuedTooltips.clear();
    }

    protected void drawHoveringText(List<String> lines, int mouseX, int mouseY, FontRenderer font) {
        if (lines.isEmpty()) {
            return;
        }
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        int maxWidth = 0;
        for (String line : lines) {
            int w = font.getStringWidth(line);
            if (w > maxWidth) {
                maxWidth = w;
            }
        }
        int x = mouseX + 12;
        int y = mouseY - 12;
        int totalHeight = 8;
        if (lines.size() > 1) {
            totalHeight += 2 + (lines.size() - 1) * 10;
        }
        if (x + maxWidth > this.width) {
            x -= 28 + maxWidth;
        }
        if (y + totalHeight + 6 > this.height) {
            y = this.height - totalHeight - 6;
        }
        this.zLevel = 300.0F;
        int bg = -267386864;
        this.drawGradientRect(x - 3, y - 4, x + maxWidth + 3, y - 3, bg, bg);
        this.drawGradientRect(x - 3, y + totalHeight + 3, x + maxWidth + 3, y + totalHeight + 4, bg, bg);
        this.drawGradientRect(x - 3, y - 3, x + maxWidth + 3, y + totalHeight + 3, bg, bg);
        this.drawGradientRect(x - 4, y - 3, x - 3, y + totalHeight + 3, bg, bg);
        this.drawGradientRect(x + maxWidth + 3, y - 3, x + maxWidth + 4, y + totalHeight + 3, bg, bg);
        int border = 1347420415;
        int border2 = (border & 16711422) >> 1 | border & -16777216;
        this.drawGradientRect(x - 3, y - 3 + 1, x - 3 + 1, y + totalHeight + 3 - 1, border, border2);
        this.drawGradientRect(x + maxWidth + 2, y - 3 + 1, x + maxWidth + 3, y + totalHeight + 3 - 1, border, border2);
        this.drawGradientRect(x - 3, y - 3, x + maxWidth + 3, y - 3 + 1, border, border);
        this.drawGradientRect(x - 3, y + totalHeight + 2, x + maxWidth + 3, y + totalHeight + 3, border2, border2);
        for (int i = 0; i < lines.size(); ++i) {
            font.drawStringWithShadow(lines.get(i), x, y, -1);
            if (i == 0) {
                y += 2;
            }
            y += 10;
        }
        this.zLevel = 0.0F;
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    public void queueTooltip(QueuedTooltip queuedTooltip) {
        queuedTooltips.add(queuedTooltip);
    }

    private void drawTabsShades(int lightColor, int darkColor) {
        GL11.glEnable(GL11.GL_BLEND);
        org.lwjgl.opengl.GL11.glBlendFunc(770, 771);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glShadeModel(7425);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        Tessellator buffer = Tessellator.instance;
        buffer.startDrawingQuads();
        buffer.setColorRGBA(0, 0, 0, lightColor);
        buffer.addVertexWithUV(tabsBounds.getMinX() + 20, tabsBounds.getMinY() + 4, 0.0D, 0, 1f);
        buffer.addVertexWithUV(tabsBounds.getMaxX() - 20, tabsBounds.getMinY() + 4, 0.0D, 1f, 1f);
        buffer.setColorRGBA(0, 0, 0, darkColor);
        buffer.addVertexWithUV(tabsBounds.getMaxX() - 20, tabsBounds.getMinY(), 0.0D, 1f, 0);
        buffer.addVertexWithUV(tabsBounds.getMinX() + 20, tabsBounds.getMinY(), 0.0D, 0, 0);
        buffer.draw();
        buffer.startDrawingQuads();
        buffer.setColorRGBA(0, 0, 0, darkColor);
        buffer.addVertexWithUV(tabsBounds.getMinX() + 20, tabsBounds.getMaxY(), 0.0D, 0, 1f);
        buffer.addVertexWithUV(tabsBounds.getMaxX() - 20, tabsBounds.getMaxY(), 0.0D, 1f, 1f);
        buffer.setColorRGBA(0, 0, 0, lightColor);
        buffer.addVertexWithUV(tabsBounds.getMaxX() - 20, tabsBounds.getMaxY() - 4, 0.0D, 1f, 0);
        buffer.addVertexWithUV(tabsBounds.getMinX() + 20, tabsBounds.getMaxY() - 4, 0.0D, 0, 0);
        buffer.draw();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glShadeModel(7424);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glDisable(GL11.GL_BLEND);
    }

    @SuppressWarnings("deprecation")
    protected void overlayBackground(Rectangle rect, int red, int green, int blue, int startAlpha, int endAlpha) {
        if (isTransparentBackground())
            return;
        Tessellator buffer = Tessellator.instance;
        minecraft.renderEngine.bindTexture(getBackgroundLocation());
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        float f = 32.0F;
        buffer.startDrawingQuads();
        buffer.setColorRGBA(red, green, blue, endAlpha);
        buffer.addVertexWithUV(rect.getMinX(), rect.getMaxY(), 0.0D, rect.getMinX() / 32.0F, rect.getMaxY() / 32.0F);
        buffer.addVertexWithUV(rect.getMaxX(), rect.getMaxY(), 0.0D, rect.getMaxX() / 32.0F, rect.getMaxY() / 32.0F);
        buffer.setColorRGBA(red, green, blue, startAlpha);
        buffer.addVertexWithUV(rect.getMaxX(), rect.getMinY(), 0.0D, rect.getMaxX() / 32.0F, rect.getMinY() / 32.0F);
        buffer.addVertexWithUV(rect.getMinX(), rect.getMinY(), 0.0D, rect.getMinX() / 32.0F, rect.getMinY() / 32.0F);
        buffer.draw();
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
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (this.focusedBinding != null && this.startedKeyCode != null && focusedBinding.isAllowMouse()) {
            if (startedKeyCode.isUnknown())
                startedKeyCode.setKeyCode(KeyInput.of(KeyInput.Type.MOUSE, mouseButton));
            else if (focusedBinding.isAllowModifiers()) {
                if (startedKeyCode.getType() == KeyInput.Type.KEYSYM) {
                    int code = startedKeyCode.getKeyCode().getKeyCode();
                    if (GuiScreen.isMacOs ? (code == 343 || code == 347) : (code == 341 || code == 345)) {
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
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (isShowingTabs()) {
            for (ClothConfigTabButton tabButton : tabButtons) {
                if (tabButton.func_146115_a(mouseX, mouseY)) {
                    tabButton.onClick();
                    return;
                }
            }
            if (buttonLeftTab != null && buttonLeftTab.mousePressed(minecraft, mouseX, mouseY)) {
                tabsScrollProgress = Integer.MIN_VALUE;
                tabsScrollVelocity = 0d;
                clampTabsScrolled();
                return;
            }
            if (buttonRightTab != null && buttonRightTab.mousePressed(minecraft, mouseX, mouseY)) {
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
    protected void keyTyped(char typedChar, int keyCode) {
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
                        if (GuiScreen.isMacOs ? (code == 343 || code == 347) : (code == 341 || code == 345)) {
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
                    if (GuiScreen.isMacOs ? (keyCode == 343 || keyCode == 347) : (keyCode == 341 || keyCode == 345)) {
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
                minecraft.displayGuiScreen(new GuiYesNo(new QuitSaveConsumer(), I18n.format("text.cloth-config.quit_config"), I18n.format("text.cloth-config.quit_config_sure"), I18n.format("text.cloth-config.quit_discard"), I18n.format("gui.cancel"), new Random().nextInt()));
            else
                minecraft.displayGuiScreen(parent);
            return;
        }
        try {
            super.keyTyped(typedChar, keyCode);
        } catch (Exception e) {}
    }

    public boolean allowCloseWithEscape() {
        return true;
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button == quitButton) {
            if (confirmSave && edited)
                minecraft.displayGuiScreen(new GuiYesNo(new QuitSaveConsumer(), I18n.format("text.cloth-config.quit_config"), I18n.format("text.cloth-config.quit_config_sure"), I18n.format("text.cloth-config.quit_discard"), I18n.format("gui.cancel"), 1));
            else {
                button.mouseReleased(0, 0);
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

    private class QuitSaveConsumer extends GuiScreen {
        private void handle(boolean t) {
            if (!t)
                minecraft.displayGuiScreen(ClothConfigScreen.this);
            else
                minecraft.displayGuiScreen(parent);
        }

        @Override
        public void confirmClicked(boolean p_confirmResult_1_, int p_confirmResult_2_) {
            this.handle(p_confirmResult_1_);
        }
    }

    public class ListWidget<R extends DynamicElementListWidget.ElementEntry<R>> extends DynamicElementListWidget<R> {
        public ListWidget(Minecraft client, int width, int height, int top, int bottom, String backgroundLocation) {
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
            if (!this.func_146115_a(double_1, double_2)) {
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
