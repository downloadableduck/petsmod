package com.jeff.pets.client.rendering.vanilla.snowgolem;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientSnowGolem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.model.SnowmanEntityModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientSnowGolemRenderer extends PetRenderer<@NotNull ClientSnowGolem, @NotNull SnowmanEntityModel<ClientSnowGolem>> {

    public ClientSnowGolemRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new SnowmanEntityModel<>(), 0.5F);
        this.addFeature(new ClientSnowGolemHeadLayer(this, MinecraftClient.getInstance().getBlockRenderManager(), context2.getItemRenderer()));
    }

    @Override
    public @NotNull Identifier getTexture(ClientSnowGolem snowGolemRenderState) {
        return new Identifier("minecraft", "textures/entity/snow_golem.png");
    }

    /*@Override
    public void extractRenderState(ClientSnowGolem snowGolem, LivingEntityRenderState state, float f) {
        super.extractRenderState(snowGolem, state, f);
        snowGolem.headItem = CONFIG.snowGolemSkin.equals("pumpkin_on") ? new ItemStack(Items.CARVED_PUMPKIN) : ItemStack.EMPTY;
        snowGolem.headItemModel = this.itemRenderer.resolveItemModel(state.headItem, snowGolem, ItemDisplayContext.HEAD);
    }*/
}
