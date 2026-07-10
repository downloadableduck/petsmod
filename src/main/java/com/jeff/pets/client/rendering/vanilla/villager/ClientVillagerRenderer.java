package com.jeff.pets.client.rendering.vanilla.villager;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientVillager;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientVillagerRenderer extends PetRenderer<@NotNull ClientVillager, VillagerModel<ClientVillager>> {

    public ClientVillagerRenderer(net.minecraft.client.renderer.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry.Context context2) {
        super(context, new VillagerModel<>(0), 0.5F);
        this.addLayer(new ClientVillagerDefaultLayer(this));
        this.addLayer(new ClientVillagerProfessionLayer(this));
    }

    @Override
    protected void scale(ClientVillager state, @NotNull PoseStack poseStack, float f) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(ClientVillager villagerRenderState) {
        return new ResourceLocation("minecraft", "textures/entity/villager/villager.png");
    }
}
