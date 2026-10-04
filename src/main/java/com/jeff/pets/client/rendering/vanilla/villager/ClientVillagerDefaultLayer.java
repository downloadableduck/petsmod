package com.jeff.pets.client.rendering.vanilla.villager;

import com.jeff.pets.client.rendering.PetLayer;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Undead villager "default"/type overlay. 1.7.10 has no {@code LayerRenderer}, so this is
 * driven from {@link PetRenderer#renderModel} and re-renders the parent's main model.
 */
public class ClientVillagerDefaultLayer implements PetLayer {
    private static final ResourceLocation PLAINS =
            new ResourceLocation("minecraft", "textures/entity/villager/type/plains.png");

    private final PetRenderer parent;

    public ClientVillagerDefaultLayer(RenderManager context, PetRenderer parent) {
        this.parent = parent;
    }

    @Override
    public void render(EntityLivingBase villager, float limbSwing, float limbSwingAmount, float ageInTicks,
                       float netHeadYaw, float headPitch, float scale) {
        GL11.glPushMatrix();
        this.parent.bindTexture(PLAINS);
        this.parent.getMainModel().render(villager, limbSwing, limbSwingAmount, ageInTicks,
                netHeadYaw, headPitch, scale);
        GL11.glPopMatrix();
    }
}
