package com.jeff.pets.client.rendering.custom.aprilfools.head;

import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aprilfools.Head;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.ResourceLocation;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import static com.jeff.pets.client.Central.CONFIG;

public class HeadRenderer extends PetRenderer<Head, HeadModel> {

    private final Map<String, GameProfile> PROFILLES = new ConcurrentHashMap<>();

    public HeadRenderer(final RenderManager context, PetsClientInitializer.Context context2) {
        super(context, new HeadModel(), 0.3F);
    }

    private static CompletableFuture<Optional<GameProfile>> fetchGameProfile(String string) throws IllegalAccessException, NoSuchFieldException {
        java.lang.reflect.Field field = TileEntitySkull.class.getDeclaredField("profileCache");
        field.setAccessible(true);
        PlayerProfileCache loadingCache = (PlayerProfileCache) field.get(null);
        return loadingCache != null
                ? CompletableFuture.completedFuture(Optional.ofNullable(loadingCache.getGameProfileForUsername(string)))
                : CompletableFuture.completedFuture(Optional.empty());
    }

    @Override
    public ResourceLocation getEntityTexture(final Head state) {
        Minecraft minecraft = Minecraft.getInstance();
        try {
            Optional<GameProfile> gameProfile = fetchGameProfile(CONFIG.headSkin).get();
            if (!PROFILLES.containsKey(CONFIG.headSkin)) {
                PROFILLES.put(CONFIG.headSkin, gameProfile.get());
                MinecraftSessionService service = Minecraft.getInstance().getSessionService();
                service.fillProfileProperties(gameProfile.get(), true);
            }
            Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = minecraft.getSkinManager().loadSkinFromCache(gameProfile.get());
            if (map.containsKey(MinecraftProfileTexture.Type.SKIN)) {
                return minecraft.getSkinManager().loadSkin(map.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return DefaultPlayerSkin.getDefaultSkinLegacy();
    }
}
