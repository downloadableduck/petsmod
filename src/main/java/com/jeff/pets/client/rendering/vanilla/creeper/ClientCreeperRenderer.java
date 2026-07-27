package com.jeff.pets.client.rendering.vanilla.creeper;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientCreeper;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.CreeperChargeLayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.CreeperModel;
import net.minecraft.util.ResourceLocation;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCreeperRenderer extends PetRenderer<ClientCreeper, CreeperModel<ClientCreeper>> {

    public ClientCreeperRenderer(net.minecraft.client.renderer.entity.EntityRendererManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new CreeperModel<>(), 0.75f);
        this.addLayer((LayerRenderer) new CreeperChargeLayer((IEntityRenderer) this));
    }

    @Override
    public ResourceLocation getTextureLocation(ClientCreeper livingEntityRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/creeper/creeper.png");
    }

    @Override
    public void render(ClientCreeper creeper, float f, float g, MatrixStack poseStack, IRenderTypeBuffer source, int i) {
        super.render(creeper, f, g, poseStack, source, i);
        creeper.isPowered = Objects.equals(CONFIG.creeperSkin, "charged");
    }
}
