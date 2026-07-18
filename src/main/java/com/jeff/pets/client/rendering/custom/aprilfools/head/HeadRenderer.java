package com.jeff.pets.client.rendering.custom.aprilfools.head;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aprilfools.Head;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.DefaultSkinHelper;
import net.minecraft.util.Identifier;
import net.minecraft.util.UserCache;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import static com.jeff.pets.client.Central.CONFIG;

public class HeadRenderer extends PetRenderer<@NotNull Head, @NotNull HeadModel> {

    private final Map<String, GameProfile> PROFILLES = new ConcurrentHashMap<>();

    public HeadRenderer(final net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new HeadModel(), 0.3F);
    }

    private static CompletableFuture<Optional<GameProfile>> fetchGameProfile(String string) {
        UserCache loadingCache = SkullBlockEntity.userCache;
        return loadingCache != null
                ? CompletableFuture.completedFuture(Optional.ofNullable(loadingCache.findByName(string)))
                : CompletableFuture.completedFuture(Optional.empty());
    }

    @Override
    public @NotNull Identifier getTexture(final Head state) {
        MinecraftClient minecraft = MinecraftClient.getInstance();
        try {
            Optional<GameProfile> gameProfile = fetchGameProfile(CONFIG.headSkin).get();
            if (!PROFILLES.containsKey(CONFIG.headSkin)) {
                PROFILLES.put(CONFIG.headSkin, gameProfile.get());
                MinecraftSessionService service = MinecraftClient.getInstance().getSessionService();
                service.fillProfileProperties(gameProfile.get(), true);
            }
            Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = minecraft.getSkinProvider().getTextures(gameProfile.get());
            if (map.containsKey(MinecraftProfileTexture.Type.SKIN)) {
                return minecraft.getSkinProvider().loadSkin(map.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return DefaultSkinHelper.getTexture();
    }
}
