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

package me.shedaniel.autoconfig.gui;

import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.gui.registry.GuiRegistry;
import me.shedaniel.forge.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.forge.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.forge.clothconfig2.gui.entries.TextListEntry;
import me.shedaniel.forge.clothconfig2.gui.entries.TooltipListEntry;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DefaultGuiTransformers {

    private static final ConfigEntryBuilder ENTRY_BUILDER = ConfigEntryBuilder.create();

    private DefaultGuiTransformers() {
    }

    public static GuiRegistry apply(GuiRegistry registry) {

        registry.registerAnnotationTransformer(
                (guis, i18n, field, config, defaults, guiProvider) -> guis.stream()
                        .peek(gui -> {
                            if (!(gui instanceof TextListEntry)) {
                                ConfigEntry.Gui.Tooltip tooltip = field.getAnnotation(ConfigEntry.Gui.Tooltip.class);
                                if (tooltip.count() == 0) {
                                    tryRemoveTooltip(gui);
                                } else if (tooltip.count() == 1) {
                                    tryApplyTooltip(
                                            gui,
                                            new ITextComponent[]{
                                                    new TextComponentTranslation(String.format("%s.%s", i18n, "@Tooltip"))
                                            }
                                    );
                                } else {
                                    tryApplyTooltip(
                                            gui, IntStream.range(0, tooltip.count()).boxed()
                                                    .map(i -> String.format("%s.%s[%d]", i18n, "@Tooltip", i))
                                                    .map(TextComponentTranslation::new)
                                                    .toArray(ITextComponent[]::new)
                                    );
                                }
                            }
                        })
                        .collect(Collectors.toList()),
                ConfigEntry.Gui.Tooltip.class
        );

        registry.registerAnnotationTransformer(
                (guis, i18n, field, config, defaults, guiProvider) -> guis.stream()
                        .peek(gui -> {
                            if (!(gui instanceof TextListEntry)) {
                                tryRemoveTooltip(gui);
                            }
                        })
                        .collect(Collectors.toList()),
                ConfigEntry.Gui.NoTooltip.class
        );

        registry.registerAnnotationTransformer(
                (guis, i18n, field, config, defaults, guiProvider) -> {
                    ArrayList<AbstractConfigListEntry> ret = new ArrayList<>(guis);
                    String text = String.format("%s.%s", i18n, "@PrefixText");
                    TextListEntry element = ENTRY_BUILDER.startTextDescription(new TextComponentTranslation(text).getUnformattedComponentText()).build();
                    String s = new TextComponentTranslation(i18n).getUnformattedComponentText().toLowerCase(Locale.ROOT);
                    if (!s.isEmpty()) {
                        //element.appendSearchTags(Lists.newArrayList(s.split(" ")));
                    }
                    ret.add(0, element);
                    return Collections.unmodifiableList(ret);
                },
                ConfigEntry.Gui.PrefixText.class
        );

        registry.registerAnnotationTransformer(
                (guis, i18n, field, config, defaults, guiProvider) -> {
                    for (AbstractConfigListEntry gui : guis) {
                        gui.setRequiresRestart(field.getAnnotation(ConfigEntry.Gui.RequiresRestart.class).value());
                    }
                    return guis;
                },
                ConfigEntry.Gui.RequiresRestart.class
        );

        return registry;
    }

    private static void tryApplyTooltip(AbstractConfigListEntry gui, ITextComponent[] text) {
        if (gui instanceof TooltipListEntry) {
            TooltipListEntry tooltipGui = (TooltipListEntry) gui;
            tooltipGui.setTooltipSupplier(() -> Optional.of(text));
        }
    }

    private static void tryRemoveTooltip(AbstractConfigListEntry gui) {
        if (gui instanceof TooltipListEntry) {
            TooltipListEntry tooltipGui = (TooltipListEntry) gui;
            tooltipGui.setTooltipSupplier(() -> Optional.empty());
        }
    }
}
