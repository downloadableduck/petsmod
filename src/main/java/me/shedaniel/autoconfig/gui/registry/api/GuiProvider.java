//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.autoconfig.gui.registry.api;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;

import java.lang.reflect.Field;
import java.util.List;

@FunctionalInterface
public interface GuiProvider {
    List<AbstractConfigListEntry> get(String var1, Field var2, Object var3, Object var4, GuiRegistryAccess var5);
}
