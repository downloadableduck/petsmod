package com.jeff.pets.vanilla.zombievillager;

import com.jeff.pets.vanilla.hostile.ClientZombieVillager;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.zombie.ZombieVillagerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.ZombieVillagerRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientZombieVillagerRenderer extends MobRenderer<@NotNull ClientZombieVillager, @NotNull ZombieVillagerRenderState, @NotNull ZombieVillagerModel<@NotNull ZombieVillagerRenderState>> {

    public static final ModelLayerLocation ZOMBIE_VILLAGER_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientzombievillager"), "main");

    public ClientZombieVillagerRenderer(EntityRendererProvider.Context context) {
        super(context, new ZombieVillagerModel<>(context.bakeLayer(ModelLayers.ZOMBIE_VILLAGER)), 0.75f);
        this.addLayer(new ClientZombieVillagerProfessionLayer(this));
    }

    @Override
    public @NotNull Identifier getTextureLocation(ZombieVillagerRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/zombie_villager/zombie_villager.png");
    }

    @Override
    public ZombieVillagerRenderState createRenderState() {
        return new ZombieVillagerRenderState();
    }
}
