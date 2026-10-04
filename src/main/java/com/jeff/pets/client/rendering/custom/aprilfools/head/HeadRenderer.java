package com.jeff.pets.client.rendering.custom.aprilfools.head;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.custom.aprilfools.Head;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.resource.Identifier;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static com.jeff.pets.client.Central.CONFIG;

public class HeadRenderer extends PetRenderer<Head> {

    private final Map<String, GameProfile> PROFILLES = new ConcurrentHashMap<>();

    public HeadRenderer(final EntityRenderDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new HeadModel(), 0.3F);
    }

    @Override
    public Identifier getTextureLocation(Head entity) {
        entity.petSkin = CONFIG.headSkin;
        if (entity.petSkin.equals(Minecraft.getInstance().player.getGameProfile().getName())) {
            return Minecraft.getInstance().player.getSkinTextureLocation();
        }
        if (!entity.isLoading) {
            this.fetchSkin(entity);
        }
        return entity.skin;
    }

    private void fetchSkin(Head entity) {
        new Thread(() -> {
            GameProfile gameProfile = PROFILLES.get(entity.petSkin);
            if (gameProfile == null) {
                UUID uuid = null;
                try {
                    HttpURLConnection stream = (HttpURLConnection) new URL("https://api.mojang.com/users/profiles/minecraft/" + entity.petSkin).openConnection();
                    stream.setRequestMethod("GET");
                    if (stream.getResponseCode() == 200) {
                        InputStreamReader reader = new InputStreamReader(stream.getInputStream());
                        JsonObject object = JsonParser.parseReader(reader).getAsJsonObject();
                        String response = object.get("id").getAsString();
                        uuid = UUID.fromString(response.replaceAll(
                                "(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})",
                                "$1-$2-$3-$4-$5"
                        ));
                    }

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                GameProfile profile = Minecraft.getInstance().getSessionService().fillProfileProperties(new GameProfile(uuid, entity.petSkin), true);
                PROFILLES.put(entity.petSkin, profile);
                Minecraft lvt_11_1_ = Minecraft.getInstance();
                if (profile != null) {
                    lvt_11_1_.execute(() -> {
                        Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> lvt_12_1_ = lvt_11_1_.getSkinManager().getTextures(profile);
                        if (lvt_12_1_.containsKey(MinecraftProfileTexture.Type.SKIN)) {
                            entity.skin = (lvt_11_1_.getSkinManager().register(lvt_12_1_.get(MinecraftProfileTexture.Type.SKIN), MinecraftProfileTexture.Type.SKIN));
                        } else {
                            lvt_11_1_.getSkinManager().register(
                                    profile,
                                    (type, location, profileTexture) -> {
                                        if (type == MinecraftProfileTexture.Type.SKIN) {
                                            entity.skin = location;
                                        }
                                    },
                                    true
                            );
                        }
                    });
                }
            }
            entity.isLoading = true;
        }, "petsmod-thread").start();
    }
}
