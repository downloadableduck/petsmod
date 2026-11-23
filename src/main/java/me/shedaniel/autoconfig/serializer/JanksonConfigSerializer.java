//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.autoconfig.serializer;

import blue.endless.jankson.Jankson;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.util.Utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

public class JanksonConfigSerializer<T extends ConfigData> implements ConfigSerializer<T> {
    private final Config definition;
    private final Class<T> configClass;
    private final Jankson jankson;

    public JanksonConfigSerializer(Config definition, Class<T> configClass, Jankson jankson) {
        this.definition = definition;
        this.configClass = configClass;
        this.jankson = jankson;
    }

    public JanksonConfigSerializer(Config definition, Class<T> configClass) {
        this(definition, configClass, Jankson.builder().build());
    }

    private Path getConfigPath() {
        return Utils.getConfigFolder().resolve(this.definition.name() + ".json5");
    }

    public void serialize(T config) throws SerializationException {
        Path configPath = this.getConfigPath();

        try {
            Files.createDirectories(configPath.getParent());
            BufferedWriter writer = Files.newBufferedWriter(configPath);
            writer.write(this.jankson.toJson(config).toJson(true, true));
            writer.close();
        } catch (IOException var4) {
            IOException e = var4;
            throw new SerializationException(e);
        }
    }

    public T deserialize() throws SerializationException {
        Path configPath = this.getConfigPath();
        if (Files.exists(configPath, new LinkOption[0])) {
            try {
                return (T)(Object)this.jankson.fromJson(this.jankson.load(this.getConfigPath().toFile()), this.configClass);
            } catch (Throwable var3) {
                Throwable e = var3;
                throw new SerializationException(e);
            }
        } else {
            return this.createDefault();
        }
    }

    public T createDefault() {
        return (T)(Object)Utils.constructUnsafely(this.configClass);
    }
}
