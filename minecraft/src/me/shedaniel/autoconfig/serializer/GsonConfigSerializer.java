/*
 * This file is part of Cloth Config.
 * Copyright (C) 2020 - 2021 shedaniel
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package me.shedaniel.autoconfig.serializer;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.util.Utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * This serializer serializes configs into Json files.
 */
@SuppressWarnings({"unused", "FieldMayBeFinal"})
public class GsonConfigSerializer<T extends ConfigData> implements ConfigSerializer<T> {

    private Config definition;
    private Class<T> configClass;

    @SuppressWarnings("WeakerAccess")
    public GsonConfigSerializer(Config definition, Class<T> configClass) {
        this.definition = definition;
        this.configClass = configClass;
    }

    private Path getConfigPath() {
        return Utils.getConfigFolder().resolve(definition.name() + ".json");
    }

    @Override
    public void serialize(T config) throws SerializationException {
        Path configPath = getConfigPath();
        try {
            Files.createDirectories(configPath.getParent());
            BufferedWriter writer = Files.newBufferedWriter(configPath, Charset.forName("UTF-8"));
            writer.write(JsonCodec.toJson(config, true));
            writer.close();
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    @Override
    public T deserialize() throws SerializationException {
        Path configPath = getConfigPath();
        if (Files.exists(configPath)) {
            try {
                byte[] bytes = Files.readAllBytes(configPath);
                String content = new String(bytes, Charset.forName("UTF-8"));
                return JsonCodec.fromJson(content, configClass);
            } catch (Exception e) {
                throw new SerializationException(e);
            }
        } else {
            return createDefault();
        }
    }

    @Override
    public T createDefault() {
        return Utils.constructUnsafely(configClass);
    }
}
