package com.jeff.pets.client.rendering.vanilla.ravager;

import com.jeff.pets.client.Utils;
import com.jeff.pets.mob.vanilla.hostile.ClientRavager;
import net.minecraft.client.render.model.Model;
import net.minecraft.client.render.model.ModelPart;
import net.minecraft.util.math.MathHelper;

public class ClientRavagerModel extends Model<ClientRavager> {
    private final ModelPart head;
    private final ModelPart mouth;
    private final ModelPart body;
    private final ModelPart leg0;
    private final ModelPart backRightLeg;
    private final ModelPart backLeftLeg;
    private final ModelPart frontRightLeg;
    private final ModelPart neck;

    public ClientRavagerModel() {
        this.f_35376783 /*textureWidth*/ = 128;
        this.f_50207596 /*textureHeight*/ = 128;
        int i = 16;
        float f = 0.0F;
        this.neck = new ModelPart(this);
        this.neck.setPos(0.0F, -7.0F, -1.5F);
        this.neck.setTextureCoords(68, 73).addBox(-5.0F, -1.0F, -18.0F, 10, 10, 18, 0.0F);
        this.head = new ModelPart(this);
        this.head.setPos(0.0F, 16.0F, -17.0F);
        this.head.setTextureCoords(0, 0).addBox(-8.0F, -20.0F, -14.0F, 16, 20, 16, 0.0F);
        this.head.setTextureCoords(0, 0).addBox(-2.0F, -6.0F, -18.0F, 4, 8, 4, 0.0F);
        ModelPart ModelPart = new ModelPart(this);
        ModelPart.setPos(-10.0F, -14.0F, -8.0F);
        ModelPart.setTextureCoords(74, 55).addBox(0.0F, -14.0F, -2.0F, 2, 14, 4, 0.0F);
        ModelPart.rotationX = 1.0995574F;
        this.head.addChild(ModelPart);
        ModelPart ModelPart2 = new ModelPart(this);
        ModelPart2.flipped = true;
        ModelPart2.setPos(8.0F, -14.0F, -8.0F);
        ModelPart2.setTextureCoords(74, 55).addBox(0.0F, -14.0F, -2.0F, 2, 14, 4, 0.0F);
        ModelPart2.rotationX = 1.0995574F;
        this.head.addChild(ModelPart2);
        this.mouth = new ModelPart(this);
        this.mouth.setPos(0.0F, -2.0F, 2.0F);
        this.mouth.setTextureCoords(0, 36).addBox(-8.0F, 0.0F, -16.0F, 16, 3, 16, 0.0F);
        this.head.addChild(this.mouth);
        this.neck.addChild(this.head);
        this.body = new ModelPart(this);
        this.body.setTextureCoords(0, 55).addBox(-7.0F, -10.0F, -7.0F, 14, 16, 20, 0.0F);
        this.body.setTextureCoords(0, 91).addBox(-6.0F, 6.0F, -7.0F, 12, 13, 18, 0.0F);
        this.body.setPos(0.0F, 1.0F, 2.0F);
        this.leg0 = new ModelPart(this, 96, 0);
        this.leg0.addBox(-4.0F, 0.0F, -4.0F, 8, 37, 8, 0.0F);
        this.leg0.setPos(-8.0F, -13.0F, 18.0F);
        this.backRightLeg = new ModelPart(this, 96, 0);
        this.backRightLeg.flipped = true;
        this.backRightLeg.addBox(-4.0F, 0.0F, -4.0F, 8, 37, 8, 0.0F);
        this.backRightLeg.setPos(8.0F, -13.0F, 18.0F);
        this.backLeftLeg = new ModelPart(this, 64, 0);
        this.backLeftLeg.addBox(-4.0F, 0.0F, -4.0F, 8, 37, 8, 0.0F);
        this.backLeftLeg.setPos(-8.0F, -13.0F, -5.0F);
        this.frontRightLeg = new ModelPart(this, 64, 0);
        this.frontRightLeg.flipped = true;
        this.frontRightLeg.addBox(-4.0F, 0.0F, -4.0F, 8, 37, 8, 0.0F);
        this.frontRightLeg.setPos(8.0F, -13.0F, -5.0F);
    }

    public void setup(ClientRavager ravager, float f, float g, float h, float i, float j, float s) {
        super.setup(ravager, f, g, h, i, j, s);
        this.head.rotationX = j * ((float) Math.PI / 180F);
        this.head.rotationY = i * ((float) Math.PI / 180F);
        this.body.rotationX = ((float) Math.PI / 2F);
        float k = 0.4F * g;
        this.leg0.rotationX = MathHelper.cos(f * 0.6662F) * k;
        this.backRightLeg.rotationX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * k;
        this.backLeftLeg.rotationX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * k;
        this.frontRightLeg.rotationX = MathHelper.cos(f * 0.6662F) * k;
    }

    @Override
    public void prepare(ClientRavager ravager, float f, float g, float h) {
        this.head.y = -0.5f;
        this.head.z = 0.5f;
        int i = 0;
        int j = 0;
        int k = 20;
        int l = 0;
        int m = 10;
        if (l > 0) {
            float n = Utils.triangleWave((float) l - h, 10.0F);
            float o = (1.0F + n) * 0.5F;
            float p = o * o * o * 12.0F;
            float q = p * MathHelper.sin(this.neck.rotationX);
            this.neck.z = -6.5F + p;
            this.neck.y = -7.0F - q;
            float r = MathHelper.sin(((float) l - h) / 10.0F * (float) Math.PI * 0.25F);
            this.mouth.rotationX = ((float) Math.PI / 2F) * r;
            if (l > 5) {
                this.mouth.rotationX = MathHelper.sin(((float) (-4 + l) - h) / 4.0F) * (float) Math.PI * 0.4F;
            } else {
                this.mouth.rotationX = 0.15707964F * MathHelper.sin((float) Math.PI * ((float) l - h) / 10.0F);
            }
        } else {
            float n = -1.0F;
            float o = -1.0F * MathHelper.sin(this.neck.rotationX);
            this.neck.x = 0.0F;
            this.neck.y = -7.0F - o;
            this.neck.z = 5.5F;
            boolean bl = i > 0;
            this.neck.rotationX = bl ? 0.21991149F : 0.0F;
            this.mouth.rotationX = (float) Math.PI * (bl ? 0.05F : 0.01F);
            if (bl) {
                double d = (double) i / (double) 40.0F;
                this.neck.x = (float) Math.sin(d * (double) 10.0F) * 3.0F;
            } else if (j > 0) {
                float q = MathHelper.sin(((float) (20 - j) - h) / 20.0F * (float) Math.PI * 0.25F);
                this.mouth.rotationX = ((float) Math.PI / 2F) * q;
            }
        }

    }

    @Override
    public void render(ClientRavager ravager, float f, float g, float h, float i, float j, float k) {
        super.render(ravager, f, g, h, i, j, k);
        this.body.render(k);
        this.head.render(k);
        this.mouth.render(k);
        this.leg0.render(k);
        this.backRightLeg.render(k);
        this.backLeftLeg.render(k);
        this.frontRightLeg.render(k);
    }
}
