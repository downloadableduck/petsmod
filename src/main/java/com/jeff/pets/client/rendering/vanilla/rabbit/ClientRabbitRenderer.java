package com.jeff.pets.client.rendering.vanilla.rabbit;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientRabbit;
import net.minecraft.resource.Identifier;

import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientRabbitRenderer extends PetRenderer<ClientRabbit> {
    public String rabbitTextureLocation;

    public ClientRabbitRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientRabbitModel(), 0.3F);
    }

    @Override
<<<<<<< HEAD
    protected void applyScale(@NotNull ClientRabbit livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            net.minecraft.client.render.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
=======
    public void preRenderCallback(ClientRabbit livingEntityRenderState, float f) {
        if (livingEntityRenderState.isChild()) {
            net.minecraft.client.renderer.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
>>>>>>> 908063cb (backport the 0.8.x updates to 1.8.9, as well as allow users to place and break blocks on top of the pet)
        }
    }

    @Override
<<<<<<< HEAD
    public @NotNull Identifier getTextureLocation(ClientRabbit rabbitRenderState) {
        if (CONFIG.activePet.equals("brown")) {
=======
    public ResourceLocation getEntityTexture(ClientRabbit rabbitRenderState) {
        if (rabbitRenderState.petSkin.equals("brown")) {
>>>>>>> 908063cb (backport the 0.8.x updates to 1.8.9, as well as allow users to place and break blocks on top of the pet)
            rabbitTextureLocation = "textures/entity/rabbit/brown.png";
        } else if (rabbitRenderState.petSkin.equals("white")) {
            rabbitTextureLocation = "textures/entity/rabbit/white.png";
        } else if (rabbitRenderState.petSkin.equals("black")) {
            rabbitTextureLocation = "textures/entity/rabbit/black.png";
        } else if (rabbitRenderState.petSkin.equals("gold")) {
            rabbitTextureLocation = "textures/entity/rabbit/gold.png";
        } else if (rabbitRenderState.petSkin.equals("salt")) {
            rabbitTextureLocation = "textures/entity/rabbit/salt.png";
        } else if (rabbitRenderState.petSkin.equals("splotched")) {
            rabbitTextureLocation = "textures/entity/rabbit/white_splotched.png";
        } else if (rabbitRenderState.petSkin.equals("killer")) {
            rabbitTextureLocation = "textures/entity/rabbit/caerbannog.png";
        } else if (rabbitRenderState.petSkin.equals("toast")) {
            rabbitTextureLocation = "textures/entity/rabbit/toast.png";
        } else {
            rabbitTextureLocation = "textures/entity/rabbit/brown.png";
        }

        return new Identifier("minecraft", rabbitTextureLocation);
    }
}
