package com.jeff.pets.rendering.vanilla.sheep;

import com.jeff.pets.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientSheep;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.SheepRenderState;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class ClientSheepRenderer extends PetRenderer<@NotNull ClientSheep, @NotNull SheepRenderState, @NotNull ClientSheepModel> {
    public static final ModelLayerLocation SHEEP_LOCATION = new ModelLayerLocation(Identifier.withDefaultNamespace("clientsheep"), "main");

    public ClientSheepRenderer(EntityRendererProvider.Context context) {
        super(context, new ClientSheepModel(context.bakeLayer(ModelLayers.SHEEP)), 0.7F);
        this.addLayer(new ClientSheepWoolLayer(this, context.getModelSet()));
    }

    @Override
    protected void scale(@NotNull SheepRenderState livingEntityRenderState, @NotNull PoseStack poseStack) {
        if (CONFIG.isBaby) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public SheepRenderState createRenderState() {
        return new SheepRenderState();
    }

    @Override
    public @NotNull Identifier getTextureLocation(@NotNull SheepRenderState livingEntityRenderState) {
        return Identifier.withDefaultNamespace("textures/entity/sheep/sheep.png");
    }

    @Override
    public void extractRenderState(ClientSheep sheep, SheepRenderState state, float f) {
        super.extractRenderState(sheep, state, f);
        state.isSheared = false;
    }
}
