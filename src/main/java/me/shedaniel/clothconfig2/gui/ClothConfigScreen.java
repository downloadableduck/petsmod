//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.gui;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.AtomicDouble;
import com.jeff.pets.compat.GlStateManager;
import me.shedaniel.clothconfig2.AbstractPressableButtonWidget;
import me.shedaniel.clothconfig2.ButtonWidget;
import me.shedaniel.clothconfig2.api.*;
import me.shedaniel.clothconfig2.compat.GuiEventListener;
import me.shedaniel.clothconfig2.compat.GuiVertexCompat;
import me.shedaniel.clothconfig2.compat.GuiVertexCompat.Builder;
import me.shedaniel.clothconfig2.compat.InputConstants;
import me.shedaniel.clothconfig2.compat.InputConstants.Type;
import me.shedaniel.clothconfig2.gui.entries.KeyCodeEntry;
import me.shedaniel.clothconfig2.gui.widget.DynamicElementListWidget;
import me.shedaniel.clothconfig2.impl.ConfigPair;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.util.OperatingSystem;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

public abstract class ClothConfigScreen extends Screen {
    private static final String CONFIG_TEX = "textures/gui/cloth_config.png";
    private final List<QueuedTooltip> queuedTooltips = Lists.newArrayList();
    public int nextVertexTabIndex;
    public int selectedTabIndex;
    public double tabsScrollVelocity = 0.0;
    public double tabsScrollProgress;
    public ListWidget<AbstractConfigEntry<AbstractConfigEntry>> listWidget;
    private KeyCodeEntry focusedBinding;
    public final List<GuiEventListener> children = Lists.newArrayList();
    private final Screen parent;
    private final LinkedHashMap<String, List<AbstractConfigEntry>> tabbedEntries;
    private final List<ConfigPair<String, Integer>> tabs;
    private boolean edited;
    private boolean requiresRestart;
    private final boolean confirmSave;
    private ButtonWidget quitButton;
    private ButtonWidget saveButton;
    private ButtonWidget applyButton;
    private ButtonWidget buttonLeftTab;
    private ButtonWidget buttonRightTab;
    private Rectangle tabsBounds;
    private Rectangle tabsLeftBounds;
    private Rectangle tabsRightBounds;
    private final String title;
    private double tabsMaximumScrolled = -1.0;
    private final boolean displayErrors;
    private final List<ClothConfigTabButton> tabButtons;
    private boolean smoothScrollingTabs = true;
    private boolean smoothScrollingList;
    private final String defaultBackgroundLocation;
    private final Map<String, String> categoryBackgroundLocation;
    private boolean transparentBackground = false;
    private boolean editable = true;
    private @Nullable String defaultFallbackCategory = null;
    private boolean alwaysShowTabs = false;
    private ModifierKeyCode startedKeyCode = null;
    private double lastMouseX;
    private double lastMouseY;
    private boolean hasLastMouse = false;

    /** @deprecated */
    @Deprecated
    public ClothConfigScreen(Screen parent, String title, Map<String, List<ConfigPair<String, Object>>> o, boolean confirmSave, boolean displayErrors, boolean smoothScrollingList, String defaultBackgroundLocation, Map<String, String> categoryBackgroundLocation) {
        this.parent = parent;
        this.title = title;
        this.tabbedEntries = Maps.newLinkedHashMap();
        this.smoothScrollingList = smoothScrollingList;
        this.defaultBackgroundLocation = defaultBackgroundLocation;
        o.forEach((tab, pairs) -> {
            List<AbstractConfigEntry> list = Lists.newArrayList();
            Iterator i$ = pairs.iterator();

            while(i$.hasNext()) {
                ConfigPair<String, Object> pair = (ConfigPair)i$.next();
                if (!(pair.getRight() instanceof AbstractConfigListEntry)) {
                    throw new IllegalArgumentException("Unsupported Type (" + (String)pair.getLeft() + "): " + pair.getRight().getClass().getSimpleName());
                }

                list.add((AbstractConfigListEntry)pair.getRight());
            }

            list.forEach((entry) -> {
                entry.setScreen(this);
            });
            this.tabbedEntries.put(tab, list);
        });
        TextRenderer textRenderer = Minecraft.INSTANCE.textRenderer;
        this.tabs = (List)this.tabbedEntries.keySet().stream().map((s) -> {
            return new ConfigPair(s, textRenderer.getWidth(I18n.getTranslation(s)) + 8);
        }).collect(Collectors.toList());
        this.nextVertexTabIndex = 0;
        this.selectedTabIndex = 0;

        for(int i = 0; i < this.tabs.size(); ++i) {
            ConfigPair<String, Integer> pair = (ConfigPair)this.tabs.get(i);
            if (((String)pair.getLeft()).equals(this.getFallbackCategory())) {
                this.nextVertexTabIndex = i;
                this.selectedTabIndex = i;
                break;
            }
        }

        this.confirmSave = confirmSave;
        this.edited = false;
        this.requiresRestart = false;
        this.tabsScrollProgress = 0.0;
        this.tabButtons = Lists.newArrayList();
        this.displayErrors = displayErrors;
        this.categoryBackgroundLocation = categoryBackgroundLocation;
    }

