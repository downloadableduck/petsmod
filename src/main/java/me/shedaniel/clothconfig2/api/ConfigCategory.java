package me.shedaniel.clothconfig2.api;




import java.util.List;


public interface ConfigCategory {
    
    String getCategoryKey();
    
    @Deprecated
    List<Object> getEntries();
    
    ConfigCategory addEntry(AbstractConfigListEntry entry);
    
    ConfigCategory setCategoryBackground(String identifier);
    
    void removeCategory();
    
}