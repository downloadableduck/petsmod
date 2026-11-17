//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.autoconfig;

import me.shedaniel.autoconfig.event.ConfigSerializeEvent;
import org.jetbrains.annotations.ApiStatus.NonExtendable;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

@NonExtendable
public interface ConfigHolder<T extends ConfigData> extends Supplier<T> {
    @NotNull Class<T> getConfigClass();

    void save();

    boolean load();

    T getConfig();

    void setConfig(T var1);

    void registerSaveListener(ConfigSerializeEvent.Save<T> var1);

    void registerLoadListener(ConfigSerializeEvent.Load<T> var1);

    default T get() {
        return this.getConfig();
    }

    void resetToDefault();
}
