package com.jeff.pets.vanilla.villager;

import com.jeff.pets.vanilla.passive.ClientVillager;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.CrossedArmsItemLayer;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.entity.state.VillagerRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientVillagerRenderer extends MobRenderer<@NotNull ClientVillager, @NotNull LivingEntityRenderState, ClientVillagerModel> {
    public static final ModelLayerLocation VILLAGER_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientvillager"), "main");
    public static final CustomHeadLayer.Transforms CUSTOM_HEAD_TRANSFORMS = new CustomHeadLayer.Transforms(-0.1171875F, -0.07421875F, 1.0F);
    private static final Identifier defaultVillagerTexturePath = Identifier.withDefaultNamespace("textures/entity/villager/villager.png");
    EntityRendererProvider.Context context;

    public ClientVillagerRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientVillagerModel(context.bakeLayer(ModelLayers.VILLAGER)), 0.5F);
        this.context = context;
        this.addLayer(new CustomHeadLayer(this, context.getModelSet(), context.getPlayerSkinRenderCache(), CUSTOM_HEAD_TRANSFORMS));
        this.addLayer(new CrossedArmsItemLayer(this));
        this.addLayer(new ClientVillagerDefaultLayer(this));
        this.addLayer(new ClientVillagerProfessionLayer(this));
    }

    public @NotNull Identifier getTextureLocation(LivingEntityRenderState villagerRenderState) {
        return defaultVillagerTexturePath;
    }

    protected float getShadowRadius(LivingEntityRenderState villagerRenderState) {
        float f = super.getShadowRadius(villagerRenderState);
        return villagerRenderState.isBaby ? f * 0.5F : f;
    }

    public VillagerRenderState createRenderState() {
        return new VillagerRenderState();
    }

}
