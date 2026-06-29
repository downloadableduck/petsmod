package com.jeff.pets.rendering.custom.aprilfools.head;

import com.jeff.pets.mob.custom.aprilfools.Head;
import com.jeff.pets.rendering.PetRenderer;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.Central.CONFIG;

public class HeadRenderer extends PetRenderer<@NotNull Head, @NotNull HeadModel> {
    public HeadRenderer(final EntityRendererProvider.Context context) {
        super(context, new HeadModel(context.bakeLayer(HeadModel.LAYER_LOCATION)), 0.3F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(final Head state) {
        Minecraft minecraft = Minecraft.getInstance();
        try {
            GameProfile gameProfile = SkullBlockEntity.fetchGameProfile(CONFIG.headSkin).get().get();
            return minecraft.getSkinManager().getInsecureSkin(gameProfile).texture();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
