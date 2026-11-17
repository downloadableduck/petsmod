//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.autoconfig;

import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.gui.registry.GuiRegistry;
import me.shedaniel.autoconfig.serializer.ConfigSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AutoConfig {
    public static final String MOD_ID = "autoconfig1u";
    private static final Map<Class<? extends ConfigData>, ConfigHolder<?>> holders = new HashMap();
    private static final Map<Class<? extends ConfigData>, GuiRegistry> guiRegistries = new HashMap();

    private AutoConfig() {
    }

    public static <T extends ConfigData> ConfigHolder<T> register(Class<T> configClass, ConfigSerializer.Factory<T> serializerFactory) {
        Objects.requireNonNull(configClass);
        Objects.requireNonNull(serializerFactory);
        if (holders.containsKey(configClass)) {
            throw new RuntimeException(String.format("Config '%s' already registered", configClass));
        } else {
            Config definition = (Config)configClass.getAnnotation(Config.class);
            if (definition == null) {
                throw new RuntimeException(String.format("No @Config annotation on %s!", configClass));
            } else {
                ConfigSerializer<T> serializer = serializerFactory.create(definition, configClass);
                ConfigManager<T> manager = new ConfigManager(definition, configClass, serializer);
                holders.put(configClass, manager);
                return manager;
            }
        }
    }

    public static <T extends ConfigData> ConfigHolder<T> getConfigHolder(Class<T> configClass) {
        Objects.requireNonNull(configClass);
        if (holders.containsKey(configClass)) {
            return (ConfigHolder)holders.get(configClass);
        } else {
            throw new RuntimeException(String.format("Config '%s' has not been registered", configClass));
        }
    }

    public static <T extends ConfigData> GuiRegistry getGuiRegistry(Class<T> configClass) {
        return (GuiRegistry)guiRegistries.computeIfAbsent(configClass, (n) -> {
            return new GuiRegistry();
        });
    }
}
