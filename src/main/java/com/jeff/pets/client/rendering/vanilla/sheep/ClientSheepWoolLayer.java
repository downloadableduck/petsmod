//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.sheep;

import net.minecraft.entity.LivingEntity;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.rendering.PetRenderLayer;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.vanilla.passive.ClientSheep;

import java.util.Objects;

public class ClientSheepWoolLayer implements PetRenderLayer {
    private final ClientSheepRenderer renderer;
    private final ClientSheepFurModel model;
    int woolColor;

    public ClientSheepWoolLayer(ClientSheepRenderer renderer) {
        this.renderer = renderer;
        this.model = new ClientSheepFurModel();
    }

    public void render(LivingEntity entity, float f, float a, float h, float i, float j, float k) {
        ClientSheep sheep = (ClientSheep)entity;
        this.renderer.bindTexture("textures/mob/sheep/sheep_fur.png");
        if (Objects.equals(Central.CONFIG.sheepSkin, "white")) {
            this.woolColor = 15132390;
        } else if (Objects.equals(Central.CONFIG.sheepSkin, "orange")) {
            this.woolColor = 12214293;
        } else if (Objects.equals(Central.CONFIG.sheepSkin, "magenta")) {
            this.woolColor = 9779853;
        } else if (Objects.equals(Central.CONFIG.sheepSkin, "light_blue")) {
            this.woolColor = 2852515;
        } else if (Objects.equals(Central.CONFIG.sheepSkin, "yellow")) {
            this.woolColor = 12493357;
        } else if (Objects.equals(Central.CONFIG.sheepSkin, "lime")) {
            this.woolColor = 6329623;
        } else if (Objects.equals(Central.CONFIG.sheepSkin, "pink")) {
            this.woolColor = 11954303;
        } else if (Objects.equals(Central.CONFIG.sheepSkin, "gray")) {
            this.woolColor = 3488573;
        } else if (Objects.equals(Central.CONFIG.sheepSkin, "light_gray")) {
            this.woolColor = 7697777;
        } else if (Objects.equals(Central.CONFIG.sheepSkin, "cyan")) {
            this.woolColor = 1078645;
        } else if (Objects.equals(Central.CONFIG.sheepSkin, "purple")) {
            this.woolColor = 6694282;
        } else if (Objects.equals(Central.CONFIG.sheepSkin, "blue")) {
            this.woolColor = 2962303;
        } else if (Objects.equals(Central.CONFIG.sheepSkin, "brown")) {
            this.woolColor = 6438693;
        } else if (Objects.equals(Central.CONFIG.sheepSkin, "green")) {
            this.woolColor = 4611344;
        } else if (Objects.equals(Central.CONFIG.sheepSkin, "red")) {
            this.woolColor = 8659484;
        } else if (Objects.equals(Central.CONFIG.sheepSkin, "black")) {
            this.woolColor = 1381656;
        } else {
            this.woolColor = 1381656;
        }

        float r = (float)(this.woolColor >> 16 & 255) / 255.0F;
        float g = (float)(this.woolColor >> 8 & 255) / 255.0F;
        float b = (float)(this.woolColor & 255) / 255.0F;
        GlStateManager.color4f(r, g, b, 1.0F);
        this.model.isBaby = false;
        this.model.animateModel(sheep, f, a, h);
        this.model.render(sheep, f, a, h, i, j, k);
    }

    public boolean colorsWhenDamaged() {
        return false;
    }
}
