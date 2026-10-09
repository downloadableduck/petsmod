package me.shedaniel.clothconfig2.api;

import java.util.List;
import net.minecraft.util.Identifier;

public interface ConfigCategory {

    String getCategoryKey();

    @Deprecated
    List<Object> getEntries();

    ConfigCategory addEntry(AbstractConfigListEntry entry);

    ConfigCategory setCategoryBackground(Identifier ResourceLocation);

    void removeCategory();

}
