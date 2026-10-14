package com.jeff.pets.client.rendering.vanilla.snowgolem;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelSnowMan;
import com.jeff.pets.client.rendering.PetRenderer;

public class ClientSnowGolemRenderer extends PetRenderer {

    public ClientSnowGolemRenderer() {
        super(new ModelSnowMan(), 0.5F);
        this.setPetLayer(new ClientSnowGolemHeadLayer(this));
    }

    @Override
    public String getEntityTexture( final Entity snowGolemRenderState) {
        return "textures/entity/snow_golem.png";
    }

    /*@Override
    public void extractRenderState(ClientSnowGolem snowGolem, LivingEntityRenderState state, float f) {
        super.extractRenderState(snowGolem, state, f);
        snowGolem.headItem = CONFIG.snowGolemSkin.equals("pumpkin_on") ? new ItemStack(Items.CARVED_PUMPKIN) : ItemStack.EMPTY;
        snowGolem.headItemModel = this.itemRenderer.resolveItemModel(state.headItem, snowGolem, ItemDisplayContext.HEAD);
    }*/
}

