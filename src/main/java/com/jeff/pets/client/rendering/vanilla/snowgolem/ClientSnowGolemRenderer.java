package com.jeff.pets.client.rendering.vanilla.snowgolem;

import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.entity.Entity; import net.minecraft.client.renderer.entity.RenderManager; import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.util.ResourceLocation;

public class ClientSnowGolemRenderer extends PetRenderer {

    public ClientSnowGolemRenderer(RenderManager renderManager, PetsClientInitializer.Context context) {
        super(new ModelSnowMan(), 0.5F);
        this.setPetLayer(new ClientSnowGolemHeadLayer(this));
    }

    @Override
    public ResourceLocation getEntityTexture( final Entity snowGolemRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/snow_golem.png");
    }

    /*@Override
    public void extractRenderState(ClientSnowGolem snowGolem, LivingEntityRenderState state, float f) {
        super.extractRenderState(snowGolem, state, f);
        snowGolem.headItem = CONFIG.snowGolemSkin.equals("pumpkin_on") ? new ItemStack(Items.CARVED_PUMPKIN) : ItemStack.EMPTY;
        snowGolem.headItemModel = this.itemRenderer.resolveItemModel(state.headItem, snowGolem, ItemDisplayContext.HEAD);
    }*/
}