    public boolean isShowingTabs() {
        return this.isAlwaysShowTabs() || this.tabs.size() > 1;
    }

    public boolean isAlwaysShowTabs() {
        return this.alwaysShowTabs;
    }

    /** @deprecated */
    @Deprecated
    public void setAlwaysShowTabs(boolean alwaysShowTabs) {
        this.alwaysShowTabs = alwaysShowTabs;
    }

    public boolean isTransparentBackground() {
        return this.transparentBackground && Minecraft.INSTANCE.world != null;
    }

    /** @deprecated */
    @Deprecated
    public void setTransparentBackground(boolean transparentBackground) {
        this.transparentBackground = transparentBackground;
    }

    public String getFallbackCategory() {
        return this.defaultFallbackCategory != null ? this.defaultFallbackCategory : (String)((ConfigPair)this.tabs.get(0)).getLeft();
    }

    /** @deprecated */
    @Deprecated
    public void setFallbackCategory(@Nullable String defaultFallbackCategory) {
        this.defaultFallbackCategory = defaultFallbackCategory;

        for(int i = 0; i < this.tabs.size(); ++i) {
            ConfigPair<String, Integer> pair = (ConfigPair)this.tabs.get(i);
            if (((String)pair.getLeft()).equals(this.getFallbackCategory())) {
                this.nextVertexTabIndex = i;
                this.selectedTabIndex = i;
                break;
            }
        }

    }

    public void tick() {
        super.tick();

        GuiEventListener var2;
        for(Iterator var1 = this.children.iterator(); var1.hasNext(); var2 = (GuiEventListener)var1.next()) {
        }

    }

    public String getBackgroundLocation() {
        return this.categoryBackgroundLocation.containsKey(Lists.newArrayList(this.tabbedEntries.keySet()).get(this.selectedTabIndex)) ? (String)this.categoryBackgroundLocation.get(Lists.newArrayList(this.tabbedEntries.keySet()).get(this.selectedTabIndex)) : this.defaultBackgroundLocation;
    }

    public boolean isSmoothScrollingList() {
        return this.smoothScrollingList;
    }

    /** @deprecated */
    @Deprecated
    public void setSmoothScrollingList(boolean smoothScrollingList) {
        this.smoothScrollingList = smoothScrollingList;
    }

    public boolean isSmoothScrollingTabs() {
        return this.smoothScrollingTabs;
    }

    /** @deprecated */
    @Deprecated
    public void setSmoothScrollingTabs(boolean smoothScrolling) {
        this.smoothScrollingTabs = smoothScrolling;
    }

    public boolean isEdited() {
        return this.edited;
    }

    /** @deprecated */
    @Deprecated
    public void setEdited(boolean edited) {
        this.edited = edited;
        this.quitButton.text = edited ? I18n.getTranslation("text.cloth-config.cancel_discard") : I18n.getTranslation("gui.cancel");
        this.saveButton.active = edited;
    }

    public void setEdited(boolean edited, boolean requiresRestart) {
        this.setEdited(edited);
        if (!this.requiresRestart && requiresRestart) {
            this.requiresRestart = requiresRestart;
        }

    }

    public void saveAll(boolean openOtherScreens) {
        Iterator var2 = Lists.newArrayList(this.tabbedEntries.values()).iterator();

        while(var2.hasNext()) {
            List<AbstractConfigEntry> entries = (List)var2.next();
            Iterator var4 = entries.iterator();

            while(var4.hasNext()) {
                AbstractConfigEntry entry = (AbstractConfigEntry)var4.next();
                entry.save();
            }
        }

        this.save();
        this.setEdited(false);
        if (openOtherScreens) {
            if (this.requiresRestart) {
                this.minecraft.setScreen(new ClothRequiresRestartScreen(this.parent));
            } else {
                this.minecraft.setScreen(this.parent);
            }
        }

        this.requiresRestart = false;
    }

