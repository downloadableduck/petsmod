package com.jeff.pets.client.rendering.custom.aprilfools.head;

import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aprilfools.Head;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.tileentity.SkullTileEntity;
import net.minecraft.util.ResourceLocation;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import static com.jeff.pets.client.Central.CONFIG;

public class HeadRenderer extends PetRenderer<Head, HeadModel> {

    private final Map<String, GameProfile> PROFILLES = new ConcurrentHashMap<>();

    public HeadRenderer(final EntityRendererManager context, PetsClientInitializer.Context context2) {
        super(context, new HeadModel(), 0.3F);
    }

    private static CompletableFuture<Optional<GameProfile>> fetchGameProfile(String string) {
        PlayerProfileCache loadingCache = SkullTileEntity.profileCache;
        return loadingCache != null
                ? CompletableFuture.completedFuture(Optional.ofNullable(loadingCache.get(string)))
                : CompletableFuture.completedFuture(Optional.empty());
    }

    @Override
    public ResourceLocation getTextureLocation(final Head state) {
        Minecraft minecraft = Minecraft.getInstance();
        try {
            Optional<GameProfile> gameProfile = fetchGameProfile(CONFIG.headSkin).get();
            if (!PROFILLES.containsKey(CONFIG.headSkin)) {
                PROFILLES.put(CONFIG.headSkin, gameProfile.get());
                MinecraftSessionService service = Minecraft.getInstance().getMinecraftSessionService();
                service.fillProfileProperties(gameProfile.get(), true);
            }
            Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = minecraft.getSkinManager().getInsecureSkinInformation(gameProfile.get());
            if (map.containsKey(MinecraftProfileTexture.Type.SKIN)) {
                return minecraft.getSkinManager().registerTexture(map.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return DefaultPlayerSkin.getDefaultSkin();
    }
}
