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
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import java.net.Proxy;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

import static com.jeff.pets.client.Central.CONFIG;

public class HeadRenderer extends PetRenderer {

    private final Map<String, GameProfile> PROFILLES = new ConcurrentHashMap<>();
    private final GameProfile dummyProfile = new GameProfile(UUID.fromString("966b21b5-55d5-4a51-b41c-433a96e6050b"), "empty");

    public HeadRenderer() {
        super(new HeadModel(), 0.3F);
    }

    private GameProfile fetchGameProfile(Entity head, String string) {
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
        return Minecraft.getMinecraft().func_152347_ac().fillProfileProperties(result.get(), true);
    }

    @Override
    public ResourceLocation getEntityTexture(final Entity state) {
        GameProfile profile = PROFILLES.get(CONFIG.headSkin);
        // 1.7.10 has no DefaultPlayerSkin helper; AbstractClientPlayer.locationStevePng is the
        // same default-steve fallback that getDefaultSkinLegacy() returns in 1.8.
        ResourceLocation identifier = AbstractClientPlayer.locationStevePng;
        if (profile == null) {
            profile = fetchGameProfile(state, CONFIG.headSkin);
        }
        if (profile.equals(dummyProfile)) {
            return identifier;
        }
        Minecraft minecraft = Minecraft.getMinecraft();
        Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = minecraft.func_152342_ad().func_152788_a(profile);
        if (map.containsKey(MinecraftProfileTexture.Type.SKIN)) {
            identifier = minecraft.func_152342_ad().func_152792_a(map.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN);
        } else {
            // getDefaultSkin(UUID) has no 1.7.10 counterpart -- 1.7.10 has no per-UUID
            // default skin variants, so fall back to the same default steve texture.
            identifier = AbstractClientPlayer.locationStevePng;
        }
        return identifier;
    }
}
