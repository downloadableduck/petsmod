//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.autoconfig.serializer;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.util.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

public class Toml4jConfigSerializer<T extends ConfigData> implements ConfigSerializer<T> {
    private final Config definition;
    private final Class<T> configClass;
    private final TomlWriter tomlWriter;

    public Toml4jConfigSerializer(Config definition, Class<T> configClass, TomlWriter tomlWriter) {
        this.definition = definition;
        this.configClass = configClass;
        this.tomlWriter = tomlWriter;
    }

    public Toml4jConfigSerializer(Config definition, Class<T> configClass) {
        this(definition, configClass, new TomlWriter());
    }

    private Path getConfigPath() {
        return Utils.getConfigFolder().resolve(this.definition.name() + ".toml");
    }

    public void serialize(T config) throws ConfigSerializer.SerializationException {
        Path configPath = this.getConfigPath();

        try {
            Files.createDirectories(configPath.getParent());
            this.tomlWriter.write(config, configPath.toFile());
        } catch (IOException var4) {
            IOException e = var4;
            throw new ConfigSerializer.SerializationException(e);
        }
    }

    public T deserialize() throws ConfigSerializer.SerializationException {
        Path configPath = this.getConfigPath();
        if (Files.exists(configPath, new LinkOption[0])) {
            try {
                return (T)(Object)(new Toml()).read(configPath.toFile()).to(this.configClass);
            } catch (IllegalStateException var3) {
                IllegalStateException e = var3;
                throw new ConfigSerializer.SerializationException(e);
            }
        } else {
            return this.createDefault();
        }
    }

    public T createDefault() {
        return (T)(Object)Utils.constructUnsafely(this.configClass);
    }
}
