package com.jeff.pets.client.rendering.vanilla.sheep;

import com.jeff.pets.client.rendering.PetLayer;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

/**
 * Wool pass over the sheep model. 1.7.10 has no {@code LayerRenderer}, so the overlay is
 * drawn from {@link PetRenderer#renderModel}; the 1.8 {@code setModelAttributes} call is
 * gone because 1.7.10 {@code ModelBase} has no such method and no separate fur geometry --
 * the same main model is re-rendered with the fur texture instead.
 */
public class ClientSheepWoolLayer implements PetLayer {
    private static final ResourceLocation SHEEP_FUR =
            new ResourceLocation("minecraft", "textures/entity/sheep/sheep_fur.png");

    private final PetRenderer parent;
    private int woolColor;

    public ClientSheepWoolLayer(RenderManager context, PetRenderer parent) {
        this.parent = parent;
    }

    private void resolveWoolColor() {
        String skin = CONFIG.sheepSkin;
        if (Objects.equals(skin, "white")) {
            woolColor = 15132390;
        } else if (Objects.equals(skin, "orange")) {
            woolColor = 12214293;
        } else if (Objects.equals(skin, "magenta")) {
            woolColor = 9779853;
        } else if (Objects.equals(skin, "light_blue")) {
            woolColor = 2852515;
        } else if (Objects.equals(skin, "yellow")) {
            woolColor = 12493357;
        } else if (Objects.equals(skin, "lime")) {
            woolColor = 6329623;
        } else if (Objects.equals(skin, "pink")) {
            woolColor = 11954303;
        } else if (Objects.equals(skin, "gray")) {
            woolColor = 3488573;
        } else if (Objects.equals(skin, "light_gray")) {
            woolColor = 7697777;
        } else if (Objects.equals(skin, "cyan")) {
            woolColor = 1078645;
        } else if (Objects.equals(skin, "purple")) {
            woolColor = 6694282;
        } else if (Objects.equals(skin, "blue")) {
            woolColor = 2962303;
        } else if (Objects.equals(skin, "brown")) {
            woolColor = 6438693;
        } else if (Objects.equals(skin, "green")) {
            woolColor = 4611344;
        } else if (Objects.equals(skin, "red")) {
            woolColor = 8659484;
        } else {
            woolColor = 1381656;
        }
    }

    @Override
    public void render(EntityLivingBase sheep, float limbSwing, float limbSwingAmount, float ageInTicks,
                       float netHeadYaw, float headPitch, float scale) {
        this.resolveWoolColor();

        float r = (woolColor >> 16 & 255) / 255.0F;
        float g = (woolColor >> 8 & 255) / 255.0F;
        float b = (woolColor & 255) / 255.0F;
        GL11.glColor3f(r, g, b);

        this.parent.bindTexture(SHEEP_FUR);
        this.parent.getMainModel().render(sheep, limbSwing, limbSwingAmount, ageInTicks,
                netHeadYaw, headPitch, scale);

        GL11.glColor3f(1.0F, 1.0F, 1.0F);
    }
}
