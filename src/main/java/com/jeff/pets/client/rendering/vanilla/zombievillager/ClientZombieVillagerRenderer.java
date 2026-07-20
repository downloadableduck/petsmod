package com.jeff.pets.client.rendering.vanilla.zombievillager;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientZombieVillager;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientZombieVillagerRenderer extends PetRenderer<@NotNull ClientZombieVillager, @NotNull ClientZombieVillagerModel> {

    public ClientZombieVillagerRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientZombieVillagerModel(0, false), 0.75f);
        this.addLayer(new ClientZombieVillagerProfessionLayer(this));
    }

    @Override
    protected void applyScale(@NotNull ClientZombieVillager livingEntityRenderState, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientZombieVillager livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/zombie_villager/zombie_villager.png");
    }

    @Override
    public void applyRotation(ClientZombieVillager state, float f, float g, float h) {
        super.applyRotation(state, f, g, h);
        if (state.isRiding()) {
            com.mojang.blaze3d.platform.GlStateManager.translate(0, -0.5f, 0);
        }
    }
}
