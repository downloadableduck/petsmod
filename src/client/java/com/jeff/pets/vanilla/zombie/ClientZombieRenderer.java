package com.jeff.pets.vanilla.zombie;

import com.jeff.pets.vanilla.hostile.ClientZombie;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.monster.zombie.ZombieModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Pet.CONFIG;

public class ClientZombieRenderer extends MobRenderer<@NotNull ClientZombie, @NotNull ZombieRenderState, @NotNull ZombieModel<@NotNull ZombieRenderState>> {

    public static final ModelLayerLocation ZOMBIE_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientzombie"), "main");

    public ClientZombieRenderer(EntityRendererProvider.Context context) {
        super(context, new ZombieModel<>(context.bakeLayer(ModelLayers.ZOMBIE)), 0.75f);
    }

    public static LayerDefinition createBaseZombieLayer() {
        ZombieModel.createMesh(CubeDeformation.NONE, 0);
        return LayerDefinition.create(new MeshDefinition(), 64, 64);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ZombieRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/zombie/zombie.png");
    }

    @Override
    public ZombieRenderState createRenderState() {
        return new ZombieRenderState();
    }
}
