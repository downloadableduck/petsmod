package com.jeff.pets.client.rendering.vanilla.villager;

import net.minecraft.src.EntityLiving;
import com.jeff.pets.client.rendering.PetLayer;
import com.jeff.pets.client.rendering.PetRenderer;
import org.lwjgl.opengl.GL11;

/**
 * Undead villager "default"/type overlay. 1.7.10 has no {@code LayerRenderer}, so this is
 * driven from {@link PetRenderer#renderModel} and re-renders the parent's main model.
 */
public class ClientVillagerDefaultLayer implements PetLayer {
    static final String PLAINS =
            "textures/entity/villager/type/plains.png";

    private final PetRenderer parent;

    public ClientVillagerDefaultLayer(PetRenderer parent) {
        this.parent = parent;
    }

    @Override
    public void render(EntityLiving villager, float limbSwing, float limbSwingAmount, float ageInTicks,
                       float netHeadYaw, float headPitch, float scale) {
        GL11.glPushMatrix();
        this.parent.bindTexturePublic(PLAINS);
        this.parent.getMainModel().render(villager, limbSwing, limbSwingAmount, ageInTicks,
                netHeadYaw, headPitch, scale);
        GL11.glPopMatrix();
    }
}
