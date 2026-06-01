package com.jeff.pets.client.rendering.vanilla.tadpole;

import com.jeff.pets.mob.vanilla.passive.ClientTadpole;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.animal.frog.TadpoleModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientTadpoleRenderer extends PetRenderer<@NotNull ClientTadpole, @NotNull LivingEntityRenderState, @NotNull TadpoleModel> {
    public static final ModelLayerLocation TADPOLE_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clienttadpole"), "main");

    public ClientTadpoleRenderer(EntityRendererProvider.Context context) {
        super(context, new TadpoleModel(context.bakeLayer(ModelLayers.TADPOLE)), 0.75F);
    }

    @Override
    public @NotNull Identifier getTextureLocation(LivingEntityRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/tadpole/tadpole.png");
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }
}
