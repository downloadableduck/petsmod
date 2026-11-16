package com.jeff.pets.compat.render.model;

import com.jeff.pets.compat.GlStateManager;

import java.util.ArrayList;
import java.util.List;

/**
 * b1.8-compatible ModelPart shim supporting hierarchy, multiple boxes, and newer API used by this mod.
 *
 * <p>The box/UV layout here intentionally mirrors b1.8's
 * {@code net.minecraft.client.render.model.ModelPart} + {@code Polygon} so that models authored
 * against the newer model format (1.9+) render identically on b1.7.3. b1.7.3 itself hardcodes a
 * 64x32 texture for box UVs and has no part hierarchy, so its own ModelPart cannot be reused.</p>
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

    // named box (used by some newer models); name is irrelevant for rendering
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
            GlStateManager.rotatef(this.rotationZ * (180F / (float) Math.PI), 0.0F, 0.0F, 1.0F);
        }
        if (this.rotationY != 0.0F) {
            GlStateManager.rotatef(this.rotationY * (180F / (float) Math.PI), 0.0F, 1.0F, 0.0F);
        }
        if (this.rotationX != 0.0F) {
            GlStateManager.rotatef(this.rotationX * (180F / (float) Math.PI), 1.0F, 0.0F, 0.0F);
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
        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
        this.rotationX = other.rotationX;
        this.rotationY = other.rotationY;
        this.rotationZ = other.rotationZ;
    }

    /**
     * Single cuboid. The vertex ordering and UV regions are a direct copy of b1.8's
     * {@code ModelPart.addBox} + {@code Polygon} implementation.
     */
    private static class Box {
        final float x1, y1, z1, x2, y2, z2;
        final int u, v, w, h, d;
        final float tw, th;
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
            float maxX = x + w + inflate;
            float maxY = y + h + inflate;
            float maxZ = z + d + inflate;

            // b1.8 mirrors the cuboid on the X axis before building its vertices.
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

        void render(float scale) {
            float x1 = this.x1 * scale;
            float y1 = this.y1 * scale;
            float z1 = this.z1 * scale;
            float x2 = this.x2 * scale;
            float y2 = this.y2 * scale;
            float z2 = this.z2 * scale;

            float U = this.u;
            float V = this.v;
            float W = this.w;
            float H = this.h;
            float D = this.d;

            org.lwjgl.opengl.GL11.glBegin(org.lwjgl.opengl.GL11.GL_QUADS);

            // +X (east)
            quad(x2, y1, z2, x2, y1, z1, x2, y2, z1, x2, y2, z2,
                    U + D + W, V + D, U + D + W + D, V + D + H, 1.0F, 0.0F, 0.0F);
            // -X (west)
            quad(x1, y1, z1, x1, y1, z2, x1, y2, z2, x1, y2, z1,
                    U, V + D, U + D, V + D + H, -1.0F, 0.0F, 0.0F);
            // -Y (top)
            quad(x2, y1, z2, x1, y1, z2, x1, y1, z1, x2, y1, z1,
                    U + D, V, U + D + W, V + D, 0.0F, -1.0F, 0.0F);
            // +Y (bottom)
            quad(x2, y2, z1, x1, y2, z1, x1, y2, z2, x2, y2, z2,
                    U + D + W, V, U + D + W + W, V + D, 0.0F, 1.0F, 0.0F);
            // -Z (north/front)
            quad(x2, y1, z1, x1, y1, z1, x1, y2, z1, x2, y2, z1,
                    U + D, V + D, U + D + W, V + D + H, 0.0F, 0.0F, -1.0F);
            // +Z (south/back)
            quad(x1, y1, z2, x2, y1, z2, x2, y2, z2, x1, y2, z2,
                    U + D + W + D, V + D, U + D + W + D + W, V + D + H, 0.0F, 0.0F, 1.0F);

            org.lwjgl.opengl.GL11.glEnd();
        }

        /**
         * Emits one quad exactly like b1.8's {@code Polygon}: UVs are attached to vertices in the
         * order (u2,v1), (u1,v1), (u1,v2), (u2,v2); a flipped part reverses the vertex order.
         */
        private void quad(float ax, float ay, float az,
                          float bx, float by, float bz,
                          float cx, float cy, float cz,
                          float dx, float dy, float dz,
                          float u1, float v1, float u2, float v2,
                          float nx, float ny, float nz) {
            float insetU = 0.1F / this.tw;
            float insetV = 0.1F / this.th;

            float[] uu = {u2 / this.tw - insetU, u1 / this.tw + insetU, u1 / this.tw + insetU, u2 / this.tw - insetU};
            float[] vv = {v1 / this.th + insetV, v1 / this.th + insetV, v2 / this.th - insetV, v2 / this.th - insetV};
            float[] px = {ax, bx, cx, dx};
            float[] py = {ay, by, cy, dy};
            float[] pz = {az, bz, cz, dz};

            org.lwjgl.opengl.GL11.glNormal3f(nx, ny, nz);
            if (!this.flipped) {
                for (int i = 0; i < 4; i++) {
                    org.lwjgl.opengl.GL11.glTexCoord2f(uu[i], vv[i]);
                    org.lwjgl.opengl.GL11.glVertex3f(px[i], py[i], pz[i]);
                }
            } else {
                for (int i = 3; i >= 0; i--) {
                    org.lwjgl.opengl.GL11.glTexCoord2f(uu[i], vv[i]);
                    org.lwjgl.opengl.GL11.glVertex3f(px[i], py[i], pz[i]);
                }
            }
        }
    }
}
