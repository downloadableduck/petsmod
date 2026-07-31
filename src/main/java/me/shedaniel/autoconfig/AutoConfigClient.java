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

import me.shedaniel.autoconfig.gui.ConfigScreenProvider;
import me.shedaniel.autoconfig.gui.DefaultGuiProviders;
import me.shedaniel.autoconfig.gui.DefaultGuiTransformers;
import me.shedaniel.autoconfig.gui.registry.ComposedGuiRegistryAccess;
import me.shedaniel.autoconfig.gui.registry.DefaultGuiRegistryAccess;
import me.shedaniel.autoconfig.gui.registry.GuiRegistry;
import net.minecraft.client.gui.screens.Screen;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class AutoConfigClient {
    private static final Map<Class<? extends ConfigData>, GuiRegistry> guiRegistries = new HashMap<>();
    
    public static <T extends ConfigData> GuiRegistry getGuiRegistry(Class<T> configClass) {
        return guiRegistries.computeIfAbsent(configClass, n -> new GuiRegistry());
    }
    
    public static <T extends ConfigData> Supplier<Screen> getConfigScreen(Class<T> configClass, Screen parent) {
        return new ConfigScreenProvider<>(
                (ConfigManager<T>) AutoConfig.getConfigHolder(configClass),
                new ComposedGuiRegistryAccess(
                        getGuiRegistry(configClass),
                        AutoConfigClient.defaultGuiRegistry,
                        new DefaultGuiRegistryAccess()
                ),
                parent
        );
    }
    
    private static final GuiRegistry defaultGuiRegistry =
            DefaultGuiTransformers.apply(DefaultGuiProviders.apply(new GuiRegistry()));
}
