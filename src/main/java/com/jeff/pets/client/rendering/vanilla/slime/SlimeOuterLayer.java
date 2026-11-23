//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.slime;

import net.minecraft.core.entity.Mob;
import com.jeff.pets.client.rendering.PetRenderLayer;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.vanilla.hostile.ClientSlime;

public class SlimeOuterLayer implements PetRenderLayer {
    private final ClientSlimeRenderer parent;
    private final SlimeModel model = new SlimeModel(0);

    public SlimeOuterLayer(ClientSlimeRenderer parent) {
        this.parent = parent;
    }

    public void render(Mob entity, float f, float g, float h, float i, float j, float k) {
        ClientSlime slimeEntity = (ClientSlime)entity;
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableNormalize();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        this.model.render(f, g, h, i, j, k);
        GlStateManager.disableBlend();
        GlStateManager.disableNormalize();
    }

    public boolean colorsWhenDamaged() {
        return true;
    }
}
