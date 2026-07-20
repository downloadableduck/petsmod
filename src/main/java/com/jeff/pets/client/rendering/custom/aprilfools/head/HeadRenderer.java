package com.jeff.pets.client.rendering.custom.aprilfools.head;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aprilfools.Head;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resource.skin.DefaultSkinUtils;
import net.minecraft.resource.Identifier;
import net.minecraft.server.GameProfileCache;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import static com.jeff.pets.client.Central.CONFIG;

public class HeadRenderer extends PetRenderer<@NotNull Head, @NotNull HeadModel> {

    private final Map<String, GameProfile> PROFILLES = new ConcurrentHashMap<>();

    public HeadRenderer(final net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new HeadModel(), 0.3F);
    }

    private static CompletableFuture<Optional<GameProfile>> fetchGameProfile(String string) {
        GameProfileCache loadingCache = SkullBlockEntity.playerCache;
        return loadingCache != null
                ? CompletableFuture.completedFuture(Optional.ofNullable(loadingCache.get(string)))
                : CompletableFuture.completedFuture(Optional.empty());
    }

    @Override
    public @NotNull Identifier getTextureLocation(final Head state) {
        Minecraft minecraft = Minecraft.getInstance();
        try {
            Optional<GameProfile> gameProfile = fetchGameProfile(CONFIG.headSkin).get();
            if (!PROFILLES.containsKey(CONFIG.headSkin)) {
                PROFILLES.put(CONFIG.headSkin, gameProfile.get());
                MinecraftSessionService service = Minecraft.getInstance().getSessionService();
                service.fillProfileProperties(gameProfile.get(), true);
            }
            Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = minecraft.getSkinManager().getTextures(gameProfile.get());
            if (map.containsKey(MinecraftProfileTexture.Type.SKIN)) {
                return minecraft.getSkinManager().register(map.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return DefaultSkinUtils.getDefaultSkin();
    }
}
