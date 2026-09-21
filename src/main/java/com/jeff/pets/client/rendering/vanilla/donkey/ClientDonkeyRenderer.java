package com.jeff.pets.client.rendering.vanilla.donkey;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.horse.ClientHorseModel;
import com.jeff.pets.mob.vanilla.passive.ClientDonkey;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientDonkeyRenderer extends PetRenderer<ClientDonkey> {

    public ClientDonkeyRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientHorseModel(0), 0.5f);
    }

    public @NotNull Identifier getTextureLocation(ClientDonkey donkeyRenderState) {
        return new Identifier("minecraft", "textures/entity/horse/donkey.png");
    }

    @Override
<<<<<<< HEAD
    protected void applyScale(ClientDonkey state, float f) {
        if (CONFIG.isBaby) {
            net.minecraft.client.render.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
=======
    public void preRenderCallback(ClientDonkey state, float f) {
        if (state.isChild()) {
            net.minecraft.client.renderer.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
>>>>>>> 908063cb (backport the 0.8.x updates to 1.8.9, as well as allow users to place and break blocks on top of the pet)
        }
    }
}
