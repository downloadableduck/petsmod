//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.autoconfig.example;

import blue.endless.jankson.Comment;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.Config.Gui.Background;
import me.shedaniel.autoconfig.annotation.Config.Gui.CategoryBackground;
import me.shedaniel.autoconfig.annotation.ConfigEntry.BoundedDiscrete;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Category;
import me.shedaniel.autoconfig.annotation.ConfigEntry.ColorPicker;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.*;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.EnumHandler.EnumDisplayOption;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import org.jetbrains.annotations.ApiStatus.Internal;

import java.util.Arrays;
import java.util.List;

@Config(
    name = "autoconfig1u_example"
)
@Background("minecraft:textures/block/oak_planks.png")
@CategoryBackground(
    category = "b",
    background = "minecraft:textures/block/stone.png"
)
@Internal
public class ExampleConfig extends PartitioningSerializer.GlobalData {
    @Category("a")
    @TransitiveObject
    public ModuleA moduleA = new ModuleA();
    @Category("a")
    @TransitiveObject
    public Empty empty = new Empty();
    @Category("b")
    @TransitiveObject
    public ModuleB moduleB = new ModuleB();

    public ExampleConfig() {
    }

    @Config(
        name = "module_a"
    )
    public static class ModuleA implements ConfigData {
        @PrefixText
        public boolean aBoolean = true;
        @Tooltip(
            count = 2
        )
        public ExampleEnum anEnum;
        @Tooltip(
            count = 2
        )
        @EnumHandler(
            option = EnumDisplayOption.BUTTON
        )
        public ExampleEnum anEnumWithButton;
        @Comment("This tooltip was automatically applied from a Jankson @Comment")
        public String aString;
        @CollapsibleObject(
            startExpanded = true
        )
        public PairOfIntPairs anObject;
        public List<Integer> list;
        public int[] array;
        public List<PairOfInts> complexList;
        public PairOfInts[] complexArray;

        public ModuleA() {
            this.anEnum = ExampleEnum.FOO;
            this.anEnumWithButton = ExampleEnum.FOO;
            this.aString = "hello";
            this.anObject = new PairOfIntPairs(new PairOfInts(), new PairOfInts(3, 4));
            this.list = Arrays.asList(1, 2, 3);
            this.array = new int[]{1, 2, 3};
            this.complexList = Arrays.asList(new PairOfInts(0, 1), new PairOfInts(3, 7));
            this.complexArray = new PairOfInts[]{new PairOfInts(0, 1), new PairOfInts(3, 7)};
        }
    }

    @Config(
        name = "empty"
    )
    public static class Empty implements ConfigData {
        public Empty() {
        }
    }

    @Config(
        name = "module_b"
    )
    public static class ModuleB implements ConfigData {
        @BoundedDiscrete(
            min = -1000L,
            max = 2000L
        )
        public int intSlider = 500;
        @BoundedDiscrete(
            min = -1000L,
            max = 2000L
        )
        public Long longSlider = 500L;
        @TransitiveObject
        public PairOfIntPairs anObject = new PairOfIntPairs(new PairOfInts(), new PairOfInts(3, 4));
        @Excluded
        public List<PairOfInts> aList = Arrays.asList(new PairOfInts(), new PairOfInts(3, 4));
        @ColorPicker
        public int color = 16777215;

        public ModuleB() {
        }
    }

    public static class PairOfIntPairs {
        @CollapsibleObject
        public PairOfInts first;
        @CollapsibleObject
        public PairOfInts second;

        public PairOfIntPairs() {
            this(new PairOfInts(), new PairOfInts());
        }

        public PairOfIntPairs(PairOfInts first, PairOfInts second) {
            this.first = first;
            this.second = second;
        }
    }

    public static class PairOfInts {
        public int foo;
        public int bar;

        public PairOfInts() {
            this(1, 2);
        }

        public PairOfInts(int foo, int bar) {
            this.foo = foo;
            this.bar = bar;
        }
    }

    static enum ExampleEnum {
        FOO,
        BAR,
        BAZ;

        private ExampleEnum() {
        }
    }
}