    public void init() {
        super.init();
        this.children.clear();
        this.tabButtons.clear();
        if (this.listWidget != null) {
            this.tabbedEntries.put((String)((ConfigPair)this.tabs.get(this.selectedTabIndex)).getLeft(), (java.util.List)this.listWidget.children());
        }

        this.selectedTabIndex = this.nextVertexTabIndex;
        this.children.add(this.listWidget = new ListWidget(this.minecraft, this.width, this.height, this.isShowingTabs() ? 70 : 30, this.height - 32, this.getBackgroundLocation()));
        this.listWidget.setSmoothScrolling(this.smoothScrollingList);
        if (this.tabbedEntries.size() > this.selectedTabIndex) {
            ((List)Lists.newArrayList(this.tabbedEntries.values()).get(this.selectedTabIndex)).forEach((entry) -> {
                ((java.util.List)this.listWidget.children()).add(entry);
            });
        }

        int buttonWidths = Math.min(200, (this.width - 50 - 12) / 3);
        Screen screen = new Screen() {
            public void confirmed(boolean bl, int i) {
                super.confirmed(bl, i);
                if (bl) {
                    this.minecraft.setScreen(ClothConfigScreen.this.parent);
                } else {
                    this.minecraft.setScreen(ClothConfigScreen.this);
                }

            }
        };
        this.buttons.add(this.quitButton = new ButtonWidget(this.width / 2 - buttonWidths / 2 - buttonWidths - 6, this.height - 26, buttonWidths, 20, this.edited ? I18n.getTranslation("text.cloth-config.cancel_discard") : I18n.getTranslation("gui.cancel"), (widget) -> {
            if (this.confirmSave && this.edited) {
                this.minecraft.setScreen(new ConfirmScreen(screen, I18n.getTranslation("text.cloth-config.quit_config"), I18n.getTranslation("text.cloth-config.quit_config_sure"), "", "", 0));
            } else {
                this.minecraft.setScreen(this.parent);
            }

        }));
        this.children.add(this.quitButton);
        this.buttons.add(this.saveButton = new AbstractPressableButtonWidget(this.width / 2 + buttonWidths / 2 + 6, this.height - 26, buttonWidths, 20, I18n.getTranslation("text.cloth-config.save_and_done")) {
            public void onPress() {
                ClothConfigScreen.this.saveAll(true);
            }

            public void render(int int_1, int int_2, float float_1) {
                boolean hasErrors = false;
                if (ClothConfigScreen.this.displayErrors) {
                    Iterator var5 = Lists.newArrayList(ClothConfigScreen.this.tabbedEntries.values()).iterator();

                    while(var5.hasNext()) {
                        List<AbstractConfigEntry> entries = (List)var5.next();
                        Iterator var7 = entries.iterator();

                        while(var7.hasNext()) {
                            AbstractConfigEntry entry = (AbstractConfigEntry)var7.next();
                            if (entry.getConfigError().isPresent()) {
                                hasErrors = true;
                                break;
                            }
                        }

                        if (hasErrors) {
                            break;
                        }
                    }
                }

                this.active = ClothConfigScreen.this.edited && !hasErrors;
                this.text = ClothConfigScreen.this.displayErrors && hasErrors ? I18n.getTranslation("text.cloth-config.error_cannot_save") : I18n.getTranslation("text.cloth-config.save_and_done");
                super.render(int_1, int_2, float_1);
            }
        });
        this.children.add(this.saveButton);
        this.buttons.add(this.applyButton = new AbstractPressableButtonWidget(this.width / 2 - buttonWidths / 2, this.height - 26, buttonWidths, 20, I18n.getTranslation("text.cloth-config.apply")) {
            public void onPress() {
                if (ClothConfigScreen.this.requiresRestart) {
                    ClothConfigScreen.this.minecraft.setScreen(new ClothRequiresRestartScreen(ClothConfigScreen.this));
                }

                ClothConfigScreen.this.saveAll(false);
            }

            public void render(int int_1, int int_2, float float_1) {
                this.active = ClothConfigScreen.this.saveButton.active;
                super.render(int_1, int_2, float_1);
            }
        });
        this.children.add(this.applyButton);
        this.saveButton.active = this.edited;
        if (this.isShowingTabs()) {
            this.tabsBounds = new Rectangle(0, 41, this.width, 24);
            this.tabsLeftBounds = new Rectangle(0, 41, 18, 24);
            this.tabsRightBounds = new Rectangle(this.width - 18, 41, 18, 24);
            this.children.add(this.buttonLeftTab = new AbstractPressableButtonWidget(4, 44, 12, 18, "") {
                public void onPress() {
                    ClothConfigScreen.this.tabsScrollProgress = -2.147483648E9;
                    ClothConfigScreen.this.tabsScrollVelocity = 0.0;
                    ClothConfigScreen.this.clampTabsScrolled();
                }

                public void render(int int_1, int int_2, float float_1) {
                    ClothConfigScreen.this.minecraft.textureManager.getTextureId("textures/gui/cloth_config.png");
                    GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                    int int_3 = this.getYImage(false);
                    GlStateManager.enableBlend();
                    GlStateManager.blendFunc(770, 771);
                    GlStateManager.blendFunc(770, 771);
                    this.drawTexture(this.x, this.y, 12, 18 * int_3, this.width, this.height);
                }
            });
            int j = 0;

            for(Iterator var4 = this.tabs.iterator(); var4.hasNext(); ++j) {
                ConfigPair<String, Integer> tab = (ConfigPair)var4.next();
                this.tabButtons.add(new ClothConfigTabButton(this, j, -100, 43, (Integer)tab.getRight(), 20, I18n.getTranslation((String)tab.getLeft())));
            }

            this.children.addAll(this.tabButtons);
            this.children.add(this.buttonRightTab = new AbstractPressableButtonWidget(this.width - 16, 44, 12, 18, "") {
                public void onPress() {
                    ClothConfigScreen.this.tabsScrollProgress = 2.147483647E9;
                    ClothConfigScreen.this.tabsScrollVelocity = 0.0;
                    ClothConfigScreen.this.clampTabsScrolled();
                }

                public void render(int int_1, int int_2, float float_1) {
                    ClothConfigScreen.this.minecraft.textureManager.getTextureId("textures/gui/cloth_config.png");
                    GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                    int int_3 = this.getYImage(false);
                    GlStateManager.enableBlend();
                    GlStateManager.blendFunc(770, 771);
                    GlStateManager.blendFunc(770, 771);
                    this.drawTexture(this.x, this.y, 0, 18 * int_3, this.width, this.height);
                }
            });
        } else {
            this.tabsBounds = this.tabsLeftBounds = this.tabsRightBounds = new Rectangle();
        }

    }

