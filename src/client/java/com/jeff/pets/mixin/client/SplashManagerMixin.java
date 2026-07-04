package com.jeff.pets.mixin.client;

import com.jeff.pets.PetsConfig;
import net.minecraft.client.resources.SplashManager;
import net.minecraft.resources.ResourceLocation;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static com.jeff.pets.Central.CONFIG;
import static com.jeff.pets.PetsInitializer.MOD_ID;

/**
 * Edits the {@link SplashManager#SPLASHES_LOCATION} at launch to assign it to a
 * custom value, if {@link PetsConfig#customTitleEnabled} is {@code true}. Please note that
 * the splashes are initialized once, at launch, and won't reset until the game is closed
 * and re-opened.
 *
 * @see TitleScreenRenderingMixin
 */
@Mixin(SplashManager.class)
public class SplashManagerMixin {

    @Redirect(method = "<clinit>", at = @At(value = "FIELD", opcode = Opcodes.PUTSTATIC, target = "Lnet/minecraft/client/resources/SplashManager;SPLASHES_LOCATION:Lnet/minecraft/resources/ResourceLocation;"))
    private static void redirect(ResourceLocation identifier) {
        SplashManager.SPLASHES_LOCATION = CONFIG.customTitleEnabled
                ? new ResourceLocation(MOD_ID, "texts/splashes.txt")
                : new ResourceLocation("minecraft", "texts/splashes.txt");
    }
}
