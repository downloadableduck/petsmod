//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.api;

import me.shedaniel.clothconfig2.impl.ConfigBuilderImpl;
import me.shedaniel.clothconfig2.impl.ConfigEntryBuilderImpl;
import net.minecraft.client.gui.screen.Screen;

import java.util.function.Consumer;

public interface ConfigBuilder {
    static ConfigBuilder create() {
        return new ConfigBuilderImpl();
    }

    /** @deprecated */
    @Deprecated
    static ConfigBuilder create(Screen parent, String title) {
        return create().setParentScreen(parent).setTitle(title);
    }

    ConfigBuilder setFallbackCategory(ConfigCategory var1);

    Screen getParentScreen();

    ConfigBuilder setParentScreen(Screen var1);

    String getTitle();

    ConfigBuilder setTitle(String var1);

    boolean isEditable();

    ConfigBuilder setEditable(boolean var1);

    ConfigCategory getOrCreateCategory(String var1);

    ConfigBuilder removeCategory(String var1);

    ConfigBuilder removeCategoryIfExists(String var1);

    boolean hasCategory(String var1);

    ConfigBuilder setShouldTabsSmoothScroll(boolean var1);

    boolean isTabsSmoothScrolling();

    ConfigBuilder setShouldListSmoothScroll(boolean var1);

    boolean isListSmoothScrolling();

    ConfigBuilder setDoesConfirmSave(boolean var1);

    boolean doesConfirmSave();

    ConfigBuilder setDoesProcessErrors(boolean var1);

    boolean doesProcessErrors();

    String getDefaultBackgroundTexture();

    ConfigBuilder setDefaultBackgroundTexture(String var1);

    Runnable getSavingRunnable();

    ConfigBuilder setSavingRunnable(Runnable var1);

    Consumer<Screen> getAfterInitConsumer();

    ConfigBuilder setAfterInitConsumer(Consumer<Screen> var1);

    default ConfigBuilder alwaysShowTabs() {
        return this.setAlwaysShowTabs(true);
    }

    boolean isAlwaysShowTabs();

    ConfigBuilder setAlwaysShowTabs(boolean var1);

    ConfigBuilder setTransparentBackground(boolean var1);

    default ConfigBuilder transparentBackground() {
        return this.setTransparentBackground(true);
    }

    default ConfigBuilder solidBackground() {
        return this.setTransparentBackground(false);
    }

    /** @deprecated */
    @Deprecated
    default ConfigEntryBuilderImpl getEntryBuilder() {
        return (ConfigEntryBuilderImpl)this.entryBuilder();
    }

    default ConfigEntryBuilder entryBuilder() {
        return ConfigEntryBuilderImpl.create();
    }

    Screen build();
}
