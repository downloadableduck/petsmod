package com.jeff.pets.client.rendering.vanilla.sheep;

import com.jeff.pets.mob.vanilla.passive.ClientSheep;
import net.minecraft.client.render.entity.layer.SheepFurLayer;
import net.minecraft.client.render.platform.GlStateManager;
import net.minecraft.client.render.entity.layer.EntityRenderLayer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.model.Model;
import net.minecraft.resource.Identifier;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSheepWoolLayer implements EntityRenderLayer<ClientSheep> {
    private final ClientSheepRenderer renderer;
    private final ClientSheepFurModel model;
    int woolColor;

    public ClientSheepWoolLayer(ClientSheepRenderer renderer) {
        this.renderer = renderer;
        this.model = new ClientSheepFurModel();
    }

    @Override
<<<<<<< HEAD
    public void render(ClientSheep sheep, float f, float a, float h, float i, float j, float k, float l) {
        this.renderer.bindTexture(new Identifier("minecraft", "textures/entity/sheep/sheep_fur.png"));
        if (Objects.equals(CONFIG.sheepSkin, "white")) {
=======
    public void render(ClientSheep sheep, float p_212842_2_, float p_212842_3_, float p_212842_4_, float p_212842_5_, float p_212842_6_, float p_212842_7_, float p_212842_8_) {
        this.renderer.bindTexture(new ResourceLocation("minecraft", "textures/entity/sheep/sheep_fur.png"));
        if (Objects.equals(sheep.petSkin, "white")) {
>>>>>>> 908063cb (backport the 0.8.x updates to 1.8.9, as well as allow users to place and break blocks on top of the pet)
            woolColor = 15132390;
        } else if (Objects.equals(sheep.petSkin, "orange")) {
            woolColor = 12214293;
        } else if (Objects.equals(sheep.petSkin, "magenta")) {
            woolColor = 9779853;
        } else if (Objects.equals(sheep.petSkin, "light_blue")) {
            woolColor = 2852515;
        } else if (Objects.equals(sheep.petSkin, "yellow")) {
            woolColor = 12493357;
        } else if (Objects.equals(sheep.petSkin, "lime")) {
            woolColor = 6329623;
        } else if (Objects.equals(sheep.petSkin, "pink")) {
            woolColor = 11954303;
        } else if (Objects.equals(sheep.petSkin, "gray")) {
            woolColor = 3488573;
        } else if (Objects.equals(sheep.petSkin, "light_gray")) {
            woolColor = 7697777;
        } else if (Objects.equals(sheep.petSkin, "cyan")) {
            woolColor = 1078645;
        } else if (Objects.equals(sheep.petSkin, "purple")) {
            woolColor = 6694282;
        } else if (Objects.equals(sheep.petSkin, "blue")) {
            woolColor = 2962303;
        } else if (Objects.equals(sheep.petSkin, "brown")) {
            woolColor = 6438693;
        } else if (Objects.equals(sheep.petSkin, "green")) {
            woolColor = 4611344;
        } else if (Objects.equals(sheep.petSkin, "red")) {
            woolColor = 8659484;
        } else if (Objects.equals(sheep.petSkin, "black")) {
            woolColor = 1381656;
        } else {
            woolColor = 1381656;
        }

        float r = (float) (woolColor >> 16 & 255) / 255.0F;
        float g = (float) (woolColor >> 8 & 255) / 255.0F;
        float b = (float) (woolColor & 255) / 255.0F;
            GlStateManager.color4f(r, g, b, 1.0F);
            this.model.isBaby = false;

            this.model.copyPropertiesFrom(this.renderer.getModel());
            this.model.prepare(sheep, f, a, h);
            this.model.render(sheep, f, a, i, j, k, l);
    }

    @Override
    public boolean colorsWhenDamaged() {
        return false;
    }
}
