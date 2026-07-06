package com.jeff.pets.rendering.custom.aprilfools.head;

import com.google.common.cache.LoadingCache;
import com.jeff.pets.mob.custom.aprilfools.Head;
import com.jeff.pets.rendering.PetRenderer;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.core.UUIDUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.players.GameProfileCache;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import static com.jeff.pets.Central.CONFIG;

public class HeadRenderer extends PetRenderer<@NotNull Head, @NotNull HeadModel> {

    private Map<String, GameProfile> PROFILLES = new ConcurrentHashMap<>();

    public HeadRenderer(final EntityRendererProvider.Context context) {
        super(context, new HeadModel(context.bakeLayer(HeadModel.LAYER_LOCATION)), 0.3F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(final Head state) {
        Minecraft minecraft = Minecraft.getInstance();
        try {
            Optional<GameProfile> gameProfile = fetchGameProfile(CONFIG.headSkin).get();
            if (!PROFILLES.containsKey(CONFIG.headSkin)) {
                PROFILLES.put(CONFIG.headSkin, gameProfile.get());
                MinecraftSessionService service = Minecraft.getInstance().getMinecraftSessionService();
                service.fillProfileProperties(gameProfile.get(), true);
            }
            return minecraft.getSkinManager().getInsecureSkinLocation(gameProfile.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static CompletableFuture<Optional<GameProfile>> fetchGameProfile(String string) {
        GameProfileCache loadingCache = SkullBlockEntity.profileCache;
        return loadingCache != null
                ? CompletableFuture.completedFuture(loadingCache.get(string))
                : CompletableFuture.completedFuture(Optional.empty());
    }
}
