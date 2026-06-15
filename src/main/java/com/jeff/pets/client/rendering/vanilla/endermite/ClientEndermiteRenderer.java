package com.jeff.pets.client.rendering.vanilla.endermite;

import com.jeff.pets.mob.vanilla.hostile.ClientEndermite;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.endermite.EndermiteModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientEndermiteRenderer extends PetRenderer<@NotNull ClientEndermite, @NotNull LivingEntityRenderState, @NotNull EndermiteModel> {

    public static final ModelLayerLocation ENDERMITE_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientendermite"), "main");

    public ClientEndermiteRenderer(EntityRendererProvider.Context context) {
        super(context, new EndermiteModel(context.bakeLayer(ModelLayers.ENDERMITE)), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(LivingEntityRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/endermite.png");
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }
}
