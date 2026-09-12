package me.shedaniel.clothconfig2.gui;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.AtomicDouble;
import me.shedaniel.clothconfig2.AbstractPressableButtonWidget;
import me.shedaniel.clothconfig2.api.*;
import me.shedaniel.clothconfig2.gui.entries.KeyCodeEntry;
import me.shedaniel.clothconfig2.gui.widget.DynamicElementListWidget;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiElement;
import me.shedaniel.clothconfig2.compat.GuiEventListener;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.gui.screen.Screen;
import me.shedaniel.clothconfig2.ButtonWidget;
import me.shedaniel.clothconfig2.compat.InputConstants;
import net.minecraft.client.render.TextRenderer;
import net.minecraft.client.render.platform.GlStateManager;
import net.minecraft.client.render.texture.TextureAtlasSprite;
import net.minecraft.client.render.vertex.BufferBuilder;
import net.minecraft.client.render.vertex.DefaultVertexFormat;
import net.minecraft.client.render.vertex.Tesselator;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.resource.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings({"deprecation", "rawtypes", "unchecked", "DuplicatedCode"})
@Environment(EnvType.CLIENT)
public abstract class ClothConfigScreen extends Screen {
    
    private static final Identifier CONFIG_TEX = new Identifier("cloth-config2", "textures/gui/cloth_config.png");
    private final List<QueuedTooltip> queuedTooltips = Lists.newArrayList();
    public int nextVertexTabIndex;
    public int selectedTabIndex;
    public double tabsScrollVelocity = 0d;
    public double tabsScrollProgress;
    public ListWidget<AbstractConfigEntry<AbstractConfigEntry>> listWidget;
    private KeyCodeEntry focusedBinding;
    public final List<GuiEventListener> children = Lists.newArrayList();
    private final Screen parent;
    private final LinkedHashMap<String, List<AbstractConfigEntry>> tabbedEntries;
    private final List<Pair<String, Integer>> tabs;
    private boolean edited;
    private boolean requiresRestart;
    private final boolean confirmSave;
    private ButtonWidget quitButton, saveButton, applyButton, buttonLeftTab, buttonRightTab;
    private Rectangle tabsBounds, tabsLeftBounds, tabsRightBounds;
    private final String title;
    private double tabsMaximumScrolled = -1d;
    private final boolean displayErrors;
    private final List<ClothConfigTabButton> tabButtons;
    private boolean smoothScrollingTabs = true;
    private boolean smoothScrollingList;
    private final Identifier defaultBackgroundLocation;
    private final Map<String, Identifier> categoryBackgroundLocation;
    private boolean transparentBackground = false;
    private boolean editable = true;
    @Nullable private String defaultFallbackCategory = null;
    private boolean alwaysShowTabs = false;
    private ModifierKeyCode startedKeyCode = null;
    private double lastMouseX, lastMouseY;
    private boolean hasLastMouse = false;
    
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
        TextRenderer textRenderer = Minecraft.getInstance().textRenderer;
        this.tabs = tabbedEntries.keySet().stream().map(s -> new Pair<>(s, textRenderer.getWidth(I18n.translate(s)) + 8)).collect(Collectors.toList());
        this.nextVertexTabIndex = 0;
        this.selectedTabIndex = 0;
        for (int i = 0; i < tabs.size(); i++) {
            Pair<String, Integer> pair = tabs.get(i);
            if (pair.getLeft().equals(getFallbackCategory())) {
                this.nextVertexTabIndex = i;
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
        for (int i = 0; i < tabs.size(); i++) {
            Pair<String, Integer> pair = tabs.get(i);
            if (pair.getLeft().equals(getFallbackCategory())) {
                this.nextVertexTabIndex = i;
                this.selectedTabIndex = i;
                break;
            }
        }
    }
    
    @Override
    public void tick() {
        super.tick();
        for (GuiEventListener child : children)
            if (child instanceof Tickable)
                ((Tickable) child).tick();
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
        quitButton.message = edited ? I18n.translate("text.cloth-config.cancel_discard") : I18n.translate("gui.cancel");
        saveButton.active = edited;
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
        if (openOtherScreens) {
            if (requiresRestart)
                ClothConfigScreen.this.minecraft.openScreen(new ClothRequiresRestartScreen(parent));
            else
                ClothConfigScreen.this.minecraft.openScreen(parent);
        }
        requiresRestart = false;
    }
    
    @Override
    public void init() {
        super.init();
        this.children.clear();
        this.tabButtons.clear();
        if (listWidget != null)
            tabbedEntries.put(tabs.get(selectedTabIndex).getLeft(), (List) listWidget.children());
        selectedTabIndex = nextVertexTabIndex;
        children.add(listWidget = new ListWidget(minecraft, width, height, isShowingTabs() ? 70 : 30, height - 32, getBackgroundLocation()));
        listWidget.setSmoothScrolling(this.smoothScrollingList);
        if (tabbedEntries.size() > selectedTabIndex)
            Lists.newArrayList(tabbedEntries.values()).get(selectedTabIndex).forEach(entry -> listWidget.children().add(entry));
        int buttonWidths = Math.min(200, (width - 50 - 12) / 3);
        this.buttons.add(quitButton = new me.shedaniel.clothconfig2.ButtonWidget(width / 2 - buttonWidths / 2 - buttonWidths - 6, height - 26, buttonWidths, 20, edited ? I18n.translate("text.cloth-config.cancel_discard") : I18n.translate("gui.cancel"), widget -> {
            if (confirmSave && edited)
                minecraft.openScreen(new ConfirmScreen((t, i) -> {
                    if (t)
                        minecraft.openScreen(parent);
                    else
                        minecraft.openScreen(ClothConfigScreen.this);
                }, I18n.translate("text.cloth-config.quit_config"), I18n.translate("text.cloth-config.quit_config_sure"), 0));
            else
                minecraft.openScreen(parent);
        }));
        children.add(quitButton);
        this.buttons.add(saveButton = new AbstractPressableButtonWidget(width / 2 + buttonWidths / 2 + 6, height - 26, buttonWidths, 20, I18n.translate("text.cloth-config.save_and_done")) {
            @Override
            public void onPress() {
                saveAll(true);
            }
            
            @Override
            public void render(int int_1, int int_2, float float_1) {
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
                active = edited && !hasErrors;
                message = displayErrors && hasErrors ? I18n.translate("text.cloth-config.error_cannot_save") : I18n.translate("text.cloth-config.save_and_done");
                super.render(int_1, int_2, float_1);
            }
        });
        children.add(saveButton);
        this.buttons.add(applyButton = new AbstractPressableButtonWidget(width / 2 - buttonWidths / 2, height - 26, buttonWidths, 20, I18n.translate("text.cloth-config.apply")) {
            @Override
            public void onPress() {
                if (requiresRestart)
                    ClothConfigScreen.this.minecraft.openScreen(new ClothRequiresRestartScreen(ClothConfigScreen.this));
                saveAll(false);
            }
            
            @Override
            public void render(int int_1, int int_2, float float_1) {
                active = saveButton.active;
                super.render(int_1, int_2, float_1);
            }
        });
        children.add(applyButton);
        saveButton.active = edited;
        if (isShowingTabs()) {
            tabsBounds = new Rectangle(0, 41, width, 24);
            tabsLeftBounds = new Rectangle(0, 41, 18, 24);
            tabsRightBounds = new Rectangle(width - 18, 41, 18, 24);
            children.add(buttonLeftTab = new AbstractPressableButtonWidget(4, 44, 12, 18, "") {
                @Override
                public void onPress() {
                    tabsScrollProgress = Integer.MIN_VALUE;
                    tabsScrollVelocity = 0d;
                    clampTabsScrolled();
                }
                
                public void render(int int_1, int int_2, float float_1) {
                    minecraft.getTextureManager().bind(CONFIG_TEX);
                    GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                    int int_3 = this.getYImage(this.isHovered());
                    GlStateManager.enableBlend();
                    GlStateManager.blendFunc(770, 771);
                    GlStateManager.blendFunc(770, 771);
                    this.drawTexture(x, y, 12, (18 * int_3), width, height);
                }
            });
            int j = 0;
            for (Pair<String, Integer> tab : tabs) {
                tabButtons.add(new ClothConfigTabButton(this, j, -100, 43, tab.getRight(), 20, I18n.translate(tab.getLeft())));
                j++;
            }
            children.addAll(tabButtons);
            children.add(buttonRightTab = new AbstractPressableButtonWidget(width - 16, 44, 12, 18, "") {
                @Override
                public void onPress() {
                    tabsScrollProgress = Integer.MAX_VALUE;
                    tabsScrollVelocity = 0d;
                    clampTabsScrolled();
                }
                
                public void render(int int_1, int int_2, float float_1) {
                    minecraft.getTextureManager().bind(CONFIG_TEX);
                    GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                    int int_3 = this.getYImage(this.isHovered());
                    GlStateManager.enableBlend();
                    GlStateManager.blendFunc(770, 771);
                    GlStateManager.blendFunc(770, 771);
                    this.drawTexture(x, y, 0, 18 * int_3, width, height);
                }
            });
        } else {
            tabsBounds = tabsLeftBounds = tabsRightBounds = new Rectangle();
        }
    }
    
    
    public boolean mouseScrolled(double double_1) {
        // 1.13 Screen.mouseScrolled takes only 1 param (scroll amount)
        // 1.12.2 Screen has no mouseScrolled; kept as a helper (void handler absent).
        for (GuiEventListener child : Lists.newArrayList(this.children))
            child.mouseScrolled(double_1);
        if (double_1 != 0d) {
            if (double_1 < 0)
                tabsScrollVelocity += 16;
            if (double_1 > 0)
                tabsScrollVelocity -= 16;
            return true;
        }
        return false;
    }
    
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
            buttonLeftTab.active = tabsScrollProgress > 0d;
            buttonRightTab.active = tabsScrollProgress < getTabsMaximumScrolled() - width + 40;
        }
        if (isTransparentBackground()) {
            fillGradient(0, 0, this.width, this.height, -1072689136, -804253680);
        } else {
            renderBackground(0);
        }
        listWidget.render(int_1, int_2, float_1);
        ScissorsHandler.INSTANCE.scissor(new Rectangle(listWidget.left, listWidget.top, listWidget.width, listWidget.bottom - listWidget.top));
        for (AbstractConfigEntry child : listWidget.children())
            child.lateRender(int_1, int_2, float_1);
        ScissorsHandler.INSTANCE.removeLastScissor();
        if (isShowingTabs()) {
            drawCenteredString(minecraft.textRenderer, title, width / 2, 18, -1);
            Rectangle onlyInnerTabBounds = new Rectangle(tabsBounds.x + 20, tabsBounds.y, tabsBounds.width - 40, tabsBounds.height);
            ScissorsHandler.INSTANCE.scissor(onlyInnerTabBounds);
            if (isTransparentBackground())
                fillGradient(onlyInnerTabBounds.x, onlyInnerTabBounds.y, onlyInnerTabBounds.getMaxX(), onlyInnerTabBounds.getMaxY(), 0x68000000, 0x68000000);
            else
                overlayBackground(onlyInnerTabBounds, 32, 32, 32, 255, 255);
            tabButtons.forEach(widget -> widget.render(int_1, int_2, float_1));
            drawTabsShades(0, isTransparentBackground() ? 120 : 255);
            ScissorsHandler.INSTANCE.removeLastScissor();
            buttonLeftTab.render(int_1, int_2, float_1);
            buttonRightTab.render(int_1, int_2, float_1);
        } else
            drawCenteredString(minecraft.textRenderer, title, width / 2, 12, -1);
        
