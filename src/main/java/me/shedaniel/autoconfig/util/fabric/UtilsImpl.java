//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.autoconfig.util.fabric;

import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class UtilsImpl {
    public UtilsImpl() {
    }

    public static Path getConfigFolder() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
