package com.jeff.pets.vanilla.husk;

import com.jeff.pets.vanilla.hostile.ClientHusk;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.zombie.ZombieModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.ZombieRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientHuskRenderer extends MobRenderer<@NotNull ClientHusk, @NotNull ZombieRenderState, @NotNull ZombieModel<@NotNull ZombieRenderState>> {

    public static final ModelLayerLocation HUSK_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clienthusk"), "main");

    public ClientHuskRenderer(EntityRendererProvider.Context context) {
        super(context, new ZombieModel<>(context.bakeLayer(ModelLayers.HUSK)), 0.75F);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ZombieRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/zombie/husk.png");
    }

    @Override
    public ZombieRenderState createRenderState() {
        return new ZombieRenderState();
    }
}