        if (displayErrors && isEditable()) {
            List<String> errors = Lists.newArrayList();
            for (List<AbstractConfigEntry> entries : Lists.newArrayList(tabbedEntries.values()))
                for (AbstractConfigEntry entry : entries)
                    if (entry.getConfigError().isPresent())
                        errors.add(((Optional<String>) entry.getConfigError()).get());
            if (errors.size() > 0) {
                minecraft.getTextureManager().bind(CONFIG_TEX);
                GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                String text = "\u00A7c" + (errors.size() == 1 ? errors.get(0) : I18n.translate("text.cloth-config.multi_error"));
                if (isTransparentBackground()) {
                    int stringWidth = minecraft.textRenderer.getWidth(text);
                    fillGradient(8, 9, 20 + stringWidth, 14 + minecraft.textRenderer.fontHeight, 0x68000000, 0x68000000);
                }
                drawTexture(10, 10, 0, 54, 3, 11);
                drawString(minecraft.textRenderer, text, 18, 12, -1);
                if (errors.size() > 1) {
                    int stringWidth = minecraft.textRenderer.getWidth(text);
                    if (int_1 >= 10 && int_2 >= 10 && int_1 <= 18 + stringWidth && int_2 <= 14 + minecraft.textRenderer.fontHeight)
                        queuedTooltips.add(QueuedTooltip.create(new Point(int_1, int_2), new ArrayList<>(errors)));
                }
            }
        } else if (!isEditable()) {
            minecraft.getTextureManager().bind(CONFIG_TEX);
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            String text = "\u00A7c" + I18n.translate("text.cloth-config.not_editable");
            if (isTransparentBackground()) {
                int stringWidth = minecraft.textRenderer.getWidth(text);
                fillGradient(8, 9, 20 + stringWidth, 14 + minecraft.textRenderer.fontHeight, 0x68000000, 0x68000000);
            }
            drawTexture(10, 10, 0, 54, 3, 11);
            drawString(minecraft.textRenderer, text, 18, 12, -1);
        }
        super.render(int_1, int_2, float_1);
        queuedTooltips.forEach(queuedTooltip -> renderTooltip(queuedTooltip.getText(), queuedTooltip.getX(), queuedTooltip.getY()));
        queuedTooltips.clear();
    }
    
    public void queueTooltip(QueuedTooltip queuedTooltip) {
        queuedTooltips.add(queuedTooltip);
    }
    
    private void drawTabsShades(int lightColor, int darkColor) {
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.disableAlphaTest();
        GlStateManager.shadeModel(7425);
        // GlStateManager.disableBoundTexture(); // Not available in 1.13
        net.minecraft.client.render.vertex.Tesselator tesselator = net.minecraft.client.render.vertex.Tesselator.getInstance();
        BufferBuilder buffer = tesselator.getBuffer();
        buffer.begin(7, DefaultVertexFormat.POSITION_COLOR);
        buffer.vertex(tabsBounds.getMinX() + 20, tabsBounds.getMinY() + 4, 0.0D).texture(0, 1f).color(0, 0, 0, lightColor).nextVertex();
        buffer.vertex(tabsBounds.getMaxX() - 20, tabsBounds.getMinY() + 4, 0.0D).texture(1f, 1f).color(0, 0, 0, lightColor).nextVertex();
        buffer.vertex(tabsBounds.getMaxX() - 20, tabsBounds.getMinY(), 0.0D).texture(1f, 0).color(0, 0, 0, darkColor).nextVertex();
        buffer.vertex(tabsBounds.getMinX() + 20, tabsBounds.getMinY(), 0.0D).texture(0, 0).color(0, 0, 0, darkColor).nextVertex();
        tesselator.end();
        buffer.begin(7, DefaultVertexFormat.POSITION_COLOR);
        buffer.vertex(tabsBounds.getMinX() + 20, tabsBounds.getMaxY(), 0.0D).texture(0, 1f).color(0, 0, 0, darkColor).nextVertex();
        buffer.vertex(tabsBounds.getMaxX() - 20, tabsBounds.getMaxY(), 0.0D).texture(1f, 1f).color(0, 0, 0, darkColor).nextVertex();
        buffer.vertex(tabsBounds.getMaxX() - 20, tabsBounds.getMaxY() - 4, 0.0D).texture(1f, 0).color(0, 0, 0, lightColor).nextVertex();
        buffer.vertex(tabsBounds.getMinX() + 20, tabsBounds.getMaxY() - 4, 0.0D).texture(0, 0).color(0, 0, 0, lightColor).nextVertex();
        tesselator.end();
        // GlStateManager.enableBoundTexture(); // Not available in 1.13
        GlStateManager.shadeModel(7424);
        // GlStateManager.enableAlphaFunc(); // Not available in 1.13
        GlStateManager.disableBlend();
    }
    
    @SuppressWarnings("deprecation")
    protected void overlayBackground(Rectangle rect, int red, int green, int blue, int startAlpha, int endAlpha) {
        if (isTransparentBackground())
            return;
        net.minecraft.client.render.vertex.Tesselator tesselator2 = net.minecraft.client.render.vertex.Tesselator.getInstance();
        BufferBuilder buffer = tesselator2.getBuffer();
        minecraft.getTextureManager().bind(getBackgroundLocation());
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        float f = 32.0F;
        buffer.begin(7, DefaultVertexFormat.POSITION_COLOR);
        buffer.vertex(rect.getMinX(), rect.getMaxY(), 0.0D).texture(rect.getMinX() / 32.0F, rect.getMaxY() / 32.0F).color(red, green, blue, endAlpha).nextVertex();
        buffer.vertex(rect.getMaxX(), rect.getMaxY(), 0.0D).texture(rect.getMaxX() / 32.0F, rect.getMaxY() / 32.0F).color(red, green, blue, endAlpha).nextVertex();
        buffer.vertex(rect.getMaxX(), rect.getMinY(), 0.0D).texture(rect.getMaxX() / 32.0F, rect.getMinY() / 32.0F).color(red, green, blue, startAlpha).nextVertex();
        buffer.vertex(rect.getMinX(), rect.getMinY(), 0.0D).texture(rect.getMinX() / 32.0F, rect.getMinY() / 32.0F).color(red, green, blue, startAlpha).nextVertex();
        tesselator2.end();
    }
    
    public KeyCodeEntry getFocusedBinding() {
        return focusedBinding;
    }
    
    public void setFocusedBinding(KeyCodeEntry focusedBinding) {
        this.focusedBinding = focusedBinding;
        if (focusedBinding != null) {
            startedKeyCode = this.focusedBinding.getValue();
            startedKeyCode.setKeyCodeAndModifier(InputConstants.UNKNOWN, Modifier.none());
        } else
            startedKeyCode = null;
    }
    
    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        if (this.focusedBinding != null && this.startedKeyCode != null && !this.startedKeyCode.isUnknown() && focusedBinding.isAllowMouse()) {
            focusedBinding.setValue(startedKeyCode);
            setFocusedBinding(null);
            return;
        }
        super.mouseReleased(mouseX, mouseY, state);
        for (GuiEventListener child : Lists.newArrayList(this.children))
            child.mouseReleased((double) mouseX, (double) mouseY, state);
        this.hasLastMouse = false;
    }
    
    @Override
    public void mouseDragged(int mouseX, int mouseY, int button, long time) {
        super.mouseDragged(mouseX, mouseY, button, time);
        double deltaX = this.hasLastMouse ? (double) mouseX - this.lastMouseX : 0d;
        double deltaY = this.hasLastMouse ? (double) mouseY - this.lastMouseY : 0d;
        for (GuiEventListener child : Lists.newArrayList(this.children))
            child.mouseDragged((double) mouseX, (double) mouseY, button, deltaX, deltaY);
        this.lastMouseX = mouseX;
        this.lastMouseY = mouseY;
        this.hasLastMouse = true;
    }
    
    // 1.12.2 Screen has no keyReleased callback; retained as a helper.
    public void keyReleased(int int_1, int int_2, int int_3) {
        if (this.focusedBinding != null && this.startedKeyCode != null && focusedBinding.isAllowKey()) {
            focusedBinding.setValue(startedKeyCode);
            setFocusedBinding(null);
        }
    }
    
    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        int int_1 = button;
        if (this.focusedBinding != null && this.startedKeyCode != null && focusedBinding.isAllowMouse()) {
            if (startedKeyCode.isUnknown())
                startedKeyCode.setKeyCode(InputConstants.Type.MOUSE.getOrCreate(int_1));
            else if (focusedBinding.isAllowModifiers()) {
                if (startedKeyCode.getType() == InputConstants.Type.KEYSYM) {
                    int code = startedKeyCode.getKeyCode().getValue();
                    if (Minecraft.IS_MAC ? (code == 343 || code == 347) : (code == 341 || code == 345)) {
                        Modifier modifier = startedKeyCode.getModifier();
                        startedKeyCode.setModifier(Modifier.of(modifier.hasAlt(), true, modifier.hasShift()));
                        startedKeyCode.setKeyCode(InputConstants.Type.MOUSE.getOrCreate(int_1));
                        return;
                    } else if (code == 344 || code == 340) {
                        Modifier modifier = startedKeyCode.getModifier();
                        startedKeyCode.setModifier(Modifier.of(modifier.hasAlt(), modifier.hasControl(), true));
                        startedKeyCode.setKeyCode(InputConstants.Type.MOUSE.getOrCreate(int_1));
                        return;
                    } else if (code == 342 || code == 346) {
                        Modifier modifier = startedKeyCode.getModifier();
                        startedKeyCode.setModifier(Modifier.of(true, modifier.hasControl(), modifier.hasShift()));
                        startedKeyCode.setKeyCode(InputConstants.Type.MOUSE.getOrCreate(int_1));
                        return;
                    }
                }
            }
            return;
        } else {
            if (this.focusedBinding != null)
                return;
            super.mouseClicked(mouseX, mouseY, button);
            for (GuiEventListener child : Lists.newArrayList(this.children)) {
                if (child.mouseClicked((double) mouseX, (double) mouseY, button))
                    break;
            }
        }
        this.hasLastMouse = false;
    }
    
    @Override
    public void keyPressed(char typedChar, int keyCode) {
        int int_1 = keyCode;
        int int_2 = 0;
        if (this.focusedBinding != null && (focusedBinding.isAllowKey() || int_1 == 1)) {
            if (int_1 != 1) {
                if (startedKeyCode.isUnknown())
                    startedKeyCode.setKeyCode(InputConstants.getKey(int_1, int_2));
                else if (focusedBinding.isAllowModifiers()) {
                    if (startedKeyCode.getType() == InputConstants.Type.KEYSYM) {
                        int code = startedKeyCode.getKeyCode().getValue();
                        if (Minecraft.IS_MAC ? (code == 343 || code == 347) : (code == 341 || code == 345)) {
                            Modifier modifier = startedKeyCode.getModifier();
                            startedKeyCode.setModifier(Modifier.of(modifier.hasAlt(), true, modifier.hasShift()));
                            startedKeyCode.setKeyCode(InputConstants.getKey(int_1, int_2));
                            return;
                        } else if (code == 344 || code == 340) {
                            Modifier modifier = startedKeyCode.getModifier();
                            startedKeyCode.setModifier(Modifier.of(modifier.hasAlt(), modifier.hasControl(), true));
                            startedKeyCode.setKeyCode(InputConstants.getKey(int_1, int_2));
                            return;
                        } else if (code == 342 || code == 346) {
                            Modifier modifier = startedKeyCode.getModifier();
                            startedKeyCode.setModifier(Modifier.of(true, modifier.hasControl(), modifier.hasShift()));
                            startedKeyCode.setKeyCode(InputConstants.getKey(int_1, int_2));
                            return;
                        }
                    }
                    if (Minecraft.IS_MAC ? (int_1 == 343 || int_1 == 347) : (int_1 == 341 || int_1 == 345)) {
                        Modifier modifier = startedKeyCode.getModifier();
                        startedKeyCode.setModifier(Modifier.of(modifier.hasAlt(), true, modifier.hasShift()));
                        return;
                    } else if (int_1 == 344 || int_1 == 340) {
                        Modifier modifier = startedKeyCode.getModifier();
                        startedKeyCode.setModifier(Modifier.of(modifier.hasAlt(), modifier.hasControl(), true));
                        return;
                    } else if (int_1 == 342 || int_1 == 346) {
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
        if (this.focusedBinding != null && int_1 != 1)
            return;
        if (int_1 == 1) {
            if (confirmSave && edited)
                minecraft.openScreen(new ConfirmScreen((t, i) -> {
                    if (t)
                        minecraft.openScreen(parent);
                    else
                        minecraft.openScreen(ClothConfigScreen.this);
                }, I18n.translate("text.cloth-config.quit_config"), I18n.translate("text.cloth-config.quit_config_sure"), 0));
            else
                minecraft.openScreen(parent);
            return;
        }
        super.keyPressed(typedChar, keyCode);
        for (GuiEventListener child : Lists.newArrayList(this.children)) {
            child.keyPressed(keyCode, 0, 0);
            child.charTyped(typedChar, 0);
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
    
    public class ListWidget<R extends DynamicElementListWidget.ElementEntry<R>> extends DynamicElementListWidget<R> {
        public ListWidget(Minecraft client, int width, int height, int top, int bottom, Identifier backgroundLocation) {
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

        public List<? extends GuiEventListener> getChildren() {
            return this.children();
        }

        @Override
        public boolean mouseClicked(double double_1, double double_2, int int_1) {
            this.updateScrollingState(double_1, double_2, int_1);
            if (!this.isMouseOver(double_1, double_2)) {
                return false;
            } else {
                for (R entry : children()) {
                    if (entry.mouseClicked(double_1, double_2, int_1)) {
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
        
        protected void renderBackBackground(BufferBuilder buffer, Tesselator tesselator) {
            if (!isTransparentBackground())
                super.renderBackBackground(buffer, tesselator);
            else {
                fillGradient(left, top, right, bottom, 0x68000000, 0x68000000);
            }
        }
        
        @Override
        protected void renderHoleBackground(int int_1, int int_2, int int_3, int int_4) {
            if (!isTransparentBackground())
                super.renderHoleBackground(int_1, int_2, int_3, int_4);
        }
    }
    
}
