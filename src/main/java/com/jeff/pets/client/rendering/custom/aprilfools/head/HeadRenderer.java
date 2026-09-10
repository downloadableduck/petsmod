package com.jeff.pets.client.rendering.custom.aprilfools.head;

import com.jeff.pets.mob.custom.aprilfools.Head;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.DefaultSkinHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

import java.util.Map;

import static com.jeff.pets.client.Central.CONFIG;

public class HeadRenderer extends MobEntityRenderer<Head> {

    private MinecraftProfileTexture minecraftProfileTexture = null;

    public HeadRenderer(final net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new HeadModel(), 0.3F);
    }

    @Override
    public void render(Head state, double x, double y, double z, float yaw, float pitch) {
        if (minecraftProfileTexture == null) {
            GameProfile profile = this.fetchGameProfile(CONFIG.headSkin);
            Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> textures =
                MinecraftClient.getInstance().getSkinProvider().getTextures(profile);
            if (textures != null) {
                minecraftProfileTexture = textures.get(MinecraftProfileTexture.Type.SKIN);
            }
            if (minecraftProfileTexture != null) {
                MinecraftClient.getInstance().getSkinProvider().loadSkin(minecraftProfileTexture, MinecraftProfileTexture.Type.SKIN);
            }
        }

        {
            // Scale the head down, alone with the skins ref
            com.mojang.blaze3d.platform.GlStateManager.scale(0.5f, 0.5f, 0.5f);
            super.render(state, x, y, z, yaw, pitch);
            com.mojang.blaze3d.platform.GlStateManager.scale(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public Identifier getTexture(Head state) {
        if (minecraftProfileTexture != null && minecraftProfileTexture.getUrl() != null) {
            return MinecraftClient.getInstance().getSkinProvider().loadSkin(minecraftProfileTexture, MinecraftProfileTexture.Type.SKIN);
        }
        return DefaultSkinHelper.getTexture(PlayerEntity.getOfflinePlayerUuid(CONFIG.headSkin));
    }

    private GameProfile fetchGameProfile(String playerName) {
        try {
            return MinecraftClient.getInstance().getSessionService().fillProfileProperties(
                new GameProfile(PlayerEntity.getOfflinePlayerUuid(playerName), playerName), true);
        } catch (Exception e) {
            return new GameProfile(PlayerEntity.getOfflinePlayerUuid(playerName), playerName);
        }
    }
}