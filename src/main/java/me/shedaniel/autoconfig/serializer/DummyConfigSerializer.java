//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.autoconfig.serializer;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.util.Utils;

public class DummyConfigSerializer<T extends ConfigData> implements ConfigSerializer<T> {
    private final Class<T> configClass;

    public DummyConfigSerializer(Config definition, Class<T> configClass) {
        this.configClass = configClass;
    }

    public void serialize(T config) {
    }

    public T deserialize() {
        return this.createDefault();
    }

    public T createDefault() {
        return (T)(Object)Utils.constructUnsafely(this.configClass);
    }
}
