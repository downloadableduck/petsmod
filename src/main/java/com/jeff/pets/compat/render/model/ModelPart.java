package com.jeff.pets.compat.render.model;

import com.jeff.pets.compat.GlStateManager;

import java.util.ArrayList;
import java.util.List;

/**
 * b1.8-compatible ModelPart shim supporting hierarchy, multiple boxes, and newer API used by this mod.
 */
public class ModelPart {
    public float textureWidth = 64.0F;
    public float textureHeight = 32.0F;
    private int u;
    private int v;
    public float x;
    public float y;
    public float z;
    public float rotationX;
    public float rotationY;
    public float rotationZ;
    public boolean flipped;
    public boolean visible = true;
    public boolean invisible;

    public final List<ModelPart> children = new ArrayList<>();
    private final List<Box> boxes = new ArrayList<>();

    public ModelPart(Model model) {
        if (model != null) {
            this.textureWidth = model.textureWidth;
            this.textureHeight = model.textureHeight;
            model.parts.add(this);
        }
    }

    public ModelPart(Model model, String name) {
        this(model);
    }

    public ModelPart(Model model, int u, int v) {
        this(model);
        this.u = u;
        this.v = v;
    }

    public void addChild(ModelPart child) {
        if (child != null && !this.children.contains(child)) {
            this.children.add(child);
        }
    }

    public ModelPart setTextureCoords(int u, int v) {
        this.u = u;
        this.v = v;
        return this;
    }

    // named box (used by some 1.2+ models); ignore name in b1.8 compat
    public ModelPart addBox(String name, float x, float y, float z, int w, int h, int d) {
        return this.addBox(x, y, z, w, h, d);
    }

    public ModelPart addBox(String name, float x, float y, float z, int w, int h, int d, float inflate) {
        this.addBox(x, y, z, w, h, d, inflate);
        return this;
    }

    public ModelPart addBox(float x, float y, float z, int w, int h, int d) {
        this.addBox(x, y, z, w, h, d, 0.0F);
        return this;
    }

    public void addBox(float x, float y, float z, int w, int h, int d, float inflate) {
        this.boxes.add(new Box(this.u, this.v, x, y, z, w, h, d, inflate, this.textureWidth, this.textureHeight, this.flipped));
    }

    public void setPos(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void render(float scale) {
        if (!this.visible) return;
        if (this.invisible) return;

        GlStateManager.pushMatrix();
        GlStateManager.translatef(this.x * scale, this.y * scale, this.z * scale);
        if (this.rotationZ != 0.0F) {
            GlStateManager.rotatef(this.rotationZ * (180F / (float)Math.PI), 0.0F, 0.0F, 1.0F);
        }
        if (this.rotationY != 0.0F) {
            GlStateManager.rotatef(this.rotationY * (180F / (float)Math.PI), 0.0F, 1.0F, 0.0F);
        }
        if (this.rotationX != 0.0F) {
            GlStateManager.rotatef(this.rotationX * (180F / (float)Math.PI), 1.0F, 0.0F, 0.0F);
        }

        for (Box box : this.boxes) {
            box.render(scale);
        }
        for (ModelPart child : this.children) {
            child.render(scale);
        }
        GlStateManager.popMatrix();
    }

    public ModelPart setTextureSize(int w, int h) {
        this.textureWidth = (float) w;
        this.textureHeight = (float) h;
        return this;
    }

    public void setPositionAndRotation(ModelPart other) {
        this.x = other.x; this.y = other.y; this.z = other.z;
        this.rotationX = other.rotationX; this.rotationY = other.rotationY; this.rotationZ = other.rotationZ;
    }

    private static class Box {
        final float x1,y1,z1,x2,y2,z2;
        final int u,v,w,h,d;
        final float tw,th;
        final boolean flipped;

        Box(int u, int v, float x, float y, float z, int w, int h, int d, float inflate, float tw, float th, boolean flipped) {
            this.u = u; this.v = v; this.w = w; this.h = h; this.d = d; this.tw = tw; this.th = th; this.flipped = flipped;
            this.x1 = x - inflate; this.y1 = y - inflate; this.z1 = z - inflate;
            this.x2 = x + w + inflate; this.y2 = y + h + inflate; this.z2 = z + d + inflate;
        }

        void render(float scale) {
            // Draw a textured cuboid using immediate mode for b1.8 compatibility.
            // UV layout follows standard MC box mapping.
            float u0,u1,u2,u3,u4,u5,u6;
            float v0,v1,v2,v3;
            // compute pixel UVs
            u0 = (u) / tw; u1 = (u + d) / tw; u2 = (u + d + w) / tw; u3 = (u + d + w + w) / tw; u4 = (u + d + w + d) / tw; u5 = (u + d + w + d + w) / tw;
            v0 = (v) / th; v1 = (v + d) / th; v2 = (v + d + h) / th;
            float X1=x1*scale,Y1=y1*scale,Z1=z1*scale,X2=x2*scale,Y2=y2*scale,Z2=z2*scale;
            org.lwjgl.opengl.GL11.glBegin(org.lwjgl.opengl.GL11.GL_QUADS);
            // bottom (y2)
            tex(u1,v1); vert(X2,Y2,Z2); tex(u2,v1); vert(X1,Y2,Z2); tex(u2,v0); vert(X1,Y2,Z1); tex(u1,v0); vert(X2,Y2,Z1);
            // top (y1)
            tex(u2,v0); vert(X2,Y1,Z1); tex(u3,v0); vert(X1,Y1,Z1); tex(u3,v1); vert(X1,Y1,Z2); tex(u2,v1); vert(X2,Y1,Z2);
            // west (x1)
            tex(u0,v1); vert(X1,Y2,Z1); tex(u1,v1); vert(X1,Y2,Z2); tex(u1,v2); vert(X1,Y1,Z2); tex(u0,v2); vert(X1,Y1,Z1);
            // north (z1)
            tex(u1,v1); vert(X2,Y2,Z1); tex(u2,v1); vert(X1,Y2,Z1); tex(u2,v2); vert(X1,Y1,Z1); tex(u1,v2); vert(X2,Y1,Z1);
            // east (x2)
            tex(u2,v1); vert(X2,Y2,Z2); tex(u4,v1); vert(X2,Y2,Z1); tex(u4,v2); vert(X2,Y1,Z1); tex(u2,v2); vert(X2,Y1,Z2);
            // south (z2)
            tex(u4,v1); vert(X1,Y2,Z2); tex(u5,v1); vert(X2,Y2,Z2); tex(u5,v2); vert(X2,Y1,Z2); tex(u4,v2); vert(X1,Y1,Z2);
            org.lwjgl.opengl.GL11.glEnd();
        }

        static void vert(float x,float y,float z){ org.lwjgl.opengl.GL11.glVertex3f(x,y,z); }
        static void tex(float u,float v){ org.lwjgl.opengl.GL11.glTexCoord2f(u,v); }
    }
}
