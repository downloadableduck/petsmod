package com.jeff.pets.client.rendering.vanilla.zombievillager;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientZombieVillager;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombieVillagerRenderer extends PetRenderer<@NotNull ClientZombieVillager, @NotNull ClientZombieVillagerModel> {

    public ClientZombieVillagerRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new ClientZombieVillagerModel(0, false), 0.75f);
        this.addFeature(new ClientZombieVillagerProfessionLayer(this));
    }

    @Override
    protected void scale(@NotNull ClientZombieVillager livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTexture(ClientZombieVillager livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/zombie_villager/zombie_villager.png");
    }

    @Override
    public void setupTransforms(ClientZombieVillager state, float f, float g, float h) {
        super.setupTransforms(state, f, g, h);
        if (state.hasVehicle()) {
            com.mojang.blaze3d.platform.GlStateManager.translatef(0, -0.5f, 0);
        }
    }
}
