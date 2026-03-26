package com.jeff.pets.vanilla.guardian;

import com.jeff.pets.vanilla.hostile.ClientGuardian;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.monster.guardian.GuardianModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.GuardianRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.GuardianRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;


public class ClientGuardianRenderer extends MobRenderer<@NotNull ClientGuardian, @NotNull GuardianRenderState, @NotNull GuardianModel> {
    public static final ModelLayerLocation GUARDIAN_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientguardian"), "main");

    public ClientGuardianRenderer(EntityRendererProvider.Context context) {
        super(context, new GuardianModel(context.bakeLayer(ModelLayers.GUARDIAN)), 0.75f);
    }

    @Override
    public @NotNull Identifier getTextureLocation(GuardianRenderState livingEntityRenderState) {
        livingEntityRenderState.spikesAnimation = 1;
        return Identifier.withDefaultNamespace("textures/entity/guardian.png");
    }

    @Override
    public GuardianRenderState createRenderState() {
        return new GuardianRenderState();
    }
}
