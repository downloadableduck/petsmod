package com.jeff.duck;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "duckconfig")
public class DuckSavedData implements ConfigData {
    public boolean duckSummonedPreference;
    public String duckName;
    public String duckTexturePath;
    public int uuid;
}
