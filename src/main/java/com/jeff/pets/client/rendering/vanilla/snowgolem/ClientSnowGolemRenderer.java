package com.jeff.pets.client.rendering.vanilla.snowgolem;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientSnowGolem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.SnowGolemModel;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ClientSnowGolemRenderer extends PetRenderer<@NotNull ClientSnowGolem, @NotNull SnowGolemModel<ClientSnowGolem>> {

    public ClientSnowGolemRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context) {
        super(context, new SnowGolemModel<>(), 0.5F);
        this.addLayer(new ClientSnowGolemHeadLayer(this, Minecraft.getInstance().getBlockRenderer(), Minecraft.getInstance().getItemRenderer()));
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientSnowGolem snowGolemRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/snow_golem.png");
    }

    /*@Override
    public void extractRenderState(ClientSnowGolem snowGolem, LivingEntityRenderState state, float f) {
        super.extractRenderState(snowGolem, state, f);
        snowGolem.headItem = CONFIG.snowGolemSkin.equals("pumpkin_on") ? new ItemStack(Items.CARVED_PUMPKIN) : ItemStack.EMPTY;
        snowGolem.headItemModel = this.itemRenderer.resolveItemModel(state.headItem, snowGolem, ItemDisplayContext.HEAD);
    }*/
}