    public boolean mouseScrolled(double double_1) {
        Iterator var3 = Lists.newArrayList(this.children).iterator();

        while(var3.hasNext()) {
            GuiEventListener child = (GuiEventListener)var3.next();
            child.mouseScrolled(double_1);
        }

        if (double_1 != 0.0) {
            if (double_1 < 0.0) {
                this.tabsScrollVelocity += 16.0;
            }

            if (double_1 > 0.0) {
                this.tabsScrollVelocity -= 16.0;
            }

            return true;
        } else {
            return false;
        }
    }

    public double getTabsMaximumScrolled() {
        if (this.tabsMaximumScrolled == -1.0) {
            AtomicDouble d = new AtomicDouble();
            this.tabs.forEach((pair) -> {
                d.addAndGet((double)((Integer)pair.getRight() + 2));
            });
            this.tabsMaximumScrolled = d.get();
        }

        return this.tabsMaximumScrolled + 8.0;
    }

    public void resetTabsMaximumScrolled() {
        this.tabsMaximumScrolled = -1.0;
        this.tabsScrollVelocity = 0.0;
    }

    public void clampTabsScrolled() {
        int xx = 0;

        ClothConfigTabButton tabButton;
        for(Iterator var2 = this.tabButtons.iterator(); var2.hasNext(); xx += tabButton.width + 2) {
            tabButton = (ClothConfigTabButton)var2.next();
        }

        if (xx > this.width - 40) {
            this.tabsScrollProgress = (double)Math.max(0, Math.min((int)(this.getTabsMaximumScrolled() - (double)this.width + 40.0), (int)this.tabsScrollProgress));
        } else {
            this.tabsScrollProgress = 0.0;
        }

    }

