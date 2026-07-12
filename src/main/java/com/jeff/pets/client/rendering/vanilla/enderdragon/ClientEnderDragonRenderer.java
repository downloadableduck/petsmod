package com.jeff.pets.client.rendering.vanilla.enderdragon;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.boss.ClientEnderDragon;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;


public class ClientEnderDragonRenderer extends PetRenderer<@NotNull ClientEnderDragon, ClientEnderDragonModel> {

    public ClientEnderDragonRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context) {
        super(context, new ClientEnderDragonModel(), 0.75f);
    }

    @Override
    protected void scale(@NotNull ClientEnderDragon livingEntityRenderState, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.25f, 0.25f, 0.25f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientEnderDragon livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/enderdragon/dragon.png");
    }
}
