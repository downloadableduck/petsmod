//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.compat.render.model;

import net.minecraft.client.render.renderer.DrawMode;
import net.minecraft.client.render.tessellator.TessellatorGeneral;
import com.jeff.pets.compat.GlStateManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ModelPart {
    public float textureWidth;
    public float textureHeight;
    private int u;
    private int v;
    public float x;
    public float y;
    public float z;
    public float rotationX;
    public float rotationY;
    public float rotationZ;
    public boolean flipped;
    public boolean visible;
    public boolean invisible;
    public final List<ModelPart> children;
    private final List<Box> boxes;
    private final Model model;

    public ModelPart(Model model) {
        this.textureWidth = 64.0F;
        this.textureHeight = 32.0F;
        this.visible = true;
        this.children = new ArrayList();
        this.boxes = new ArrayList();
        this.model = model;
        if (model != null) {
            this.textureWidth = (float)model.textureWidth;
            this.textureHeight = (float)model.textureHeight;
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
        if (this.visible) {
            if (!this.invisible) {
                GlStateManager.pushMatrix();
                GlStateManager.translatef(this.x * scale, this.y * scale, this.z * scale);
                if (this.rotationZ != 0.0F) {
                    GlStateManager.rotatef(this.rotationZ * 57.295776F, 0.0F, 0.0F, 1.0F);
                }

                if (this.rotationY != 0.0F) {
                    GlStateManager.rotatef(this.rotationY * 57.295776F, 0.0F, 1.0F, 0.0F);
                }

                if (this.rotationX != 0.0F) {
                    GlStateManager.rotatef(this.rotationX * 57.295776F, 1.0F, 0.0F, 0.0F);
                }

                TessellatorGeneral tessellator = this.model != null ? this.model.getTessellator() : null;
                Iterator var2 = this.boxes.iterator();

                while(var2.hasNext()) {
                    Box box = (Box)var2.next();
                    box.render(scale, tessellator);
                }

                var2 = this.children.iterator();

                while(var2.hasNext()) {
                    ModelPart child = (ModelPart)var2.next();
                    child.render(scale);
                }

                GlStateManager.popMatrix();
            }
        }
    }

    public ModelPart setTextureSize(int w, int h) {
        this.textureWidth = (float)w;
        this.textureHeight = (float)h;
        return this;
    }

    public void setPositionAndRotation(ModelPart other) {
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
        this.rotationX = other.rotationX;
        this.rotationY = other.rotationY;
        this.rotationZ = other.rotationZ;
    }

    private static class Box {
        final float x1;
        final float y1;
        final float z1;
        final float x2;
        final float y2;
        final float z2;
        final int u;
        final int v;
        final int w;
        final int h;
        final int d;
        final float tw;
        final float th;
        final boolean flipped;

        Box(int u, int v, float x, float y, float z, int w, int h, int d, float inflate, float tw, float th, boolean flipped) {
            this.u = u;
            this.v = v;
            this.w = w;
            this.h = h;
            this.d = d;
            this.tw = tw;
            this.th = th;
            this.flipped = flipped;
            float minX = x - inflate;
            float minY = y - inflate;
            float minZ = z - inflate;
            float maxX = x + (float)w + inflate;
            float maxY = y + (float)h + inflate;
            float maxZ = z + (float)d + inflate;
            if (flipped) {
                float tmp = minX;
                minX = maxX;
                maxX = tmp;
            }

            this.x1 = minX;
            this.y1 = minY;
            this.z1 = minZ;
            this.x2 = maxX;
            this.y2 = maxY;
            this.z2 = maxZ;
        }

        void render(float scale, TessellatorGeneral tessellator) {
            if (tessellator == null) {
                return;
            }
            float x1 = this.x1 * scale;
            float y1 = this.y1 * scale;
            float z1 = this.z1 * scale;
            float x2 = this.x2 * scale;
            float y2 = this.y2 * scale;
            float z2 = this.z2 * scale;
            float U = (float)this.u;
            float V = (float)this.v;
            float W = (float)this.w;
            float H = (float)this.h;
            float D = (float)this.d;
            tessellator.startDrawing(DrawMode.QUADS);
            this.quad(tessellator, x2, y1, z2, x2, y1, z1, x2, y2, z1, x2, y2, z2, U + D + W, V + D, U + D + W + D, V + D + H, 1.0F, 0.0F, 0.0F);
            this.quad(tessellator, x1, y1, z1, x1, y1, z2, x1, y2, z2, x1, y2, z1, U, V + D, U + D, V + D + H, -1.0F, 0.0F, 0.0F);
            this.quad(tessellator, x2, y1, z2, x1, y1, z2, x1, y1, z1, x2, y1, z1, U + D, V, U + D + W, V + D, 0.0F, -1.0F, 0.0F);
            this.quad(tessellator, x2, y2, z1, x1, y2, z1, x1, y2, z2, x2, y2, z2, U + D + W, V, U + D + W + W, V + D, 0.0F, 1.0F, 0.0F);
            this.quad(tessellator, x2, y1, z1, x1, y1, z1, x1, y2, z1, x2, y2, z1, U + D, V + D, U + D + W, V + D + H, 0.0F, 0.0F, -1.0F);
            this.quad(tessellator, x1, y1, z2, x2, y1, z2, x2, y2, z2, x1, y2, z2, U + D + W + D, V + D, U + D + W + D + W, V + D + H, 0.0F, 0.0F, 1.0F);
            tessellator.draw();
        }

        private void quad(TessellatorGeneral tessellator, float ax, float ay, float az, float bx, float by, float bz, float cx, float cy, float cz, float dx, float dy, float dz, float u1, float v1, float u2, float v2, float nx, float ny, float nz) {
            float insetU = 0.1F / this.tw;
            float insetV = 0.1F / this.th;
            float[] uu = new float[]{u2 / this.tw - insetU, u1 / this.tw + insetU, u1 / this.tw + insetU, u2 / this.tw - insetU};
            float[] vv = new float[]{v1 / this.th + insetV, v1 / this.th + insetV, v2 / this.th - insetV, v2 / this.th - insetV};
            float[] px = new float[]{ax, bx, cx, dx};
            float[] py = new float[]{ay, by, cy, dy};
            float[] pz = new float[]{az, bz, cz, dz};
            tessellator.setNormal(nx, ny, nz);
            int i;
            if (!this.flipped) {
                for(i = 0; i < 4; ++i) {
                    tessellator.addVertexWithUV((double)px[i], (double)py[i], (double)pz[i], (double)uu[i], (double)vv[i]);
                }
            } else {
                for(i = 3; i >= 0; --i) {
                    tessellator.addVertexWithUV((double)px[i], (double)py[i], (double)pz[i], (double)uu[i], (double)vv[i]);
                }
            }

        }
    }
}
