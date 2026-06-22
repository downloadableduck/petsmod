package com.jeff.pets.rendering.vanilla.snowgolem;

import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientSnowGolem;
import net.minecraft.client.model.SnowGolemModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SnowGolemRenderer;
import net.minecraft.client.renderer.entity.layers.SnowGolemHeadLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class ClientSnowGolemRenderer extends PetRenderer<@NotNull ClientSnowGolem, @NotNull LivingEntityRenderState, @NotNull SnowGolemModel> {
    public static final ModelLayerLocation SNOW_GOLEM = new ModelLayerLocation(ResourceLocation.withDefaultNamespace("clientsnowgolem"), "main");

    public ClientSnowGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new SnowGolemModel(context.bakeLayer(ModelLayers.SNOW_GOLEM)), 0.5F);
        this.addLayer(new SnowGolemHeadLayer(this, context.getBlockRenderDispatcher(), context.getItemRenderer()));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(LivingEntityRenderState snowGolemRenderState) {
        return ResourceLocation.withDefaultNamespace("textures/entity/snow_golem.png");
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    public void extractRenderState(ClientSnowGolem snowGolem, LivingEntityRenderState state, float f) {
        super.extractRenderState(snowGolem, state, f);
        state.headItem = CONFIG.snowGolemSkin.equals("pumpkin_on") ? new ItemStack(Items.CARVED_PUMPKIN) : ItemStack.EMPTY;
        state.headItemModel = this.itemRenderer.resolveItemModel(state.headItem, snowGolem, ItemDisplayContext.HEAD);
    }
}
