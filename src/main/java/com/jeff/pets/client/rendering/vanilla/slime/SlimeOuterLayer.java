//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.slime;

import com.jeff.pets.mob.vanilla.hostile.ClientSlime;

import net.minecraft.client.render.entity.SlimeRenderer;
import com.jeff.pets.client.rendering.PetRenderLayer;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.render.model.Model;
import net.minecraft.client.render.model.entity.SlimeModel;
import com.jeff.pets.compat.GlStateManager;
import net.minecraft.entity.living.mob.monster.SlimeEntity;


public class SlimeOuterLayer implements PetRenderLayer {
    private final ClientSlimeRenderer parent;
    private final Model model = new SlimeModel(0);

    public SlimeOuterLayer(ClientSlimeRenderer parent) {
        this.parent = parent;
    }

    public void render(net.minecraft.entity.living.LivingEntity entity, float f, float g, float h, float i, float j, float k) {
        ClientSlime slimeEntity = (ClientSlime) entity;
        if (!slimeEntity.isInvisible()) {
            GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.enableNormalize();
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(770, 771);
            
            this.model.render(slimeEntity, f, g, h, i, j, k);
            GlStateManager.disableBlend();
            GlStateManager.disableNormalize();
        }
    }

    public boolean colorsWhenDamaged() {
        return true;
    }
}
