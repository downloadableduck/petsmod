package com.jeff.pets.client.rendering.vanilla.sheep;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientSheep;
import net.minecraft.util.ResourceLocation;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSheepRenderer extends PetRenderer<ClientSheep, ClientSheepModel> {

    public ClientSheepRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientSheepModel(), 0.7F);
        this.addLayer(new ClientSheepWoolLayer(this));
    }

    @Override
    protected void scale(ClientSheep livingEntityRenderState, com.mojang.blaze3d.matrix.MatrixStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(ClientSheep livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/sheep/sheep.png");
    }

    /*@Override
    public void extractRenderState(Sheep sheep, SheepRenderState state, float f) {
        super.extractRenderState(sheep, state, f);
        sheep.sheared = false;
    }*/
}
