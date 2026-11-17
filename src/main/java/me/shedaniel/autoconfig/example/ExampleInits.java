//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.autoconfig.example;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.DummyConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import org.jetbrains.annotations.ApiStatus.Internal;

@Internal
public class ExampleInits {
    public ExampleInits() {
    }

    public static void exampleCommonInit() {
        ConfigHolder<ExampleConfig> holder = AutoConfig.register(ExampleConfig.class, PartitioningSerializer.wrap(DummyConfigSerializer::new));
        holder.getConfig();
        AutoConfig.getConfigHolder(ExampleConfig.class).getConfig();
        AutoConfig.getConfigHolder(ExampleConfig.class).registerSaveListener((manager, data) -> {
            return 2;
        });
        AutoConfig.getConfigHolder(ExampleConfig.class).registerLoadListener((manager, newData) -> {
            return 2;
        });
    }

    public static void exampleClientInit() {
        AutoConfig.getGuiRegistry(ExampleConfig.class);
    }
}