    public void render(int int_1, int int_2, float float_1) {
        Iterator var5;
        if (this.isShowingTabs()) {
            if (this.smoothScrollingTabs) {
                double change = this.tabsScrollVelocity * 0.20000000298023224;
                if (change != 0.0) {
                    if (change > 0.0 && change < 0.2) {
                        change = 0.2;
                    } else if (change < 0.0 && change > -0.2) {
                        change = -0.2;
                    }

                    this.tabsScrollProgress += change;
                    this.tabsScrollVelocity -= change;
                    if (change > 0.0 == this.tabsScrollVelocity < 0.0) {
                        this.tabsScrollVelocity = 0.0;
                    }

                    this.clampTabsScrolled();
                }
            } else {
                this.tabsScrollProgress += this.tabsScrollVelocity;
                this.tabsScrollVelocity = 0.0;
                this.clampTabsScrolled();
            }

            int xx = 24 - (int)this.tabsScrollProgress;

            ClothConfigTabButton tabButton;
            for(var5 = this.tabButtons.iterator(); var5.hasNext(); xx += tabButton.width + 2) {
                tabButton = (ClothConfigTabButton)var5.next();
                tabButton.x = xx;
            }

            this.buttonLeftTab.active = this.tabsScrollProgress > 0.0;
            this.buttonRightTab.active = this.tabsScrollProgress < this.getTabsMaximumScrolled() - (double)this.width + 40.0;
        }

        if (this.isTransparentBackground()) {
            this.fillGradient(0, 0, this.width, this.height, -1072689136, -804253680);
        } else {
            this.renderBackground(0);
        }

        this.listWidget.render(int_1, int_2, float_1);
        ScissorsHandler.INSTANCE.scissor(new Rectangle(this.listWidget.left, this.listWidget.top, this.listWidget.width, this.listWidget.bottom - this.listWidget.top));
        Iterator var12 = this.listWidget.children().iterator();

        while(var12.hasNext()) {
            AbstractConfigEntry child = (AbstractConfigEntry)var12.next();
            child.lateRender(int_1, int_2, float_1);
        }

        ScissorsHandler.INSTANCE.removeLastScissor();
        if (this.isShowingTabs()) {
            this.drawCenteredTextWithShadow(this.minecraft.textRenderer, this.title, this.width / 2, 18, -1);
            Rectangle onlyInnerTabBounds = new Rectangle(this.tabsBounds.x + 20, this.tabsBounds.y, this.tabsBounds.width - 40, this.tabsBounds.height);
            ScissorsHandler.INSTANCE.scissor(onlyInnerTabBounds);
            if (this.isTransparentBackground()) {
                this.fillGradient(onlyInnerTabBounds.x, onlyInnerTabBounds.y, onlyInnerTabBounds.getMaxX(), onlyInnerTabBounds.getMaxY(), 1744830464, 1744830464);
            } else {
                this.overlayBackground(onlyInnerTabBounds, 32, 32, 32, 255, 255);
            }

            this.tabButtons.forEach((widget) -> {
                widget.render(int_1, int_2, float_1);
            });
            this.drawTabsShades(0, this.isTransparentBackground() ? 120 : 255);
            ScissorsHandler.INSTANCE.removeLastScissor();
            this.buttonLeftTab.render(int_1, int_2, float_1);
            this.buttonRightTab.render(int_1, int_2, float_1);
        } else {
            this.drawCenteredTextWithShadow(this.minecraft.textRenderer, this.title, this.width / 2, 12, -1);
        }

        if (this.displayErrors && this.isEditable()) {
            List<String> errors = Lists.newArrayList();
            var5 = Lists.newArrayList(this.tabbedEntries.values()).iterator();

            while(var5.hasNext()) {
                List<AbstractConfigEntry> entries = (List)var5.next();
                Iterator var7 = entries.iterator();

                while(var7.hasNext()) {
                    AbstractConfigEntry entry = (AbstractConfigEntry)var7.next();
                    if (entry.getConfigError().isPresent()) {
                        errors.add((String)entry.getConfigError().get());
                    }
                }
            }

            if (errors.size() > 0) {
                this.minecraft.textureManager.getTextureId("textures/gui/cloth_config.png");
                GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
                String text = "§c" + (errors.size() == 1 ? (String)errors.get(0) : I18n.getTranslation("text.cloth-config.multi_error"));
                int stringWidth;
                if (this.isTransparentBackground()) {
                    stringWidth = this.minecraft.textRenderer.getWidth(text);
                    this.fillGradient(8, 9, 20 + stringWidth, 23, 1744830464, 1744830464);
                }

                this.drawTexture(10, 10, 0, 54, 3, 11);
                this.drawTextWithShadow(this.minecraft.textRenderer, text, 18, 12, -1);
                if (errors.size() > 1) {
                    stringWidth = this.minecraft.textRenderer.getWidth(text);
                    if (int_1 >= 10 && int_2 >= 10 && int_1 <= 18 + stringWidth && int_2 <= 23) {
                        this.queuedTooltips.add(QueuedTooltip.create(new Point(int_1, int_2), (List)(new ArrayList(errors))));
                    }
                }
            }
        } else if (!this.isEditable()) {
            this.minecraft.textureManager.getTextureId("textures/gui/cloth_config.png");
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            String text = "§c" + I18n.getTranslation("text.cloth-config.not_editable");
            if (this.isTransparentBackground()) {
                int stringWidth = this.minecraft.textRenderer.getWidth(text);
                this.fillGradient(8, 9, 20 + stringWidth, 23, 1744830464, 1744830464);
            }

            this.drawTexture(10, 10, 0, 54, 3, 11);
            this.drawTextWithShadow(this.minecraft.textRenderer, text, 18, 12, -1);
        }

        super.render(int_1, int_2, float_1);
        this.queuedTooltips.clear();
    }

    public void queueTooltip(QueuedTooltip queuedTooltip) {
        this.queuedTooltips.add(queuedTooltip);
    }

