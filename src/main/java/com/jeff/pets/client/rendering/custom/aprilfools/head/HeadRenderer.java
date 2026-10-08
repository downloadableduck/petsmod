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
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

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

    public HeadRenderer(final net.minecraft.client.renderer.entity.RenderManager context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new HeadModel(), 0.3F);
    }

    private GameProfile fetchGameProfile(Head head, String string) {
        YggdrasilAuthenticationService authService = new YggdrasilAuthenticationService(Proxy.NO_PROXY, UUID.randomUUID().toString());
        GameProfileRepository repository = authService.createProfileRepository();
        AtomicReference<GameProfile> result = new AtomicReference<>();
        repository.findProfilesByNames(new String[]{string}, Agent.MINECRAFT, new ProfileLookupCallback() {
            public void onProfileLookupSucceeded(GameProfile profile) {
                result.set(profile);
            }

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
        return Minecraft.getMinecraft().getSessionService().fillProfileProperties(result.get(), true);
    }

    public @NotNull ResourceLocation getEntityTexture(net.minecraft.entity.Entity __e) {
        Head state = (Head) __e;
        GameProfile profile = PROFILLES.get(CONFIG.headSkin);
        ResourceLocation ResourceLocation = net.minecraft.client.resources.SkinManager.DEFAULT_SKIN;
        if (profile == null) {
            profile = fetchGameProfile(state, CONFIG.headSkin);
        }
        if (profile.equals(dummyProfile)) {
            return ResourceLocation;
        }
        Minecraft minecraft = Minecraft.getMinecraft();
        Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = minecraft.getSkinManager().loadSkinFromCache(profile);
        if (map.containsKey(MinecraftProfileTexture.Type.SKIN)) {
            ResourceLocation = minecraft.getSkinManager().loadSkin((MinecraftProfileTexture) map.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN);
        } else {
            UUID uUID = profile.getId();
            ResourceLocation = net.minecraft.client.resources.SkinManager.DEFAULT_SKIN;
        }
        return ResourceLocation;
    }
}
