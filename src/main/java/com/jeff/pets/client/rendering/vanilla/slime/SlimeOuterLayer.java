//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.slime;

import com.jeff.pets.client.rendering.PetRenderLayer;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.vanilla.hostile.ClientSlime;
import com.jeff.pets.client.rendering.PetModel;
import net.minecraft.client.render.model.Model;
import net.minecraft.client.render.model.entity.SlimeModel;


public class SlimeOuterLayer implements PetRenderLayer {
    private final ClientSlimeRenderer parent;
    private final Model model = new SlimeModel(0);

    public SlimeOuterLayer(ClientSlimeRenderer parent) {
        this.parent = parent;
    }

    public void render(net.minecraft.entity.mob.MobEntity entity, float f, float g, float h, float i, float j, float k) {
        ClientSlime slimeEntity = (ClientSlime) entity;
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            org.lwjgl.opengl.GL11.glEnable(2977); // GL_NORMALIZE
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(770, 771);
            
            this.model.render(f, g, h, i, j, k);
            GlStateManager.disableBlend();
            org.lwjgl.opengl.GL11.glDisable(2977); // GL_NORMALIZE
    }

    public boolean colorsWhenDamaged() {
        return true;
    }
}
