package com.jeff.pets.rendering.aprilfools.potatohusk;

import com.jeff.pets.mob.aprilfools.PotatoHusk;
import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.rendering.vanilla.zombie.ClientZombieModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class PotatoHuskRenderer extends PetRenderer<@NotNull PotatoHusk, @NotNull ZombieRenderState, @NotNull ClientZombieModel> {

    public static final ModelLayerLocation POTATO_HUSK_LOCATION = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("potatohusk"), "main");

    public PotatoHuskRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientZombieModel(context.bakeLayer(ModelLayers.HUSK)), 0.75f);
    }

    protected void scale(ZombieRenderState state, @NotNull PoseStack poseStack) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ZombieRenderState livingEntityRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/zombie/husk_potato.png");
    }

    @Override
    public ZombieRenderState createRenderState() {
        return new ZombieRenderState();
    }

    @Override
    public void extractRenderState(PotatoHusk husk, ZombieRenderState state, float f) {
        super.extractRenderState(husk, state, f);
        state.isPassenger = husk.isPassenger();
    }
}
