//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.autoconfig.gui.registry;

import me.shedaniel.autoconfig.gui.registry.api.GuiRegistryAccess;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ComposedGuiRegistryAccess implements GuiRegistryAccess {
    private final List<GuiRegistryAccess> children;

    public ComposedGuiRegistryAccess(GuiRegistryAccess... children) {
        this.children = Arrays.asList(children);
    }

    public List<AbstractConfigListEntry> get(String i18n, Field field, Object config, Object defaults, GuiRegistryAccess registry) {
        return (List)this.children.stream().map((child) -> {
            return child.get(i18n, field, config, defaults, registry);
        }).filter(Objects::nonNull).findFirst().orElseThrow(() -> {
            return new RuntimeException("No ConfigGuiProvider match!");
        });
    }

    public List<AbstractConfigListEntry> transform(List<AbstractConfigListEntry> guis, String i18n, Field field, Object config, Object defaults, GuiRegistryAccess registry) {
        GuiRegistryAccess child;
        for(Iterator var7 = this.children.iterator(); var7.hasNext(); guis = child.transform(guis, i18n, field, config, defaults, registry)) {
            child = (GuiRegistryAccess)var7.next();
        }

        return guis;
    }
}
