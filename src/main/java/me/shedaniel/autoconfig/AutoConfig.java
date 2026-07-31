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

package me.shedaniel.autoconfig;

import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.ConfigSerializer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @see AutoConfigClient
 */
public class AutoConfig {
    public static final String MOD_ID = "autoconfig1u";
    
    private static final Map<Class<? extends ConfigData>, ConfigHolder<?>> holders = new HashMap<>();
    
    private AutoConfig() {
    }
    
    public static <T extends ConfigData> ConfigHolder<T> register(
            Class<T> configClass,
            ConfigSerializer.Factory<T> serializerFactory
    ) {
        Objects.requireNonNull(configClass);
        Objects.requireNonNull(serializerFactory);
        
        if (holders.containsKey(configClass)) {
            throw new RuntimeException(String.format("Config '%s' already registered", configClass));
        }
        
        Config definition = configClass.getAnnotation(Config.class);
        
        if (definition == null) {
            throw new RuntimeException(String.format("No @Config annotation on %s!", configClass));
        }
        
        ConfigSerializer<T> serializer = serializerFactory.create(definition, configClass);
        ConfigManager<T> manager = new ConfigManager<>(definition, configClass, serializer);
        holders.put(configClass, manager);
        
        return manager;
    }
    
    public static <T extends ConfigData> ConfigHolder<T> getConfigHolder(Class<T> configClass) {
        Objects.requireNonNull(configClass);
        if (holders.containsKey(configClass)) {
            return (ConfigHolder<T>) holders.get(configClass);
        } else {
            throw new RuntimeException(String.format("Config '%s' has not been registered", configClass));
        }
    }
}
