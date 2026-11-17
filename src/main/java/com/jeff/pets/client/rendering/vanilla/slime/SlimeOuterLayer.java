//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.slime;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.SlimeEntityModel;
import net.minecraft.entity.LivingEntity;
import org.lwjgl.opengl.GL11;
import com.jeff.pets.client.rendering.PetRenderLayer;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.vanilla.hostile.ClientSlime;

public class SlimeOuterLayer implements PetRenderLayer {
    private final ClientSlimeRenderer parent;
    private final EntityModel model = new SlimeEntityModel(0);

    public SlimeOuterLayer(ClientSlimeRenderer parent) {
        this.parent = parent;
    }

    public void render(LivingEntity entity, float f, float g, float h, float i, float j, float k) {
        ClientSlime slimeEntity = (ClientSlime)entity;
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(2977);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        this.model.render(f, g, h, i, j, k);
        GlStateManager.disableBlend();
        GL11.glDisable(2977);
    }

    public boolean colorsWhenDamaged() {
        return true;
    }
}
