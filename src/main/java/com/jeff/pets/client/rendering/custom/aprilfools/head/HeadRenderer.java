package com.jeff.pets.client.rendering.custom.aprilfools.head;

import com.jeff.pets.client.rendering.IPetRenderState;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aprilfools.Head;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.component.ResolvableProfile;
import org.jetbrains.annotations.NotNull;

public class HeadRenderer extends PetRenderer<@NotNull Head, @NotNull LivingEntityRenderState, @NotNull HeadModel> {
    public HeadRenderer(final EntityRendererProvider.Context context) {
        super(context, new HeadModel(context.bakeLayer(HeadModel.LAYER_LOCATION)), 0.3F);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    public @NotNull Identifier getTextureLocation(final LivingEntityRenderState state) {
        String skin = ((IPetRenderState) state).pets$getPetSkin();
        return Minecraft.getInstance().playerSkinRenderCache().getOrDefault(ResolvableProfile.createUnresolved(skin)).playerSkin().body().texturePath();
    }
}
