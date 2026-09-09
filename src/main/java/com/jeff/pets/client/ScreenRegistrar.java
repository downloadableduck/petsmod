package com.jeff.pets.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

import static com.jeff.pets.PetsInitializer.MOD_ID;

@Mod(value=MOD_ID, dist= Dist.CLIENT)
public class ScreenRegistrar {
    public ScreenRegistrar(ModContainer modContainer) {
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, (screen, s) -> new PetsConfigScreen());
    }
}
