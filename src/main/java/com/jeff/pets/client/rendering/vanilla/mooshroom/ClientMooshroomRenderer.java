package com.jeff.pets.client.rendering.vanilla.mooshroom;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.cow.ClientCowModel;
import com.jeff.pets.mob.vanilla.passive.ClientMooshroom;
import net.minecraft.client.Minecraft;
import net.minecraft.resource.Identifier;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientMooshroomRenderer extends PetRenderer<ClientMooshroom> {

    String mooshroomTexturePath;

    public ClientMooshroomRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientCowModel(), 0.7F);
        this.addLayer(new ClientMushroomCowMushroomLayer(this, Minecraft.getInstance().getBlockRenderDispatcher()));
    }

    @Override
<<<<<<< HEAD
    protected void applyScale(ClientMooshroom state, float f) {
        if (CONFIG.isBaby) {
            net.minecraft.client.render.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
=======
    public void preRenderCallback(ClientMooshroom state, float f) {
        if (state.isChild()) {
            net.minecraft.client.renderer.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
>>>>>>> 908063cb (backport the 0.8.x updates to 1.8.9, as well as allow users to place and break blocks on top of the pet)
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientMooshroom cowRenderState) {
            mooshroomTexturePath = "textures/entity/cow/mooshroom.png";
        return new Identifier("minecraft", mooshroomTexturePath);
    }
}