    private void drawTabsShades(int lightColor, int darkColor) {
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.disableAlphaTest();
        GlStateManager.shadeModel(7425);
        Tessellator tesselator = Tessellator.INSTANCE;
        GuiVertexCompat.Builder buffer = Builder.of(tesselator);
        buffer.begin(7, GuiVertexCompat.POSITION_COLOR);
        buffer.vertex((double)(this.tabsBounds.getMinX() + 20), (double)(this.tabsBounds.getMinY() + 4), 0.0).texture(0.0, 1.0).color(0, 0, 0, lightColor).nextVertex();
        buffer.vertex((double)(this.tabsBounds.getMaxX() - 20), (double)(this.tabsBounds.getMinY() + 4), 0.0).texture(1.0, 1.0).color(0, 0, 0, lightColor).nextVertex();
        buffer.vertex((double)(this.tabsBounds.getMaxX() - 20), (double)this.tabsBounds.getMinY(), 0.0).texture(1.0, 0.0).color(0, 0, 0, darkColor).nextVertex();
        buffer.vertex((double)(this.tabsBounds.getMinX() + 20), (double)this.tabsBounds.getMinY(), 0.0).texture(0.0, 0.0).color(0, 0, 0, darkColor).nextVertex();
        tesselator.draw();
        buffer.begin(7, GuiVertexCompat.POSITION_COLOR);
        buffer.vertex((double)(this.tabsBounds.getMinX() + 20), (double)this.tabsBounds.getMaxY(), 0.0).texture(0.0, 1.0).color(0, 0, 0, darkColor).nextVertex();
        buffer.vertex((double)(this.tabsBounds.getMaxX() - 20), (double)this.tabsBounds.getMaxY(), 0.0).texture(1.0, 1.0).color(0, 0, 0, darkColor).nextVertex();
        buffer.vertex((double)(this.tabsBounds.getMaxX() - 20), (double)(this.tabsBounds.getMaxY() - 4), 0.0).texture(1.0, 0.0).color(0, 0, 0, lightColor).nextVertex();
        buffer.vertex((double)(this.tabsBounds.getMinX() + 20), (double)(this.tabsBounds.getMaxY() - 4), 0.0).texture(0.0, 0.0).color(0, 0, 0, lightColor).nextVertex();
        tesselator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
    }

    protected void overlayBackground(Rectangle rect, int red, int green, int blue, int startAlpha, int endAlpha) {
        if (!this.isTransparentBackground()) {
            Tessellator tesselator2 = Tessellator.INSTANCE;
            GuiVertexCompat.Builder buffer = Builder.of(tesselator2);
            this.minecraft.textureManager.getTextureId(this.getBackgroundLocation());
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            float f = 32.0F;
            buffer.begin(7, GuiVertexCompat.POSITION_COLOR);
            buffer.vertex((double)rect.getMinX(), (double)rect.getMaxY(), 0.0).texture((double)((float)rect.getMinX() / 32.0F), (double)((float)rect.getMaxY() / 32.0F)).color(red, green, blue, endAlpha).nextVertex();
            buffer.vertex((double)rect.getMaxX(), (double)rect.getMaxY(), 0.0).texture((double)((float)rect.getMaxX() / 32.0F), (double)((float)rect.getMaxY() / 32.0F)).color(red, green, blue, endAlpha).nextVertex();
            buffer.vertex((double)rect.getMaxX(), (double)rect.getMinY(), 0.0).texture((double)((float)rect.getMaxX() / 32.0F), (double)((float)rect.getMinY() / 32.0F)).color(red, green, blue, startAlpha).nextVertex();
            buffer.vertex((double)rect.getMinX(), (double)rect.getMinY(), 0.0).texture((double)((float)rect.getMinX() / 32.0F), (double)((float)rect.getMinY() / 32.0F)).color(red, green, blue, startAlpha).nextVertex();
            tesselator2.draw();
        }
    }

    public KeyCodeEntry getFocusedBinding() {
        return this.focusedBinding;
    }

    public void setFocusedBinding(KeyCodeEntry focusedBinding) {
        this.focusedBinding = focusedBinding;
        if (focusedBinding != null) {
            this.startedKeyCode = this.focusedBinding.getValue();
            this.startedKeyCode.setKeyCodeAndModifier(InputConstants.UNKNOWN, Modifier.none());
        } else {
            this.startedKeyCode = null;
        }

    }

    public void mouseReleased(int mouseX, int mouseY, int state) {
        if (this.focusedBinding != null && this.startedKeyCode != null && !this.startedKeyCode.isUnknown() && this.focusedBinding.isAllowMouse()) {
            this.focusedBinding.setValue(this.startedKeyCode);
            this.setFocusedBinding((KeyCodeEntry)null);
        } else {
            super.mouseReleased(mouseX, mouseY, state);
            Iterator var4 = Lists.newArrayList(this.children).iterator();

            while(var4.hasNext()) {
                GuiEventListener child = (GuiEventListener)var4.next();
                child.mouseReleased((double)mouseX, (double)mouseY, state);
            }

            this.hasLastMouse = false;
        }
    }

