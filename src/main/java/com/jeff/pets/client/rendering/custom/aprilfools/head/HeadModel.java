package com.jeff.pets.client.rendering.custom.aprilfools.head;
// Exported for Minecraft version 1.15 - 1.16 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.jeff.pets.client.rendering.PetModel;
import net.minecraft.client.render.model.Box;
import net.minecraft.client.render.model.Model; import com.jeff.pets.client.rendering.PetModel;
import net.minecraft.client.render.model.ModelPart;

public class HeadModel extends PetModel {
    private final ModelPart Head;

    public HeadModel() {
        textureWidth = 64;
        textureHeight = 64;

        Head = new ModelPart(this);
        Head.setPos(5.0F, 0.0F, -5.0F);
        Head.boxes.add(new Box(Head, 0, 0, -8.0F, 16.0F, 0.0F, 8, 8, 8, 0.0F));
        Head.boxes.add(new Box(Head, 32, 0, -8.0F, 16.0F, 0.0F, 8, 8, 8, 0.5F));
    }

    @Override
    public void render(net.minecraft.entity.Entity entity, float f, float g, float h, float i, float j, float alpha) {
        Head.render(alpha);
    }

    public void setRotationAngle(ModelPart ModelPart, float x, float y, float z) {
        ModelPart.rotationX = x;
        ModelPart.rotationY = y;
        ModelPart.rotationZ = z;
    }
}