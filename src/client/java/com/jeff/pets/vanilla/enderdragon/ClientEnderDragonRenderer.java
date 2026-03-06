package com.jeff.pets.vanilla.enderdragon;

import com.jeff.pets.vanilla.boss.ClientEnderDragon;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.dragon.EnderDragonModel;
import net.minecraft.client.renderer.entity.EnderDragonRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.renderer.entity.layers.LivingEntityEmissiveLayer;
import net.minecraft.client.renderer.entity.state.EnderDragonRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientEnderDragonRenderer extends MobRenderer<@NotNull ClientEnderDragon, @NotNull ClientEnderDragonRenderState, @NotNull ClientEnderDragonModel> {

    public static final ModelLayerLocation ENDER_DRAGON_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientenderdragon"), "main");

    public ClientEnderDragonRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientEnderDragonModel(context.bakeLayer(ModelLayers.ENDER_DRAGON)), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientEnderDragonRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/enderdragon/dragon.png");
    }

    @Override
    public ClientEnderDragonRenderState createRenderState() {
        return new ClientEnderDragonRenderState();
    }

    @Override
    public void extractRenderState(ClientEnderDragon dragon, ClientEnderDragonRenderState state, float f) {
        super.extractRenderState(dragon, state, f);
        state.flapTime = dragon.getId() + state.ageInTicks / 8;
    }
}
