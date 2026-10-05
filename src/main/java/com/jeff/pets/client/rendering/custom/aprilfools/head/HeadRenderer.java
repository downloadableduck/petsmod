package com.jeff.pets.client.rendering.custom.aprilfools.head;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aprilfools.Head;
import com.mojang.authlib.Agent;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.ProfileLookupCallback;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.client.resource.Identifier;
import net.minecraft.server.GameProfileCache;
import org.jetbrains.annotations.NotNull;

import java.net.Proxy;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

import static com.jeff.pets.client.Central.CONFIG;

public class HeadRenderer extends PetRenderer<@NotNull Head> {

    /** 1.7.10 has no DefaultSkinUtils and no Alex skin; Steve is the only fallback. */
    private static final Identifier DEFAULT_SKIN = new Identifier("textures/entity/steve.png");

    private final Map<String, GameProfile> PROFILLES = new ConcurrentHashMap<>();
    private final GameProfile dummyProfile = new GameProfile(UUID.fromString("966b21b5-55d5-4a51-b41c-433a96e6050b"), "empty");

    public HeadRenderer(final net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new HeadModel(), 0.3F);
    }

    private GameProfile fetchGameProfile(Head head, String string) {
        YggdrasilAuthenticationService authService = new YggdrasilAuthenticationService(Proxy.NO_PROXY, UUID.randomUUID().toString());
        GameProfileRepository repository = authService.createProfileRepository();
        AtomicReference<GameProfile> result = new AtomicReference<>();
        repository.findProfilesByNames(new String[]{string}, Agent.MINECRAFT, new ProfileLookupCallback() {
            @Override
            public void onProfileLookupSucceeded(GameProfile profile) {
                result.set(profile);
            }

            @Override
            public void onProfileLookupFailed(GameProfile profile, Exception exception) {
                result.set(null);
            }
        });
        if (result.get() != null) {
            PROFILLES.put(CONFIG.headSkin, result.get());
        } else {
            PROFILLES.put(CONFIG.headSkin, dummyProfile);
            return dummyProfile;
        }
        return Minecraft.getInstance().getSessionService().fillProfileProperties(result.get(), true);
    }

    @Override
    public @NotNull Identifier getTextureLocation(net.minecraft.entity.Entity entity) {
        Head state = (Head) entity;
        GameProfile profile = PROFILLES.get(CONFIG.headSkin);
        Identifier identifier = DEFAULT_SKIN;
        if (profile == null) {
            profile = fetchGameProfile(state, CONFIG.headSkin);
        }
        if (profile.equals(dummyProfile)) {
            return identifier;
        }
        Minecraft minecraft = Minecraft.getInstance();
        Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = minecraft.getSkinManager().getTextures(profile);
        if (map.containsKey(MinecraftProfileTexture.Type.SKIN)) {
            identifier = minecraft.getSkinManager().register(map.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN);
        } else {
            UUID uUID = PlayerEntity.getUuid(profile);
            identifier = DEFAULT_SKIN;
        }
        return identifier;
    }
}
