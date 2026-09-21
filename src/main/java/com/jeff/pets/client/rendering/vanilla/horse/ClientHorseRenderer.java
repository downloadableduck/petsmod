package com.jeff.pets.client.rendering.vanilla.horse;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientHorse;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientHorseRenderer extends PetRenderer<ClientHorse> {
    public String horseTextureLocation;

    public ClientHorseRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientHorseModel(0), 0.5f);
    }

    @Override
<<<<<<< HEAD
    protected void applyScale(ClientHorse state, float f) {
        if (CONFIG.isBaby) {
            net.minecraft.client.render.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
=======
    public void preRenderCallback(ClientHorse state, float f) {
        if (state.isChild()) {
            net.minecraft.client.renderer.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
>>>>>>> 908063cb (backport the 0.8.x updates to 1.8.9, as well as allow users to place and break blocks on top of the pet)
        }
    }

    @Override
<<<<<<< HEAD
    public @NotNull Identifier getTextureLocation(ClientHorse horseRenderState) {
        if (CONFIG.horseSkin.equals("black")) {
=======
    public ResourceLocation getEntityTexture(ClientHorse horseRenderState) {
        if (horseRenderState.petSkin.equals("black")) {
>>>>>>> 908063cb (backport the 0.8.x updates to 1.8.9, as well as allow users to place and break blocks on top of the pet)
            horseTextureLocation = "textures/entity/horse/horse_black.png";
        } else if (horseRenderState.petSkin.equals("brown")) {
            horseTextureLocation = "textures/entity/horse/horse_brown.png";
        } else if (horseRenderState.petSkin.equals("chestnut")) {
            horseTextureLocation = "textures/entity/horse/horse_chestnut.png";
        } else if (horseRenderState.petSkin.equals("creamy")) {
            horseTextureLocation = "textures/entity/horse/horse_creamy.png";
        } else if (horseRenderState.petSkin.equals("dark_brown")) {
            horseTextureLocation = "textures/entity/horse/horse_brown.png";
        } else if (horseRenderState.petSkin.equals("gray")) {
            horseTextureLocation = "textures/entity/horse/horse_gray.png";
        } else if (horseRenderState.petSkin.equals("white")) {
            horseTextureLocation = "textures/entity/horse/horse_white.png";
        } else if (horseRenderState.petSkin.equals("skeleton")) {
            horseTextureLocation = "textures/entity/horse/horse_skeleton.png";
        } else if (horseRenderState.petSkin.equals("zombie")) {
            horseTextureLocation = "textures/entity/horse/horse_zombie.png";
        } else {
            horseTextureLocation = "textures/entity/horse/horse_black.png";
        }
        return new Identifier("minecraft", horseTextureLocation);
    }
}
