package com.jeff.pets.client.rendering.vanilla.mooshroom;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.cow.ClientCowModel;
import com.jeff.pets.mob.vanilla.passive.ClientMooshroom;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientMooshroomRenderer extends PetRenderer<@NotNull ClientMooshroom, @NotNull ClientCowModel<ClientMooshroom>> {

    String mooshroomTexturePath;

    public ClientMooshroomRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, net.fabricmc.fabric.api.client.render.EntityRendererRegistry.Context context2) {
        super(context, new ClientCowModel<>(), 0.7F);
        this.addFeature(new ClientMushroomCowMushroomLayer(this, MinecraftClient.getInstance().getBlockRenderManager()));
    }

    @Override
    protected void scale(ClientMooshroom state, float f) {
        if (CONFIG.isBaby) {
            com.mojang.blaze3d.platform.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTexture(ClientMooshroom cowRenderState) {
        if (Objects.equals(CONFIG.mooshroomSkin, "red")) {
            mooshroomTexturePath = "textures/entity/cow/red_mooshroom.png";
        } else if (Objects.equals(CONFIG.mooshroomSkin, "brown")) {
            mooshroomTexturePath = "textures/entity/cow/brown_mooshroom.png";
        } else {
            mooshroomTexturePath = "textures/entity/cow/red_mooshroom.png";
        }
        return new Identifier("minecraft", mooshroomTexturePath);
    }
}
