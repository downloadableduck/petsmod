package me.shedaniel.forge.clothconfig2.api;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ResourceLocation;

import java.util.List;

@SideOnly(Side.CLIENT)
public interface ConfigCategory {

    String getCategoryKey();

    @Deprecated
    List<Object> getEntries();

    ConfigCategory addEntry(AbstractConfigListEntry entry);

    ConfigCategory setCategoryBackground(ResourceLocation identifier);

    void removeCategory();

}
