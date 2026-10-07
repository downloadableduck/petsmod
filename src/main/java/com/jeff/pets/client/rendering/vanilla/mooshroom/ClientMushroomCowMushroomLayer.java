package com.jeff.pets.client.rendering.vanilla.mooshroom;

import com.jeff.pets.client.rendering.PetLayer;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import org.lwjgl.opengl.GL11;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

/**
 * Draws the mushroom caps on a mooshroom's back and head. 1.7.10 has no
 * {@code LayerRenderer}, no {@code BlockRendererDispatcher} and no {@code IBlockState};
 * vanilla 1.7.10's {@code RenderMooshroom#renderEquippedItems} instead calls
 * {@code RenderBlocks#renderBlockAsItem}, which is what this mirrors.
 */
public class ClientMushroomCowMushroomLayer implements PetLayer {
    private final PetRenderer parent;

    public ClientMushroomCowMushroomLayer(PetRenderer parent) {
        this.parent = parent;
    }

    @Override
    public void render(EntityLivingBase mooshroom, float limbSwing, float limbSwingAmount, float ageInTicks,
                       float netHeadYaw, float headPitch, float scale) {
        if (mooshroom.isChild() || mooshroom.isInvisible()) {
            return;
        }

        Block mushroom = Objects.equals(CONFIG.mooshroomSkin, "brown") ? Blocks.brown_mushroom : Blocks.red_mushroom;

        this.parent.bindTexturePublic(TextureMap.locationBlocksTexture);
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_FRONT);

        GL11.glPushMatrix();
        GL11.glScalef(1.0F, -1.0F, 1.0F);
        GL11.glTranslatef(0.2F, 0.35F, 0.5F);
        GL11.glRotatef(42.0F, 0.0F, 1.0F, 0.0F);
        this.parent.getRenderBlocks().renderBlockAsItem(mushroom, 0, 1.0F);
        GL11.glTranslatef(0.1F, 0.0F, -0.6F);
        GL11.glRotatef(42.0F, 0.0F, 1.0F, 0.0F);
        this.parent.getRenderBlocks().renderBlockAsItem(mushroom, 0, 1.0F);
        GL11.glPopMatrix();

        GL11.glPushMatrix();
        ((ModelCow) this.parent.getMainModel()).head.postRender(0.0625F);
        GL11.glScalef(1.0F, -1.0F, 1.0F);
        GL11.glTranslatef(0.0F, 0.7F, -0.2F);
        GL11.glRotatef(12.0F, 0.0F, 1.0F, 0.0F);
        this.parent.getRenderBlocks().renderBlockAsItem(mushroom, 0, 1.0F);
        GL11.glPopMatrix();

        GL11.glCullFace(GL11.GL_BACK);
        GL11.glDisable(GL11.GL_CULL_FACE);
    }
}
