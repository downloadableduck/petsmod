//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.autoconfig.gui;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.EnumHandler.EnumDisplayOption;
import me.shedaniel.autoconfig.gui.registry.GuiRegistry;
import me.shedaniel.autoconfig.gui.registry.api.GuiRegistryAccess;
import me.shedaniel.autoconfig.util.Utils;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.compat.GuiEventListener;
import me.shedaniel.clothconfig2.gui.entries.SelectionListEntry;
import me.shedaniel.clothconfig2.impl.builders.DropdownMenuBuilder.CellCreatorBuilder;
import me.shedaniel.clothconfig2.impl.builders.DropdownMenuBuilder.TopCellElementBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resource.language.I18n;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class DefaultGuiProviders {
    private static final ConfigEntryBuilder ENTRY_BUILDER = ConfigEntryBuilder.create();
    private static final Function<Enum<?>, String> DEFAULT_NAME_PROVIDER = (t) -> {
        return t instanceof SelectionListEntry.Translatable ? ((SelectionListEntry.Translatable)t).getKey() : t.toString();
    };

    private DefaultGuiProviders() {
    }

    public static GuiRegistry apply(GuiRegistry registry, Screen parent) {
        registry.registerAnnotationProvider((i18n, field, config, defaults, guiProvider) -> {
            return Collections.emptyList();
        }, ConfigEntry.Gui.Excluded.class);
        registry.registerAnnotationProvider((i18n, field, config, defaults, guiProvider) -> {
            ConfigEntry.BoundedDiscrete bounds = (ConfigEntry.BoundedDiscrete)field.getAnnotation(ConfigEntry.BoundedDiscrete.class);
            return Collections.singletonList(ENTRY_BUILDER.startIntSlider(i18n, (Integer)Utils.getUnsafely(field, config, 0), (int)bounds.min(), (int)bounds.max()).setDefaultValue(() -> {
                return (Integer)Utils.getUnsafely(field, defaults);
            }).setSaveConsumer((newValue) -> {
                Utils.setUnsafely(field, config, newValue);
            }).build());
        }, (field) -> {
            return field.getType() == Integer.TYPE || field.getType() == Integer.class;
        }, ConfigEntry.BoundedDiscrete.class);
        registry.registerAnnotationProvider((i18n, field, config, defaults, guiProvider) -> {
            ConfigEntry.BoundedDiscrete bounds = (ConfigEntry.BoundedDiscrete)field.getAnnotation(ConfigEntry.BoundedDiscrete.class);
            return Collections.singletonList(ENTRY_BUILDER.startLongSlider(i18n, (Long)Utils.getUnsafely(field, config, 0L), bounds.min(), bounds.max()).setDefaultValue(() -> {
                return (Long)Utils.getUnsafely(field, defaults);
            }).setSaveConsumer((newValue) -> {
                Utils.setUnsafely(field, config, newValue);
            }).build());
        }, (field) -> {
            return field.getType() == Long.TYPE || field.getType() == Long.class;
        }, ConfigEntry.BoundedDiscrete.class);
        registry.registerAnnotationProvider((i18n, field, config, defaults, guiProvider) -> {
            ConfigEntry.ColorPicker colorPicker = (ConfigEntry.ColorPicker)field.getAnnotation(ConfigEntry.ColorPicker.class);
            return Collections.singletonList(ENTRY_BUILDER.startTextDescription(i18n).build());
        }, (field) -> {
            return field.getType() == Integer.TYPE || field.getType() == Integer.class;
        }, ConfigEntry.ColorPicker.class);
        registry.registerAnnotationProvider(DefaultGuiProviders::getChildren, (field) -> {
            return !field.getType().isPrimitive();
        }, ConfigEntry.Gui.TransitiveObject.class);
        registry.registerAnnotationProvider((i18n, field, config, defaults, guiProvider) -> {
            return Collections.singletonList(ENTRY_BUILDER.startSubCategory(i18n, getChildren(i18n, field, config, defaults, guiProvider)).setExpended(((ConfigEntry.Gui.CollapsibleObject)field.getAnnotation(ConfigEntry.Gui.CollapsibleObject.class)).startExpanded()).build());
        }, (field) -> {
            return !field.getType().isPrimitive();
        }, ConfigEntry.Gui.CollapsibleObject.class);
        registry.registerPredicateProvider((i18n, field, config, defaults, guiProvider) -> {
            Object[] enumConstants = field.getType().getEnumConstants();
            Enum[] enums = new Enum[enumConstants.length];

            for(int i = 0; i < enumConstants.length; ++i) {
                enums[i] = (Enum)enumConstants[i];
            }

            return Collections.singletonList(ENTRY_BUILDER.startSelector(i18n, enums, (Enum)Utils.getUnsafely(field, config, (Enum)Utils.getUnsafely(field, defaults))).setDefaultValue(() -> {
                return (Enum)Utils.getUnsafely(field, defaults);
            }).setSaveConsumer((newValue) -> {
                Utils.setUnsafely(field, config, newValue);
            }).build());
        }, (field) -> {
            return field.getType().isEnum() && field.isAnnotationPresent(ConfigEntry.Gui.EnumHandler.class) && ((ConfigEntry.Gui.EnumHandler)field.getAnnotation(ConfigEntry.Gui.EnumHandler.class)).option() == EnumDisplayOption.BUTTON;
        });
        registry.registerPredicateProvider((i18n, field, config, defaults, guiProvider) -> {
            List<Enum<?>> enums = Arrays.asList((Enum[])field.getType().getEnumConstants());
            return Collections.singletonList(ENTRY_BUILDER.startDropdownMenu(i18n, TopCellElementBuilder.of(parent, (Enum)Utils.getUnsafely(field, config, (Enum)Utils.getUnsafely(field, defaults)), (str) -> {
                String s = str;
                Iterator i$ = enums.iterator();

                Enum constant;
                do {
                    if (!i$.hasNext()) {
                        return null;
                    }

                    constant = (Enum)i$.next();
                } while(!((String)DEFAULT_NAME_PROVIDER.apply(constant)).equals(s));

                return constant;
            }, DEFAULT_NAME_PROVIDER), CellCreatorBuilder.of(DEFAULT_NAME_PROVIDER)).setSelections(enums).setDefaultValue(() -> {
                return (Enum)Utils.getUnsafely(field, defaults);
            }).setSaveConsumer((newValue) -> {
                Utils.setUnsafely(field, config, newValue);
            }).build());
        }, (field) -> {
            return field.getType().isEnum();
        });
        registry.registerPredicateProvider((i18n, field, config, defaults, registry1) -> {
            return Collections.singletonList(ENTRY_BUILDER.startIntList(i18n, (List)Utils.getUnsafely(field, config)).setDefaultValue(() -> {
                return (List)Utils.getUnsafely(field, defaults);
            }).setSaveConsumer((newValue) -> {
                Utils.setUnsafely(field, config, newValue);
            }).build());
        }, isListOfType(Integer.class));
        registry.registerPredicateProvider((i18n, field, config, defaults, registry1) -> {
            return Collections.singletonList(ENTRY_BUILDER.startLongList(i18n, (List)Utils.getUnsafely(field, config)).setDefaultValue(() -> {
                return (List)Utils.getUnsafely(field, defaults);
            }).setSaveConsumer((newValue) -> {
                Utils.setUnsafely(field, config, newValue);
            }).build());
        }, isListOfType(Long.class));
        registry.registerPredicateProvider((i18n, field, config, defaults, registry1) -> {
            return Collections.singletonList(ENTRY_BUILDER.startFloatList(i18n, (List)Utils.getUnsafely(field, config)).setDefaultValue(() -> {
                return (List)Utils.getUnsafely(field, defaults);
            }).setSaveConsumer((newValue) -> {
                Utils.setUnsafely(field, config, newValue);
            }).build());
        }, isListOfType(Float.class));
        registry.registerPredicateProvider((i18n, field, config, defaults, registry1) -> {
            return Collections.singletonList(ENTRY_BUILDER.startDoubleList(i18n, (List)Utils.getUnsafely(field, config)).setDefaultValue(() -> {
                return (List)Utils.getUnsafely(field, defaults);
            }).setSaveConsumer((newValue) -> {
                Utils.setUnsafely(field, config, newValue);
            }).build());
        }, isListOfType(Double.class));
        registry.registerPredicateProvider((i18n, field, config, defaults, registry1) -> {
            return Collections.singletonList(ENTRY_BUILDER.startStrList(i18n, (List)Utils.getUnsafely(field, config)).setDefaultValue(() -> {
                return (List)Utils.getUnsafely(field, defaults);
            }).setSaveConsumer((newValue) -> {
                Utils.setUnsafely(field, config, newValue);
            }).build());
        }, isListOfType(String.class));
        registry.registerPredicateProvider((i18n, field, config, defaults, registry1) -> {
            List<Object> configValue = (List)Utils.getUnsafely(field, config);
            Class<?> fieldTypeParam = (Class)((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0];
            Object defaultElemValue = Utils.constructUnsafely(fieldTypeParam);
            String remainingI13n = i18n.substring(0, i18n.indexOf(".option") + ".option".length());
            String classI13n = String.format("%s.%s", remainingI13n, fieldTypeParam.getSimpleName());
            return Collections.singletonList(new AbstractConfigListEntry("", false) {
                public Object getValue() {
                    return new Object();
                }

                public Optional getDefaultValue() {
                    return Optional.of(new Object());
                }

                public void save() {
                }

                public void render(int i, int i1, int i2, int i3, int i4, int i5, int i6, boolean b, float v) {
                }

                public List<? extends GuiEventListener> children() {
                    return ImmutableList.of();
                }
            });
        }, isNotListOfType(Integer.class, Long.class, Float.class, Double.class, String.class));
        registry.registerTypeProvider((i18n, field, config, defaults, guiProvider) -> {
            return Collections.singletonList(ENTRY_BUILDER.startBooleanToggle(i18n, (Boolean)Utils.getUnsafely(field, config, false)).setDefaultValue(() -> {
                return (Boolean)Utils.getUnsafely(field, defaults);
            }).setSaveConsumer((newValue) -> {
                Utils.setUnsafely(field, config, newValue);
            }).setYesNoTextSupplier((bool) -> {
                String key = i18n + ".boolean." + bool;
                String translate = I18n.getTranslation(key);
                return translate.equals(key) ? "text.cloth-config.boolean.value." + bool : translate;
            }).build());
        }, Boolean.TYPE, Boolean.class);
        registry.registerTypeProvider((i18n, field, config, defaults, guiProvider) -> {
            return Collections.singletonList(ENTRY_BUILDER.startIntField(i18n, (Integer)Utils.getUnsafely(field, config, 0)).setDefaultValue(() -> {
                return (Integer)Utils.getUnsafely(field, defaults);
            }).setSaveConsumer((newValue) -> {
                Utils.setUnsafely(field, config, newValue);
            }).build());
        }, Integer.TYPE, Integer.class);
        registry.registerTypeProvider((i18n, field, config, defaults, guiProvider) -> {
            return Collections.singletonList(ENTRY_BUILDER.startIntList(i18n, Lists.newArrayList((Integer[])Utils.getUnsafely(field, config, new Integer[0]))).setDefaultValue(() -> {
                return defaults == null ? null : Lists.newArrayList((Integer[])Utils.getUnsafely(field, defaults));
            }).setSaveConsumer((newValue) -> {
                Utils.setUnsafely(field, config, newValue.toArray(new Integer[0]));
            }).build());
        }, Integer[].class);
        registry.registerTypeProvider((i18n, field, config, defaults, guiProvider) -> {
            return Collections.singletonList(ENTRY_BUILDER.startIntList(i18n, Lists.newArrayList((Iterable)IntStream.of((int[])Utils.getUnsafely(field, config, new int[0])).boxed().collect(Collectors.toList()))).setDefaultValue(() -> {
                return defaults == null ? null : Lists.newArrayList(Arrays.asList(ArrayUtils.toObject((int[])Utils.getUnsafely(field, defaults))));
            }).setSaveConsumer((newValue) -> {
                Utils.setUnsafely(field, config, newValue.stream().mapToInt(Integer::intValue).toArray());
            }).build());
        }, int[].class);
        registry.registerTypeProvider((i18n, field, config, defaults, guiProvider) -> {
            return Collections.singletonList(ENTRY_BUILDER.startLongField(i18n, (Long)Utils.getUnsafely(field, config, 0L)).setDefaultValue(() -> {
                return (Long)Utils.getUnsafely(field, defaults);
            }).setSaveConsumer((newValue) -> {
                Utils.setUnsafely(field, config, newValue);
            }).build());
        }, Long.TYPE, Long.class);
        registry.registerTypeProvider((i18n, field, config, defaults, guiProvider) -> {
            return Collections.singletonList(ENTRY_BUILDER.startLongList(i18n, Lists.newArrayList((Long[])Utils.getUnsafely(field, config, new Long[0]))).setDefaultValue(() -> {
                return defaults == null ? null : Lists.newArrayList((Long[])Utils.getUnsafely(field, defaults));
            }).setSaveConsumer((newValue) -> {
                Utils.setUnsafely(field, config, newValue.toArray(new Long[0]));
            }).build());
        }, Long[].class);
        registry.registerTypeProvider((i18n, field, config, defaults, guiProvider) -> {
            return Collections.singletonList(ENTRY_BUILDER.startLongList(i18n, Lists.newArrayList((Iterable)LongStream.of((long[])Utils.getUnsafely(field, config, new long[0])).boxed().collect(Collectors.toList()))).setDefaultValue(() -> {
                return defaults == null ? null : Lists.newArrayList(Arrays.asList(ArrayUtils.toObject((long[])Utils.getUnsafely(field, defaults))));
            }).setSaveConsumer((newValue) -> {
                Utils.setUnsafely(field, config, newValue.stream().mapToLong(Long::longValue).toArray());
            }).build());
        }, long[].class);
        registry.registerTypeProvider((i18n, field, config, defaults, guiProvider) -> {
            return Collections.singletonList(ENTRY_BUILDER.startFloatField(i18n, (Float)Utils.getUnsafely(field, config, 0.0F)).setDefaultValue(() -> {
                return (Float)Utils.getUnsafely(field, defaults);
            }).setSaveConsumer((newValue) -> {
                Utils.setUnsafely(field, config, newValue);
            }).build());
        }, Float.TYPE, Float.class);
        registry.registerTypeProvider((i18n, field, config, defaults, guiProvider) -> {
            return Collections.singletonList(ENTRY_BUILDER.startFloatList(i18n, Lists.newArrayList((Float[])Utils.getUnsafely(field, config, new Float[0]))).setDefaultValue(() -> {
                return defaults == null ? null : Lists.newArrayList((Float[])Utils.getUnsafely(field, defaults));
            }).setSaveConsumer((newValue) -> {
                Utils.setUnsafely(field, config, newValue.toArray(new Float[0]));
            }).build());
        }, Float[].class);
        registry.registerTypeProvider((i18n, field, config, defaults, guiProvider) -> {
            return Collections.singletonList(ENTRY_BUILDER.startFloatList(i18n, Lists.newArrayList(Arrays.asList(ArrayUtils.toObject((float[])Utils.getUnsafely(field, config, new float[0]))))).setDefaultValue(() -> {
                return defaults == null ? null : Lists.newArrayList(Arrays.asList(ArrayUtils.toObject((float[])Utils.getUnsafely(field, defaults))));
            }).setSaveConsumer((newValue) -> {
                Utils.setUnsafely(field, config, ArrayUtils.toPrimitive((Float[])newValue.toArray(new Float[0])));
            }).build());
        }, float[].class);
        registry.registerTypeProvider((i18n, field, config, defaults, guiProvider) -> {
            return Collections.singletonList(ENTRY_BUILDER.startDoubleField(i18n, (Double)Utils.getUnsafely(field, config, 0.0)).setDefaultValue(() -> {
                return (Double)Utils.getUnsafely(field, defaults);
            }).setSaveConsumer((newValue) -> {
                Utils.setUnsafely(field, config, newValue);
            }).build());
        }, Double.TYPE, Double.class);
        registry.registerTypeProvider((i18n, field, config, defaults, guiProvider) -> {
            return Collections.singletonList(ENTRY_BUILDER.startDoubleList(i18n, Lists.newArrayList((Double[])Utils.getUnsafely(field, config, new Double[0]))).setDefaultValue(() -> {
                return defaults == null ? null : Lists.newArrayList((Double[])Utils.getUnsafely(field, defaults));
            }).setSaveConsumer((newValue) -> {
                Utils.setUnsafely(field, config, newValue.toArray(new Double[0]));
            }).build());
        }, Double[].class);
        registry.registerTypeProvider((i18n, field, config, defaults, guiProvider) -> {
            return Collections.singletonList(ENTRY_BUILDER.startDoubleList(i18n, Lists.newArrayList(Arrays.asList(ArrayUtils.toObject((double[])Utils.getUnsafely(field, config, new double[0]))))).setDefaultValue(() -> {
                return defaults == null ? null : Lists.newArrayList(Arrays.asList(ArrayUtils.toObject((double[])Utils.getUnsafely(field, defaults))));
            }).setSaveConsumer((newValue) -> {
                Utils.setUnsafely(field, config, ArrayUtils.toPrimitive((Double[])newValue.toArray(new Double[0])));
            }).build());
        }, double[].class);
        registry.registerTypeProvider((i18n, field, config, defaults, guiProvider) -> {
            return Collections.singletonList(ENTRY_BUILDER.startStrField(i18n, (String)Utils.getUnsafely(field, config, "")).setDefaultValue(() -> {
                return (String)Utils.getUnsafely(field, defaults);
            }).setSaveConsumer((newValue) -> {
                Utils.setUnsafely(field, config, newValue);
            }).build());
        }, String.class);
        registry.registerTypeProvider((i18n, field, config, defaults, guiProvider) -> {
            return Collections.singletonList(ENTRY_BUILDER.startStrList(i18n, Lists.newArrayList((String[])Utils.getUnsafely(field, config, new String[0]))).setDefaultValue(() -> {
                return defaults == null ? null : Lists.newArrayList((String[])Utils.getUnsafely(field, defaults));
            }).setSaveConsumer((newValue) -> {
                Utils.setUnsafely(field, config, newValue.toArray(new String[0]));
            }).build());
        }, String[].class);
        registry.registerPredicateProvider((i18n, field, config, defaults, registry1) -> {
            Object configValue = Utils.getUnsafely(field, config);
            List<Object> configValueAsList = new ArrayList(Array.getLength(configValue));

            for(int i = 0; i < Array.getLength(configValue); ++i) {
                configValueAsList.add(Array.get(configValue, i));
            }

            Class<?> fieldTypeParam = field.getType().getComponentType();
            Object defaultElemValue = Utils.constructUnsafely(fieldTypeParam);
            String remainingI13n = i18n.substring(0, i18n.indexOf(".option") + ".option".length());
            String classI13n = String.format("%s.%s", remainingI13n, fieldTypeParam.getSimpleName());
            return Collections.singletonList(new AbstractConfigListEntry("", false) {
                public Object getValue() {
                    return null;
                }

                public Optional getDefaultValue() {
                    return Optional.empty();
                }

                public void save() {
                }

                public void render(int i, int i1, int i2, int i3, int i4, int i5, int i6, boolean b, float v) {
                }

                public List<? extends GuiEventListener> children() {
                    return ImmutableList.of();
                }
            });
        }, (field) -> {
            return field.getType().isArray() && field.getType() != String[].class && field.getType() != int[].class && field.getType() != Integer[].class && field.getType() != long[].class && field.getType() != Long[].class && field.getType() != float[].class && field.getType() != Float[].class && field.getType() != double[].class && field.getType() != Double[].class;
        });
        return registry;
    }

    private static List<AbstractConfigListEntry> getChildren(String i18n, Field field, Object config, Object defaults, GuiRegistryAccess guiProvider) {
        return getChildren(i18n, field.getType(), Utils.getUnsafely(field, config), Utils.getUnsafely(field, defaults), guiProvider);
    }

    private static List<AbstractConfigListEntry> getChildren(String i18n, Class<?> fieldType, Object iConfig, Object iDefaults, GuiRegistryAccess guiProvider) {
        return (List)Arrays.stream(fieldType.getDeclaredFields()).map((iField) -> {
            String iI13n = String.format("%s.%s", i18n, iField.getName());
            return guiProvider.getAndTransform(iI13n, iField, iConfig, iDefaults, guiProvider);
        }).filter(Objects::nonNull).flatMap(Collection::stream).collect(Collectors.toList());
    }

    private static Predicate<Field> isListOfType(Type... types) {
        return (field) -> {
            if (List.class.isAssignableFrom(field.getType()) && field.getGenericType() instanceof ParameterizedType) {
                Type[] args = ((ParameterizedType)field.getGenericType()).getActualTypeArguments();
                return args.length == 1 && Stream.of(types).anyMatch((type) -> {
                    return Objects.equals(args[0], type);
                });
            } else {
                return false;
            }
        };
    }

    private static Predicate<Field> isNotListOfType(Type... types) {
        return (field) -> {
            if (List.class.isAssignableFrom(field.getType()) && field.getGenericType() instanceof ParameterizedType) {
                Type[] args = ((ParameterizedType)field.getGenericType()).getActualTypeArguments();
                return args.length == 1 && Stream.of(types).noneMatch((type) -> {
                    return Objects.equals(args[0], type);
                });
            } else {
                return false;
            }
        };
    }
}
