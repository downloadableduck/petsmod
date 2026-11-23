//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2;

import com.google.common.collect.ImmutableList;
import me.shedaniel.clothconfig2.api.*;
import me.shedaniel.clothconfig2.compat.GuiEventListener;
import me.shedaniel.clothconfig2.compat.InputConstants;
import me.shedaniel.clothconfig2.compat.InputConstants.Type;
import me.shedaniel.clothconfig2.gui.entries.DoubleListEntry;
import me.shedaniel.clothconfig2.gui.entries.DropdownBoxEntry;
import me.shedaniel.clothconfig2.gui.entries.LongSliderEntry;
import me.shedaniel.clothconfig2.gui.entries.TooltipListEntry;
import me.shedaniel.clothconfig2.impl.EasingMethod;
import me.shedaniel.clothconfig2.impl.EasingMethod.EasingMethodImpl;
import me.shedaniel.clothconfig2.impl.EasingMethods;
import me.shedaniel.clothconfig2.impl.builders.DropdownMenuBuilder.TopCellElementBuilder;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resource.language.I18n;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.*;
import java.util.function.Supplier;

public class ClothConfigInitializer implements ClientModInitializer {
    public static final Logger LOGGER = LogManager.getFormatterLogger("ClothConfig");
    private static EasingMethod easingMethod;
    private static long scrollDuration;
    private static double scrollStep;
    private static double bounceBackMultiplier;

    public ClothConfigInitializer() {
    }

    public static double handleScrollingPosition(double[] target, double scroll, double maxScroll, float delta, double start, double duration) {
        if (getBounceBackMultiplier() >= 0.0) {
            target[0] = clamp(target[0], maxScroll);
            if (target[0] < 0.0) {
                target[0] -= target[0] * (1.0 - getBounceBackMultiplier()) * (double)delta / 3.0;
            } else if (target[0] > maxScroll) {
                target[0] = (target[0] - maxScroll) * (1.0 - (1.0 - getBounceBackMultiplier()) * (double)delta / 3.0) + maxScroll;
            }
        } else {
            target[0] = clamp(target[0], maxScroll, 0.0);
        }

        return !Precision.almostEquals(scroll, target[0], 0.0010000000474974513) ? expoEase(scroll, target[0], Math.min(((double)System.currentTimeMillis() - start) / duration * (double)delta * 3.0, 1.0)) : target[0];
    }

    public static double expoEase(double start, double end, double amount) {
        return start + (end - start) * getEasingMethod().apply(amount);
    }

    public static double clamp(double v, double maxScroll) {
        return clamp(v, maxScroll, 200.0);
    }

    public static double clamp(double v, double maxScroll, double clampExtension) {
        return Math.max(-clampExtension, Math.min(maxScroll + clampExtension, v));
    }

    public static EasingMethod getEasingMethod() {
        return easingMethod;
    }

    public static long getScrollDuration() {
        return scrollDuration;
    }

    public static double getScrollStep() {
        return scrollStep;
    }

    public static double getBounceBackMultiplier() {
        return bounceBackMultiplier;
    }

    private static void loadConfig() {
        File file = new File(FabricLoader.getInstance().getConfigDirectory(), "cloth-config2/config.properties");

        try {
            file.getParentFile().mkdirs();
            easingMethod = EasingMethodImpl.LINEAR;
            scrollDuration = 600L;
            scrollStep = 19.0;
            bounceBackMultiplier = 0.24;
            if (!file.exists()) {
                saveConfig();
            }

            Properties properties = new Properties();
            properties.load(new FileInputStream(file));
            String easing = properties.getProperty("easingMethod1", "LINEAR");
            Iterator var3 = EasingMethods.getMethods().iterator();

            while(var3.hasNext()) {
                EasingMethod value = (EasingMethod)var3.next();
                if (value.toString().equalsIgnoreCase(easing)) {
                    easingMethod = value;
                    break;
                }
            }

            scrollDuration = Long.parseLong(properties.getProperty("scrollDuration1", "600"));
            scrollStep = Double.parseDouble(properties.getProperty("scrollStep1", "19"));
            bounceBackMultiplier = Double.parseDouble(properties.getProperty("bounceBackMultiplier2", "0.24"));
        } catch (Exception var6) {
            Exception e = var6;
            e.printStackTrace();
            easingMethod = EasingMethodImpl.LINEAR;
            scrollDuration = 600L;
            scrollStep = 19.0;
            bounceBackMultiplier = 0.24;

            try {
                Files.deleteIfExists(file.toPath());
            } catch (Exception var5) {
            }
        }

        saveConfig();
    }

