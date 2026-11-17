//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.autoconfig.serializer;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

public interface ConfigSerializer<T extends ConfigData> {
    void serialize(T var1) throws SerializationException;

    T deserialize() throws SerializationException;

    T createDefault();

    public static class SerializationException extends Exception {
        public SerializationException(Throwable cause) {
            super(cause);
        }
    }

    @FunctionalInterface
    public interface Factory<T extends ConfigData> {
        ConfigSerializer<T> create(Config var1, Class<T> var2);
    }
}
