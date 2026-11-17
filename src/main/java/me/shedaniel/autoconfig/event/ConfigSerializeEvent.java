//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.autoconfig.event;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.ConfigHolder;

public final class ConfigSerializeEvent {
    private ConfigSerializeEvent() {
    }

    @FunctionalInterface
    public interface Load<T extends ConfigData> {
        int onLoad(ConfigHolder<T> var1, T var2);
    }

    @FunctionalInterface
    public interface Save<T extends ConfigData> {
        int onSave(ConfigHolder<T> var1, T var2);
    }
}