    private static void saveConfig() {
        File file = new File(FabricLoader.getInstance().getConfigDirectory(), "cloth-config2/config.properties");

        try {
            FileWriter writer = new FileWriter(file, false);
            Properties properties = new Properties();
            properties.setProperty("easingMethod1", easingMethod.toString());
            properties.setProperty("scrollDuration1", scrollDuration + "");
            properties.setProperty("scrollStep1", scrollStep + "");
            properties.setProperty("bounceBackMultiplier2", bounceBackMultiplier + "");
            properties.store(writer, (String)null);
            writer.close();
        } catch (Exception var3) {
            Exception e = var3;
            e.printStackTrace();
            easingMethod = EasingMethodImpl.LINEAR;
            scrollDuration = 600L;
            scrollStep = 19.0;
            bounceBackMultiplier = 0.24;
        }

    }

    public void onInitializeClient() {
        loadConfig();
        if (FabricLoader.getInstance().isModLoaded("modmenu")) {
            try {
                Class<?> clazz = Class.forName("io.github.prospector.modmenu.api.ModMenuApi");
                Method method = clazz.getMethod("addConfigOverride", String.class, Runnable.class);
                method.invoke((Object)null, "cloth-config2", (Runnable)() -> {
                    try {
                        Minecraft.getMinecraft().displayScreen(getConfigBuilderWithDemo().build());
                    } catch (Throwable var1) {
                        Throwable throwable = var1;
                        throwable.printStackTrace();
                    }

                });
            } catch (Exception var3) {
                Exception e = var3;
                LOGGER.error("[ClothConfig] Failed to add test config override for ModMenu!", e);
            }
        }

    }

