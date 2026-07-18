package com.jeff.pets.client.rendering.vanilla.villager;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientVillager;
import net.minecraft.client.render.entity.model.VillagerResemblingModel;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientVillagerRenderer extends PetRenderer<@NotNull ClientVillager, VillagerResemblingModel<ClientVillager>> {

    public ClientVillagerRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new VillagerResemblingModel<>(0), 0.5F);
        this.addFeature(new ClientVillagerDefaultLayer(this));
        this.addFeature(new ClientVillagerProfessionLayer(this));
    }

    @Override
    protected void scale(ClientVillager state, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTexture(ClientVillager villagerRenderState) {
        return new Identifier("minecraft", "textures/entity/villager/villager.png");
    }
}
