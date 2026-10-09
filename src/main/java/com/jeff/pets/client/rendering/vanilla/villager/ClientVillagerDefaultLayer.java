package com.jeff.pets.client.rendering.vanilla.villager;

import com.jeff.pets.mob.vanilla.passive.ClientVillager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import org.lwjgl.opengl.GL11;
import org.jetbrains.annotations.NotNull;

public class ClientVillagerDefaultLayer {

    private final ClientVillagerRenderer renderer;

    public ClientVillagerDefaultLayer(ClientVillagerRenderer renderer) {
        this.renderer = renderer;
    }

    public void render(@NotNull ClientVillager villager, float f, float g, float h, float i, float j, float k, float l) {
        GL11.glPushMatrix();
        MinecraftClient.getInstance().getTextureManager().bindTexture(new Identifier("minecraft", "textures/entity/villager/type/plains.png"));
        this.renderer.getMainModel().render(villager, f, g, i, j, k, l);
        GL11.glPopMatrix();
    }

    public boolean combineTextures() {
        return false;
    }
}