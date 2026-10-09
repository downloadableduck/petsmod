package com.jeff.pets.client.rendering.vanilla.mooshroom;

import com.jeff.pets.client.rendering.vanilla.cow.ClientCowModel;
import com.jeff.pets.mob.vanilla.passive.ClientMooshroom;
import org.lwjgl.opengl.GL11;
import net.minecraft.block.Block;
import net.minecraft.client.BlockRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.SpriteAtlasTexture;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientMushroomCowMushroomLayer {
    private final BlockRenderer blockRenderer;
    private final ClientMooshroomRenderer renderer;

    public ClientMushroomCowMushroomLayer(ClientMooshroomRenderer renderer) {
        this.renderer = renderer;
        this.blockRenderer = new BlockRenderer();
    }

    public void render(ClientMooshroom mooshroomEntity, float f, float g, float h, float i, float j, float k, float l) {
        if (!mooshroomEntity.isBaby() && !mooshroomEntity.isInvisible()) {
            MinecraftClient.getInstance().getTextureManager().bindTexture(SpriteAtlasTexture.BLOCK_ATLAS_TEX);
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glCullFace(1028);
            GL11.glPushMatrix();
            GL11.glScalef(1.0F, -1.0F, 1.0F);
            GL11.glTranslatef(0.2F, 0.35F, 0.5F);
            GL11.glRotatef(42.0F, 0.0F, 1.0F, 0.0F);
            BlockRenderer blockRenderManager = new BlockRenderer();
            GL11.glPushMatrix();
            GL11.glTranslatef(-0.5F, -0.5F, 0.5F);
;
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslatef(0.1F, 0.0F, -0.6F);
            GL11.glRotatef(42.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.5F, -0.5F, 0.5F);
;
            GL11.glPopMatrix();
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            ((ClientCowModel) this.renderer.getMainModel()).getHead().preRender(0.0625F);
            GL11.glScalef(1.0F, -1.0F, 1.0F);
            GL11.glTranslatef(0.0F, 0.7F, -0.2F);
            GL11.glRotatef(12.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.5F, -0.5F, 0.5F);
;
            GL11.glPopMatrix();
            GL11.glCullFace(1029);
            GL11.glDisable(GL11.GL_CULL_FACE);
        }
    }

    public boolean combineTextures() {
        return false;
    }
}