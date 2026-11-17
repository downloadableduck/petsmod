//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.api;

import java.util.List;

public interface ConfigCategory {
    String getCategoryKey();

    /** @deprecated */
    @Deprecated
    List<Object> getEntries();

    ConfigCategory addEntry(AbstractConfigListEntry var1);

    ConfigCategory setCategoryBackground(String var1);

    void removeCategory();
}
