package com.jeff.pets.client.rendering.vanilla.villager;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientVillager;
import net.minecraft.client.render.model.entity.VillagerModel;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientVillagerRenderer extends PetRenderer<@NotNull ClientVillager, VillagerModel<ClientVillager>> {

    public ClientVillagerRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new VillagerModel<>(0), 0.5F);
        this.addLayer(new ClientVillagerDefaultLayer(this));
        this.addLayer(new ClientVillagerProfessionLayer(this));
    }

    @Override
    protected void applyScale(ClientVillager state, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientVillager villagerRenderState) {
        return new Identifier("minecraft", "textures/entity/villager/villager.png");
    }
}
