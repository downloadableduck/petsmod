package com.jeff.pets.client.rendering.vanilla.sheep;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientSheep;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSheepRenderer extends PetRenderer<@NotNull ClientSheep, @NotNull ClientSheepModel> {

    public ClientSheepRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context) {
        super(context, new ClientSheepModel(), 0.7F);
        this.addLayer(new ClientSheepWoolLayer(this));
    }

    @Override
    protected void scale(@NotNull ClientSheep livingEntityRenderState, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull ClientSheep livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/sheep/sheep.png");
    }

    /*@Override
    public void extractRenderState(Sheep sheep, SheepRenderState state, float f) {
        super.extractRenderState(sheep, state, f);
        sheep.sheared = false;
    }*/
}
