package me.shedaniel.forge.clothconfig2.api;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
