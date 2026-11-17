//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.autoconfig;

import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.event.ConfigSerializeEvent;
import me.shedaniel.autoconfig.serializer.ConfigSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Internal
public class ConfigManager<T extends ConfigData> implements ConfigHolder<T> {
    private final Logger logger = LogManager.getLogger();
    private final Config definition;
    private final Class<T> configClass;
    private final ConfigSerializer<T> serializer;
    private final List<ConfigSerializeEvent.Save<T>> saveEvent = new ArrayList();
    private final List<ConfigSerializeEvent.Load<T>> loadEvent = new ArrayList();
    private T config;

    ConfigManager(Config definition, Class<T> configClass, ConfigSerializer<T> serializer) {
        this.definition = definition;
        this.configClass = configClass;
        this.serializer = serializer;
        if (this.load()) {
            this.save();
        }

    }

    public Config getDefinition() {
        return this.definition;
    }

    public @NotNull Class<T> getConfigClass() {
        return this.configClass;
    }

    public ConfigSerializer<T> getSerializer() {
        return this.serializer;
    }

    public void save() {
        Iterator var1 = this.saveEvent.iterator();

        while(var1.hasNext()) {
            ConfigSerializeEvent.Save<T> save = (ConfigSerializeEvent.Save)var1.next();
            int result = save.onSave(this, this.config);
            if (result == 0) {
                return;
            }

            if (result != 1) {
                break;
            }
        }

        try {
            this.serializer.serialize(this.config);
        } catch (ConfigSerializer.SerializationException var4) {
            ConfigSerializer.SerializationException e = var4;
            this.logger.error("Failed to save config '{}'", this.configClass, e);
        }

    }

    public boolean load() {
        try {
            T deserialized = this.serializer.deserialize();
            Iterator var2 = this.loadEvent.iterator();

            while(var2.hasNext()) {
                ConfigSerializeEvent.Load<T> load = (ConfigSerializeEvent.Load)var2.next();
                int result = load.onLoad(this, deserialized);
                if (result == 0) {
                    this.config = this.serializer.createDefault();
                    this.config.validatePostLoad();
                    return false;
                }

                if (result != 1) {
                    break;
                }
            }

            this.config = deserialized;
            this.config.validatePostLoad();
            return true;
        } catch (ConfigData.ValidationException | ConfigSerializer.SerializationException var5) {
            Exception e = var5;
            this.logger.error("Failed to load config '{}', using default!", this.configClass, e);
            this.resetToDefault();
            return false;
        }
    }

    public T getConfig() {
        return this.config;
    }

    public void setConfig(T config) {
        this.config = config;
    }

    public void registerLoadListener(ConfigSerializeEvent.Load<T> load) {
        this.loadEvent.add(load);
    }

    public void resetToDefault() {
        this.config = this.serializer.createDefault();

        try {
            this.config.validatePostLoad();
        } catch (ConfigData.ValidationException var2) {
            ConfigData.ValidationException v = var2;
            throw new RuntimeException("result of createDefault() was invalid!", v);
        }
    }

    public void registerSaveListener(ConfigSerializeEvent.Save<T> save) {
        this.saveEvent.add(save);
    }
}
