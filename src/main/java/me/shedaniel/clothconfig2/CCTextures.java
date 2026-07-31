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

package me.shedaniel.clothconfig2;

import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class CCTextures {
    private static final String MOD_ID = "cloth-config2";
    public static final Identifier VERTICAL_HEADER_SEPARATOR = Identifier.fromNamespaceAndPath(MOD_ID, "textures/gui/vertical_header_separator.png");
    public static final Identifier VERTICAL_FOOTER_SEPARATOR = Identifier.fromNamespaceAndPath(MOD_ID, "textures/gui/vertical_footer_separator.png");
    public static final Identifier CONFIG = Identifier.fromNamespaceAndPath(MOD_ID, "textures/gui/cloth_config.png");
}