    public void keyReleased(int int_1, int int_2, int int_3) {
        if (this.focusedBinding != null && this.startedKeyCode != null && this.focusedBinding.isAllowKey()) {
            this.focusedBinding.setValue(this.startedKeyCode);
            this.setFocusedBinding((KeyCodeEntry)null);
        }

    }

    public void mouseClicked(int mouseX, int mouseY, int button) {
        int int_1 = button;
        if (this.focusedBinding != null && this.startedKeyCode != null && this.focusedBinding.isAllowMouse()) {
            if (this.startedKeyCode.isUnknown()) {
                this.startedKeyCode.setKeyCode(Type.MOUSE.getOrCreate(int_1));
            } else if (this.focusedBinding.isAllowModifiers() && this.startedKeyCode.getType() == Type.KEYSYM) {
                int code;
                Modifier modifier;
                label56: {
                    code = this.startedKeyCode.getKeyCode().getValue();
                    if (Minecraft.getOperatingSystem().equals(OperatingSystem.MACOS)) {
                        if (code != 343 && code != 347) {
                            break label56;
                        }
                    } else if (code != 341 && code != 345) {
                        break label56;
                    }

                    modifier = this.startedKeyCode.getModifier();
                    this.startedKeyCode.setModifier(Modifier.of(modifier.hasAlt(), true, modifier.hasShift()));
                    this.startedKeyCode.setKeyCode(Type.MOUSE.getOrCreate(int_1));
                    return;
                }

                if (code == 344 || code == 340) {
                    modifier = this.startedKeyCode.getModifier();
                    this.startedKeyCode.setModifier(Modifier.of(modifier.hasAlt(), modifier.hasControl(), true));
                    this.startedKeyCode.setKeyCode(Type.MOUSE.getOrCreate(int_1));
                    return;
                }

                if (code == 342 || code == 346) {
                    modifier = this.startedKeyCode.getModifier();
                    this.startedKeyCode.setModifier(Modifier.of(true, modifier.hasControl(), modifier.hasShift()));
                    this.startedKeyCode.setKeyCode(Type.MOUSE.getOrCreate(int_1));
                    return;
                }
            }

        } else if (this.focusedBinding == null) {
            super.mouseClicked(mouseX, mouseY, button);
            Iterator var5 = Lists.newArrayList(this.children).iterator();

            while(var5.hasNext()) {
                GuiEventListener child = (GuiEventListener)var5.next();
                if (child.mouseClicked((double)mouseX, (double)mouseY, button)) {
                    break;
                }
            }

            this.hasLastMouse = false;
        }
    }

