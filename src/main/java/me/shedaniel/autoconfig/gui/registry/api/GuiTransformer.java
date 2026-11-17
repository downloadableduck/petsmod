//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.autoconfig.gui.registry.api;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;

import java.lang.reflect.Field;
import java.util.List;

@FunctionalInterface
public interface GuiTransformer {
    List<AbstractConfigListEntry> transform(List<AbstractConfigListEntry> var1, String var2, Field var3, Object var4, Object var5, GuiRegistryAccess var6);
}
