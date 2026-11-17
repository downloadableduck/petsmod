//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.gui.ClothConfigScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resource.language.I18n;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/** @deprecated */
@Deprecated
public class ConfigBuilderImpl implements ConfigBuilder {
    private Runnable savingRunnable;
    private Screen parent;
    private String title = "text.cloth-config.config";
    private boolean editable = true;
    private boolean tabsSmoothScroll = true;
    private boolean listSmoothScroll = true;
    private boolean doesProcessErrors = true;
    private boolean doesConfirmSave = true;
    private boolean transparentBackground = false;
    private String defaultBackground = "";
    private Consumer<Screen> afterInitConsumer = (screen) -> {
    };
    private final Map<String, String> categoryBackground = Maps.newHashMap();
    private final Map<String, List<ConfigPair<String, Object>>> dataMap = Maps.newLinkedHashMap();
    private String fallbackCategory = null;
    private boolean alwaysShowTabs = false;

    /** @deprecated */
    @Deprecated
    public ConfigBuilderImpl() {
    }

    public boolean isAlwaysShowTabs() {
        return this.alwaysShowTabs;
    }

    public ConfigBuilder setAlwaysShowTabs(boolean alwaysShowTabs) {
        this.alwaysShowTabs = alwaysShowTabs;
        return this;
    }

    public ConfigBuilder setTransparentBackground(boolean transparentBackground) {
        this.transparentBackground = transparentBackground;
        return this;
    }

    public ConfigBuilder setAfterInitConsumer(Consumer<Screen> afterInitConsumer) {
        this.afterInitConsumer = afterInitConsumer;
        return this;
    }

    public ConfigBuilder setFallbackCategory(ConfigCategory fallbackCategory) {
        this.fallbackCategory = ((ConfigCategory)Objects.requireNonNull(fallbackCategory)).getCategoryKey();
        return this;
    }

    public Screen getParentScreen() {
        return this.parent;
    }

    public ConfigBuilder setParentScreen(Screen parent) {
        this.parent = parent;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public ConfigBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public boolean isEditable() {
        return this.editable;
    }

    public ConfigBuilder setEditable(boolean editable) {
        this.editable = editable;
        return this;
    }

    public ConfigCategory getOrCreateCategory(String categoryKey) {
        if (this.dataMap.containsKey(categoryKey)) {
            return new ConfigCategoryImpl(categoryKey, (identifier) -> {
                if (this.transparentBackground) {
                    throw new IllegalStateException("Cannot set category background if screen is using transparent background.");
                } else {
                    this.categoryBackground.put(categoryKey, identifier);
                }
            }, () -> {
                return (List)this.dataMap.get(categoryKey);
            }, () -> {
                this.removeCategory(categoryKey);
            });
        } else {
            this.dataMap.put(categoryKey, Lists.newArrayList());
            if (this.fallbackCategory == null) {
                this.fallbackCategory = categoryKey;
            }

            return new ConfigCategoryImpl(categoryKey, (identifier) -> {
                if (this.transparentBackground) {
                    throw new IllegalStateException("Cannot set category background if screen is using transparent background.");
                } else {
                    this.categoryBackground.put(categoryKey, identifier);
                }
            }, () -> {
                return (List)this.dataMap.get(categoryKey);
            }, () -> {
                this.removeCategory(categoryKey);
            });
        }
    }

    public ConfigBuilder removeCategory(String category) {
        if (this.dataMap.containsKey(category) && this.fallbackCategory.equals(category)) {
            this.fallbackCategory = null;
        }

        if (!this.dataMap.containsKey(category)) {
            throw new NullPointerException("Category doesn't exist!");
        } else {
            this.dataMap.remove(category);
            return this;
        }
    }

    public ConfigBuilder removeCategoryIfExists(String category) {
        if (this.dataMap.containsKey(category) && this.fallbackCategory.equals(category)) {
            this.fallbackCategory = null;
        }

        this.dataMap.remove(category);
        return this;
    }

    public boolean hasCategory(String category) {
        return this.dataMap.containsKey(category);
    }

    public ConfigBuilder setShouldTabsSmoothScroll(boolean shouldTabsSmoothScroll) {
        this.tabsSmoothScroll = shouldTabsSmoothScroll;
        return this;
    }

    public boolean isTabsSmoothScrolling() {
        return this.tabsSmoothScroll;
    }

    public ConfigBuilder setShouldListSmoothScroll(boolean shouldListSmoothScroll) {
        this.listSmoothScroll = shouldListSmoothScroll;
        return this;
    }

    public boolean isListSmoothScrolling() {
        return this.listSmoothScroll;
    }

    public ConfigBuilder setDoesConfirmSave(boolean confirmSave) {
        this.doesConfirmSave = confirmSave;
        return this;
    }

    public boolean doesConfirmSave() {
        return this.doesConfirmSave;
    }

    public ConfigBuilder setDoesProcessErrors(boolean processErrors) {
        this.doesProcessErrors = processErrors;
        return this;
    }

    public boolean doesProcessErrors() {
        return this.doesProcessErrors;
    }

    public String getDefaultBackgroundTexture() {
        return this.defaultBackground;
    }

    public ConfigBuilder setDefaultBackgroundTexture(String texture) {
        this.defaultBackground = texture;
        return this;
    }

    public ConfigBuilder setSavingRunnable(Runnable runnable) {
        this.savingRunnable = runnable;
        return this;
    }

    public Consumer<Screen> getAfterInitConsumer() {
        return this.afterInitConsumer;
    }

    public Screen build() {
        if (!this.dataMap.isEmpty() && this.fallbackCategory != null) {
            ClothConfigScreen screen = new ClothConfigScreen(this.parent, I18n.getTranslation(this.title), this.dataMap, this.doesConfirmSave, this.doesProcessErrors, this.listSmoothScroll, this.defaultBackground, this.categoryBackground) {
                public void save() {
                    if (ConfigBuilderImpl.this.savingRunnable != null) {
                        ConfigBuilderImpl.this.savingRunnable.run();
                    }

                }

                public void init() {
                    super.init();
                    ConfigBuilderImpl.this.afterInitConsumer.accept(this);
                }
            };
            screen.setEditable(this.editable);
            screen.setFallbackCategory(this.fallbackCategory);
            screen.setSmoothScrollingTabs(this.tabsSmoothScroll);
            screen.setTransparentBackground(this.transparentBackground);
            screen.setAlwaysShowTabs(this.alwaysShowTabs);
            return screen;
        } else {
            throw new NullPointerException("There cannot be no categories or fallback category!");
        }
    }

    public Runnable getSavingRunnable() {
        return this.savingRunnable;
    }
}
