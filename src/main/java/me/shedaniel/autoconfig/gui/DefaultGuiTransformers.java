//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.autoconfig.gui;

import blue.endless.jankson.Comment;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.gui.registry.GuiRegistry;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.gui.entries.TextListEntry;
import me.shedaniel.clothconfig2.gui.entries.TooltipListEntry;
import net.minecraft.client.resource.language.I18n;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DefaultGuiTransformers {
    private static final ConfigEntryBuilder ENTRY_BUILDER = ConfigEntryBuilder.create();

    private DefaultGuiTransformers() {
    }

    public static GuiRegistry apply(GuiRegistry registry) {
        registry.registerAnnotationTransformer((guis, i18n, field, config, defaults, guiProvider) -> {
            return (List)guis.stream().peek((gui) -> {
                if (!(gui instanceof TextListEntry)) {
                    ConfigEntry.Gui.Tooltip tooltip = (ConfigEntry.Gui.Tooltip)field.getAnnotation(ConfigEntry.Gui.Tooltip.class);
                    if (tooltip.count() == 0) {
                        tryRemoveTooltip(gui);
                    } else if (tooltip.count() == 1) {
                        tryApplyTooltip(gui, new String[]{I18n.getTranslation(String.format("%s.%s", i18n, "@Tooltip"))});
                    } else {
                        tryApplyTooltip(gui, (String[])IntStream.range(0, tooltip.count()).boxed().map((i) -> {
                            return String.format("%s.%s[%d]", i18n, "@Tooltip", i);
                        }).map(I18n::getTranslation).toArray((x$0) -> {
                            return new String[x$0];
                        }));
                    }
                }

            }).collect(Collectors.toList());
        }, ConfigEntry.Gui.Tooltip.class);
        registry.registerAnnotationTransformer((guis, i18n, field, config, defaults, guiProvider) -> {
            return (List)guis.stream().peek((gui) -> {
                if (!(gui instanceof TextListEntry)) {
                    Comment tooltip = (Comment)field.getAnnotation(Comment.class);
                    String[] text = new String[]{I18n.getTranslation(tooltip.value())};
                    tryApplyTooltip(gui, text);
                }

            }).collect(Collectors.toList());
        }, (field) -> {
            return !field.isAnnotationPresent(ConfigEntry.Gui.Tooltip.class);
        }, Comment.class);
        registry.registerAnnotationTransformer((guis, i18n, field, config, defaults, guiProvider) -> {
            return (List)guis.stream().peek((gui) -> {
                if (!(gui instanceof TextListEntry)) {
                    tryRemoveTooltip(gui);
                }

            }).collect(Collectors.toList());
        }, ConfigEntry.Gui.NoTooltip.class);
        registry.registerAnnotationTransformer((guis, i18n, field, config, defaults, guiProvider) -> {
            ArrayList<AbstractConfigListEntry> ret = new ArrayList(guis);
            String text = String.format("%s.%s", i18n, "@PrefixText");
            TextListEntry element = ENTRY_BUILDER.startTextDescription(text).build();
            String s = I18n.getTranslation(i18n).toLowerCase(Locale.ROOT);
            if (!s.isEmpty()) {
            }

            ret.add(0, element);
            return Collections.unmodifiableList(ret);
        }, ConfigEntry.Gui.PrefixText.class);
        registry.registerAnnotationTransformer((guis, i18n, field, config, defaults, guiProvider) -> {
            Iterator i$ = guis.iterator();

            while(i$.hasNext()) {
                AbstractConfigListEntry gui = (AbstractConfigListEntry)i$.next();
                gui.setRequiresRestart(((ConfigEntry.Gui.RequiresRestart)field.getAnnotation(ConfigEntry.Gui.RequiresRestart.class)).value());
            }

            return guis;
        }, ConfigEntry.Gui.RequiresRestart.class);
        return registry;
    }

    private static void tryApplyTooltip(AbstractConfigListEntry gui, String[] text) {
        if (gui instanceof TooltipListEntry) {
            TooltipListEntry tooltipGui = (TooltipListEntry)gui;
            tooltipGui.setTooltipSupplier(() -> {
                return Optional.of(text);
            });
        }

    }

    private static void tryRemoveTooltip(AbstractConfigListEntry gui) {
        if (gui instanceof TooltipListEntry) {
            TooltipListEntry tooltipGui = (TooltipListEntry)gui;
            tooltipGui.setTooltipSupplier(() -> {
                return Optional.empty();
            });
        }

    }
}
