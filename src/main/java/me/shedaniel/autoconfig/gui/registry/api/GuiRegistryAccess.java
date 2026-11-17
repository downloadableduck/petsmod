//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.autoconfig.gui.registry.api;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;

import java.lang.reflect.Field;
import java.util.List;

public interface GuiRegistryAccess extends GuiProvider, GuiTransformer {
    default List<AbstractConfigListEntry> getAndTransform(String i18n, Field field, Object config, Object defaults, GuiRegistryAccess registry) {
        return this.transform(this.get(i18n, field, config, defaults, registry), i18n, field, config, defaults, registry);
    }
}