    public void keyPressed(char typedChar, int keyCode) {
        int int_1 = keyCode;
        int int_2 = 0;
        if (this.focusedBinding != null && (this.focusedBinding.isAllowKey() || int_1 == 1)) {
            if (int_1 == 1) {
                this.focusedBinding.setValue(ModifierKeyCode.unknown());
                this.setFocusedBinding((KeyCodeEntry)null);
            } else if (this.startedKeyCode.isUnknown()) {
                this.startedKeyCode.setKeyCode(InputConstants.getKey(int_1, int_2));
            } else if (this.focusedBinding.isAllowModifiers()) {
                if (this.startedKeyCode.getType() == Type.KEYSYM) {
                    int code;
                    Modifier modifier;
                    label94: {
                        code = this.startedKeyCode.getKeyCode().getValue();
                        if (Minecraft.getOperatingSystem().equals(OperatingSystem.MACOS)) {
                            if (code != 343 && code != 347) {
                                break label94;
                            }
                        } else if (code != 341 && code != 345) {
                            break label94;
                        }

                        modifier = this.startedKeyCode.getModifier();
                        this.startedKeyCode.setModifier(Modifier.of(modifier.hasAlt(), true, modifier.hasShift()));
                        this.startedKeyCode.setKeyCode(InputConstants.getKey(int_1, int_2));
                        return;
                    }

                    if (code == 344 || code == 340) {
                        modifier = this.startedKeyCode.getModifier();
                        this.startedKeyCode.setModifier(Modifier.of(modifier.hasAlt(), modifier.hasControl(), true));
                        this.startedKeyCode.setKeyCode(InputConstants.getKey(int_1, int_2));
                        return;
                    }

                    if (code == 342 || code == 346) {
                        modifier = this.startedKeyCode.getModifier();
                        this.startedKeyCode.setModifier(Modifier.of(true, modifier.hasControl(), modifier.hasShift()));
                        this.startedKeyCode.setKeyCode(InputConstants.getKey(int_1, int_2));
                        return;
                    }
                }

                Modifier modifier;
                label77: {
                    if (Minecraft.getOperatingSystem().equals(OperatingSystem.MACOS)) {
                        if (int_1 != 343 && int_1 != 347) {
                            break label77;
                        }
                    } else if (int_1 != 341 && int_1 != 345) {
                        break label77;
                    }

                    modifier = this.startedKeyCode.getModifier();
                    this.startedKeyCode.setModifier(Modifier.of(modifier.hasAlt(), true, modifier.hasShift()));
                    return;
                }

                if (int_1 == 344 || int_1 == 340) {
                    modifier = this.startedKeyCode.getModifier();
                    this.startedKeyCode.setModifier(Modifier.of(modifier.hasAlt(), modifier.hasControl(), true));
                    return;
                }

                if (int_1 == 342 || int_1 == 346) {
                    modifier = this.startedKeyCode.getModifier();
                    this.startedKeyCode.setModifier(Modifier.of(true, modifier.hasControl(), modifier.hasShift()));
                    return;
                }
            }

        } else if (this.focusedBinding == null || int_1 == 1) {
            if (int_1 == 1) {
                Screen screen = new Screen() {
                    public void confirmed(boolean bl, int i) {
                        super.confirmed(bl, i);
                        if (bl) {
                            this.minecraft.setScreen(ClothConfigScreen.this.parent);
                        } else {
                            this.minecraft.setScreen(ClothConfigScreen.this);
                        }

                    }
                };
                if (this.confirmSave && this.edited) {
                    this.minecraft.setScreen(new ConfirmScreen(screen, I18n.getTranslation("text.cloth-config.quit_config"), I18n.getTranslation("text.cloth-config.quit_config_sure"), "", "", 0));
                } else {
                    this.minecraft.setScreen(this.parent);
                }

            } else {
                super.keyPressed(typedChar, keyCode);
                Iterator var5 = Lists.newArrayList(this.children).iterator();

                while(var5.hasNext()) {
                    GuiEventListener child = (GuiEventListener)var5.next();
                    child.keyPressed(keyCode, 0, 0);
                    child.charTyped(typedChar, 0);
                }

            }
        }
    }

    public void save() {
    }

    public boolean isEditable() {
        return this.editable;
    }

    /** @deprecated */
    @Deprecated
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public class ListWidget<R extends DynamicElementListWidget.ElementEntry<R>> extends DynamicElementListWidget<R> {
        public ListWidget(Minecraft client, int width, int height, int top, int bottom, String backgroundLocation) {
            super(client, width, height, top, bottom, backgroundLocation);
            this.visible = false;
        }

        public int getItemWidth() {
            return this.width - 80;
        }

        public ClothConfigScreen getScreen() {
            return ClothConfigScreen.this;
        }

        protected int getScrollbarPosition() {
            return this.width - 36;
        }

        protected final void clearStuff() {
            this.clearItems();
        }

        protected void renderItem(R item, int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
            if (item instanceof AbstractConfigEntry) {
                ((AbstractConfigEntry)item).updateSelected(this.getFocused() == item);
            }

            super.renderItem(item, index, y, x, entryWidth, entryHeight, mouseX, mouseY, isSelected, delta);
        }

        public List<? extends GuiEventListener> getChildren() {
            return this.children();
        }

        public boolean mouseClicked(double double_1, double double_2, int int_1) {
            this.updateScrollingState(double_1, double_2, int_1);
            if (!this.isMouseOver(double_1, double_2)) {
                return false;
            } else {
                Iterator var6 = this.children().iterator();

                DynamicElementListWidget.ElementEntry entry;
                do {
                    if (!var6.hasNext()) {
                        if (int_1 == 0) {
                            this.clickedHeader((int)(double_1 - (double)(this.left + this.width / 2 - this.getItemWidth() / 2)), (int)(double_2 - (double)this.top) + (int)this.getScroll() - 4);
                            return true;
                        }

                        return this.scrolling;
                    }

                    entry = (DynamicElementListWidget.ElementEntry)var6.next();
                } while(!entry.mouseClicked(double_1, double_2, int_1));

                this.setFocused(entry);
                this.setDragging(true);
                return true;
            }
        }

        protected void renderBackBackground(GuiVertexCompat.Builder buffer, Tessellator tesselator) {
            if (!ClothConfigScreen.this.isTransparentBackground()) {
                super.renderBackBackground(buffer, tesselator);
            } else {
                this.fillGradient(this.left, this.top, this.right, this.bottom, 1744830464, 1744830464);
            }

        }

        protected void renderHoleBackground(int int_1, int int_2, int int_3, int int_4) {
            if (!ClothConfigScreen.this.isTransparentBackground()) {
                super.renderHoleBackground(int_1, int_2, int_3, int_4);
            }

        }
    }
}
