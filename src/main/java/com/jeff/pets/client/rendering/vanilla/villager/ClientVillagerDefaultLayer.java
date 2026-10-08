package com.jeff.pets.client.rendering.vanilla.villager;

import com.jeff.pets.mob.vanilla.passive.ClientVillager;
import org.lwjgl.opengl.GL11;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.Minecraft;
import org.jetbrains.annotations.NotNull;

public class ClientVillagerDefaultLayer {

    private final ClientVillagerRenderer renderer;

    public ClientVillagerDefaultLayer(ClientVillagerRenderer renderer) {
        this.renderer = renderer;
    }

    public void render(@NotNull ClientVillager villager, float f, float g, float h, float i, float j, float k, float l) {
        GL11.glPushMatrix();
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("minecraft", "textures/entity/villager/type/plains.png"));
        this.renderer.getMainModel().render(villager, f, g, i, j, k, l);
        GL11.glPopMatrix();
    }

    public boolean combineTextures() {
        return false;
    }
}