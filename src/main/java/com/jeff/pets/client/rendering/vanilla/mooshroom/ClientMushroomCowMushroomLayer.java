//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.mooshroom;

import net.minecraft.core.block.Block;
import net.minecraft.core.entity.Mob;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.rendering.PetRenderLayer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.vanilla.passive.ClientMooshroom;

public class ClientMushroomCowMushroomLayer implements PetRenderLayer {
    private final PetRenderer renderer;

    public ClientMushroomCowMushroomLayer(PetRenderer renderer) {
        this.renderer = renderer;
    }

    public void render(Mob entity, float f, float g, float h, float i, float j, float k) {
        ClientMooshroom mooshroomEntity = (ClientMooshroom)entity;
        Block block = Central.CONFIG.mooshroomSkin.equals("brown") ? net.minecraft.core.block.Blocks.MUSHROOM_BROWN : net.minecraft.core.block.Blocks.MUSHROOM_RED;
        GlStateManager.enableCull();
        GlStateManager.cullFace(1029);
        GlStateManager.pushMatrix();
        GlStateManager.scalef(1.0F, -1.0F, 1.0F);
        GlStateManager.translatef(0.2F, 0.35F, 0.5F);
        GlStateManager.rotatef(42.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.pushMatrix();
        GlStateManager.translatef(-0.5F, -0.5F, 0.5F);
        // this.blockRenderer.render(block, 0, 1.0F);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translatef(0.1F, 0.0F, -0.6F);
        GlStateManager.rotatef(42.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.translatef(-0.5F, -0.5F, 0.5F);
        // this.blockRenderer.render(block, 0, 1.0F);
        GlStateManager.popMatrix();
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.scalef(1.0F, -1.0F, 1.0F);
        GlStateManager.translatef(0.0F, 0.7F, -0.2F);
        GlStateManager.rotatef(12.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.translatef(-0.5F, -0.5F, 0.5F);
        // this.blockRenderer.render(block, 0, 1.0F);
        GlStateManager.popMatrix();
        GlStateManager.cullFace(1028);
        GlStateManager.disableCull();
    }

    public boolean colorsWhenDamaged() {
        return false;
    }
}
