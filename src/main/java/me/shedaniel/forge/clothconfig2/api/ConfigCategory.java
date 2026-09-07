package me.shedaniel.forge.clothconfig2.api;

import net.minecraft.util.ResourceLocation;


import java.util.List;


public interface ConfigCategory {

    String getCategoryKey();

    @Deprecated
    List<Object> getEntries();

    ConfigCategory addEntry(AbstractConfigListEntry entry);

    ConfigCategory setCategoryBackground(ResourceLocation identifier);

    void removeCategory();

}