    public static ConfigBuilder getConfigBuilder() {
        ConfigBuilder builder = ConfigBuilder.create().setParentScreen(net.minecraft.client.gui.screen.Screen.wrap(Minecraft.getMinecraft().currentScreen)).setTitle("title.cloth-config.config");
        builder.setDefaultBackgroundTexture("minecraft:textures/block/oak_planks.png");
        ConfigCategory scrolling = builder.getOrCreateCategory("category.cloth-config.scrolling");
        ConfigEntryBuilder entryBuilder = ConfigEntryBuilder.create();
        final DropdownBoxEntry<EasingMethod> easingMethodEntry = entryBuilder.startDropdownMenu("Easing Method", TopCellElementBuilder.of(builder.getParentScreen(), easingMethod, (str) -> {
            Iterator i$ = EasingMethods.getMethods().iterator();

            EasingMethod m;
            do {
                if (!i$.hasNext()) {
                    return null;
                }

                m = (EasingMethod)i$.next();
            } while(!m.toString().equals(str));

            return m;
        })).setDefaultValue(EasingMethodImpl.LINEAR).setSaveConsumer((o) -> {
            easingMethod = o;
        }).setSelections(EasingMethods.getMethods()).build();
        final LongSliderEntry scrollDurationEntry = entryBuilder.startLongSlider("option.cloth-config.scrollDuration", scrollDuration, 0L, 5000L).setTextGetter((integer) -> {
            return integer <= 0L ? "Value: Disabled" : (integer > 1500L ? String.format("Value: %.1fs", (float)integer / 1000.0F) : "Value: " + integer + "ms");
        }).setDefaultValue(600L).setSaveConsumer((i) -> {
            scrollDuration = i;
        }).build();
        final DoubleListEntry scrollStepEntry = entryBuilder.startDoubleField("option.cloth-config.scrollStep", scrollStep).setDefaultValue(19.0).setSaveConsumer((i) -> {
            scrollStep = i;
        }).build();
        final LongSliderEntry bounceMultiplierEntry = entryBuilder.startLongSlider("option.cloth-config.bounceBackMultiplier", (long)(bounceBackMultiplier * 1000.0), -10L, 750L).setTextGetter((integer) -> {
            return integer < 0L ? "Value: Disabled" : String.format("Value: %s", (double)integer / 1000.0);
        }).setDefaultValue(240L).setSaveConsumer((i) -> {
            bounceBackMultiplier = (double)i / 1000.0;
        }).build();
        scrolling.addEntry(new TooltipListEntry<Object>(I18n.getTranslation("option.cloth-config.setDefaultSmoothScroll"), (Supplier)null, false) {
            final int width = 220;
            private final ButtonWidget buttonWidget = new AbstractPressableButtonWidget(0, 0, 0, 20, this.getFieldName()) {
                public void onPress() {
                    easingMethodEntry.getSelectionElement().getTopRenderer().setValue(EasingMethodImpl.LINEAR);
                    scrollDurationEntry.setValue(600L);
                    scrollStepEntry.setValue("19.0");
                    bounceMultiplierEntry.setValue(240L);
                    getScreen().setEdited(true, isRequiresRestart());
                }
            };
            private final List<ButtonWidget> children;

            {
                this.children = ImmutableList.of(this.buttonWidget);
            }

            public Object getValue() {
                return null;
            }

            public Optional<Object> getDefaultValue() {
                return Optional.empty();
            }

            public void save() {
            }

            public List<? extends GuiEventListener> children() {
                return this.children;
            }

            public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
                super.render(index, y, x, entryWidth, entryHeight, mouseX, mouseY, isSelected, delta);
                this.buttonWidget.active = this.isEditable();
                this.buttonWidget.y = y;
                this.buttonWidget.x = x + entryWidth / 2 - 110;
                this.buttonWidget.width = 220;
                this.buttonWidget.render(mouseX, mouseY, delta);
            }
        });
        scrolling.addEntry(new TooltipListEntry<Object>(I18n.getTranslation("option.cloth-config.disableSmoothScroll"), (Supplier)null, false) {
            final int width = 220;
            private final ButtonWidget buttonWidget = new AbstractPressableButtonWidget(0, 0, 0, 20, this.getFieldName()) {
                public void onPress() {
                    easingMethodEntry.getSelectionElement().getTopRenderer().setValue(EasingMethodImpl.NONE);
                    scrollDurationEntry.setValue(0L);
                    scrollStepEntry.setValue("16.0");
                    bounceMultiplierEntry.setValue(-10L);
                    getScreen().setEdited(true, isRequiresRestart());
                }
            };
            private final List<ButtonWidget> children;

            {
                this.children = ImmutableList.of(this.buttonWidget);
            }

            public Object getValue() {
                return null;
            }

            public Optional<Object> getDefaultValue() {
                return Optional.empty();
            }

            public void save() {
            }

            public List<? extends GuiEventListener> children() {
                return this.children;
            }

            public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
                super.render(index, y, x, entryWidth, entryHeight, mouseX, mouseY, isSelected, delta);
                this.buttonWidget.active = this.isEditable();
                this.buttonWidget.y = y;
                this.buttonWidget.x = x + entryWidth / 2 - 110;
                this.buttonWidget.width = 220;
                this.buttonWidget.render(mouseX, mouseY, delta);
            }
        });
        scrolling.addEntry(easingMethodEntry);
        scrolling.addEntry(scrollDurationEntry);
        scrolling.addEntry(scrollStepEntry);
        scrolling.addEntry(bounceMultiplierEntry);
        builder.setSavingRunnable(ClothConfigInitializer::saveConfig);
        builder.transparentBackground();
        return builder;
    }

    public static ConfigBuilder getConfigBuilderWithDemo() {
        ConfigBuilder builder = getConfigBuilder();
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        ConfigCategory testing = builder.getOrCreateCategory("category.cloth-config.testing");
        testing.addEntry(entryBuilder.startKeyCodeField("Cool Key", InputConstants.UNKNOWN).setDefaultValue(InputConstants.UNKNOWN).build());
        testing.addEntry(entryBuilder.startModifierKeyCodeField("Cool Modifier Key", ModifierKeyCode.of(Type.KEYSYM.getOrCreate(79), Modifier.of(false, true, false))).setDefaultValue(ModifierKeyCode.of(Type.KEYSYM.getOrCreate(79), Modifier.of(false, true, false))).build());
        testing.addEntry(entryBuilder.startDoubleList("A list of Doubles", Arrays.asList(1.0, 2.0, 3.0)).setDefaultValue(Arrays.asList(1.0, 2.0, 3.0)).build());
        testing.addEntry(entryBuilder.startLongList("A list of Longs", Arrays.asList(1L, 2L, 3L)).setDefaultValue(Arrays.asList(1L, 2L, 3L)).build());
        testing.addEntry(entryBuilder.startStrList("A list of Strings", Arrays.asList("abc", "xyz")).setDefaultValue(Arrays.asList("abc", "xyz")).build());
        SubCategoryBuilder colors = entryBuilder.startSubCategory("Colors").setExpanded(true);
        colors.add((AbstractConfigListEntry)entryBuilder.startColorField("A color field", 65535).setDefaultValue(65535).build());
        colors.add((AbstractConfigListEntry)entryBuilder.startColorField("An alpha color field", -16711681).setDefaultValue(-16711681).setAlphaMode(true).build());
        testing.addEntry(colors.build());
        return builder;
    }

    static {
        easingMethod = EasingMethodImpl.LINEAR;
        scrollDuration = 600L;
        scrollStep = 19.0;
        bounceBackMultiplier = 0.24;
    }

    public static class Precision {
        public static final float FLOAT_EPSILON = 0.001F;
        public static final double DOUBLE_EPSILON = 1.0E-7;

        public Precision() {
        }

        public static boolean almostEquals(float value1, float value2, float acceptableDifference) {
            return Math.abs(value1 - value2) <= acceptableDifference;
        }

        public static boolean almostEquals(double value1, double value2, double acceptableDifference) {
            return Math.abs(value1 - value2) <= acceptableDifference;
        }
    }
}
