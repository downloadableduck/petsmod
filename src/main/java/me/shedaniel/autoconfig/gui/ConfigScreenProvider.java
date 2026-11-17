//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.autoconfig.gui;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.ConfigManager;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.gui.registry.api.GuiRegistryAccess;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.minecraft.client.gui.screen.Screen;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ConfigScreenProvider<T extends ConfigData> implements Supplier<Screen> {
    private static final String TRANSPARENT_BACKGROUND = "cloth-config2:transparent";
    private final ConfigManager<T> manager;
    private final GuiRegistryAccess registry;
    private final Screen parent;
    private Function<ConfigManager<T>, String> i18nFunction = (managerx) -> {
        return String.format("text.autoconfig.%s", managerx.getDefinition().name());
    };
    private Function<ConfigBuilder, Screen> buildFunction = ConfigBuilder::build;
    private BiFunction<String, Field, String> optionFunction = (baseI13n, field) -> {
        return String.format("%s.option.%s", baseI13n, field.getName());
    };
    private BiFunction<String, String, String> categoryFunction = (baseI13n, categoryName) -> {
        return String.format("%s.category.%s", baseI13n, categoryName);
    };

    public ConfigScreenProvider(ConfigManager<T> manager, GuiRegistryAccess registry, Screen parent) {
        this.manager = manager;
        this.registry = registry;
        this.parent = parent;
    }

    /** @deprecated */
    @Deprecated
    public void setI13nFunction(Function<ConfigManager<T>, String> i18nFunction) {
        this.i18nFunction = i18nFunction;
    }

    /** @deprecated */
    @Deprecated
    public void setBuildFunction(Function<ConfigBuilder, Screen> buildFunction) {
        this.buildFunction = buildFunction;
    }

    /** @deprecated */
    @Deprecated
    public void setCategoryFunction(BiFunction<String, String, String> categoryFunction) {
        this.categoryFunction = categoryFunction;
    }

    /** @deprecated */
    @Deprecated
    public void setOptionFunction(BiFunction<String, Field, String> optionFunction) {
        this.optionFunction = optionFunction;
    }

    public Screen get() {
        T config = this.manager.getConfig();
        T defaults = this.manager.getSerializer().createDefault();
        String i18n = (String)this.i18nFunction.apply(this.manager);
        ConfigBuilder var10000 = ConfigBuilder.create().setParentScreen(this.parent).setTitle(String.format("%s.title", i18n));
        ConfigManager var10001 = this.manager;
        Objects.requireNonNull(var10001);
        ConfigBuilder builder = var10000.setSavingRunnable(var10001::save);
        Class<T> configClass = this.manager.getConfigClass();
        if (configClass.isAnnotationPresent(Config.Gui.Background.class)) {
            String bg = ((Config.Gui.Background)configClass.getAnnotation(Config.Gui.Background.class)).value();
            String bgId = null;

            try {
                bgId = bg;
            } catch (Exception var9) {
                Exception e = var9;
                e.printStackTrace();
            }

            if ("cloth-config2:transparent".equals(bgId)) {
                builder.transparentBackground();
            } else {
                builder.setDefaultBackgroundTexture(bgId);
            }
        }

        Map<String, String> categoryBackgrounds = (Map)Arrays.stream((Config.Gui.CategoryBackground[])configClass.getAnnotationsByType(Config.Gui.CategoryBackground.class)).collect(Collectors.toMap(Config.Gui.CategoryBackground::category, (ann) -> {
            return ann.background();
        }));
        Map<ConfigCategory, List<Field>> grouped = Arrays.stream(configClass.getDeclaredFields()).collect(Collectors.groupingBy((field) -> {
            return this.getOrCreateCategoryForField(field, builder, categoryBackgrounds, i18n);
        }, LinkedHashMap::new, Collectors.toList()));
        grouped.forEach((key, value) -> {
            for (Field field : value) {
                String optionI13n = (String)this.optionFunction.apply(i18n, field);
                List entries = this.registry.getAndTransform(optionI13n, field, config, defaults, this.registry);
                if (entries != null) {
                    for (Object e : entries) {
                        key.addEntry((me.shedaniel.clothconfig2.api.AbstractConfigListEntry)e);
                    }
                }
            }
        });
        return (Screen)this.buildFunction.apply(builder);
    }

    private ConfigCategory getOrCreateCategoryForField(Field field, ConfigBuilder screenBuilder, Map<String, String> backgroundMap, String baseI13n) {
        String categoryName = "default";
        if (field.isAnnotationPresent(ConfigEntry.Category.class)) {
            categoryName = ((ConfigEntry.Category)field.getAnnotation(ConfigEntry.Category.class)).value();
        }

        String categoryKey = (String)this.categoryFunction.apply(baseI13n, categoryName);
        if (!screenBuilder.hasCategory(categoryKey)) {
            ConfigCategory category = screenBuilder.getOrCreateCategory(categoryKey);
            if (backgroundMap.containsKey(categoryName)) {
                category.setCategoryBackground((String)backgroundMap.get(categoryName));
            }

            return category;
        } else {
            return screenBuilder.getOrCreateCategory(categoryKey);
        }
    }
}
