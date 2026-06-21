package com.jeff.pets.client.rendering.custom.aprilfools.head;

import com.jeff.pets.mob.custom.aprilfools.Head;
import com.jeff.pets.client.rendering.PetRenderer;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class HeadRenderer extends PetRenderer<@NotNull Head, @NotNull LivingEntityRenderState, @NotNull HeadModel> {
    public HeadRenderer(final EntityRendererProvider.Context context) {
        super(context, new HeadModel(context.bakeLayer(HeadModel.LAYER_LOCATION)), 0.3F);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(final LivingEntityRenderState state) {
        Minecraft minecraft = Minecraft.getInstance();
        return minecraft.getSkinManager().getInsecureSkin(new GameProfile(minecraft.getPlayerSocialManager().getDiscoveredUUID(CONFIG.headSkin), CONFIG.headSkin)).texture();
    }
}
