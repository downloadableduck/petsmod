package com.jeff.pets.rendering.vanilla.silverfish;

import com.jeff.pets.mob.vanilla.hostile.ClientSilverfish;
import com.jeff.pets.rendering.PetRenderer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.silverfish.SilverfishModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

public class ClientSilverfishRenderer extends PetRenderer<@NotNull ClientSilverfish, @NotNull LivingEntityRenderState, @NotNull SilverfishModel> {

    public static final ModelLayerLocation SILVERFISH_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientsilverfish"), "main");

    public ClientSilverfishRenderer(EntityRendererProvider.Context context) {
        super(context, new SilverfishModel(context.bakeLayer(ModelLayers.SILVERFISH)), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(LivingEntityRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/silverfish/silverfish.png");
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }
}
