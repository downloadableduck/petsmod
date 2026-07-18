package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.PetsConfig;
import net.minecraft.client.resource.SplashTextResourceSupplier;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Edits the {@link SplashTextResourceSupplier#RESOURCE_ID} at launch to assign it to a
 * custom value, if {@link PetsConfig#customTitleEnabled} is {@code true}. Please note that
 * the splashes are initialized once, at launch, and won't reset until the game is closed
 * and re-opened.
 *
 * @see TitleScreenRenderingMixin
 */
@Mixin(SplashTextResourceSupplier.class)
public class SplashTextResourceSupplierMixin {

    /*@Redirect(method = "<clinit>", at = @At(value = "FIELD", opcode = Opcodes.PUTSTATIC, target = "Lnet/minecraft/client/resources/SplashManager;SPLASHES_LOCATION:Lnet/minecraft/resources/ResourceLocation;"))
    private static void redirect(ResourceLocation identifier) {
        SplashManager.SPLASHES_LOCATION = CONFIG.customTitleEnabled
                ? new ResourceLocation(MOD_ID, "texts/splashes.txt")
                : new ResourceLocation("minecraft", "texts/splashes.txt");
    }*/
}
