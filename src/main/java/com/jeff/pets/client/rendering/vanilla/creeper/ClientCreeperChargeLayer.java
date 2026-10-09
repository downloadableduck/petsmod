package com.jeff.pets.client.rendering.vanilla.creeper;

import com.jeff.pets.mob.vanilla.hostile.ClientCreeper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.model.CreeperEntityModel;
import net.minecraft.util.Identifier;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientCreeperChargeLayer {
    private static final Identifier SKIN = new Identifier("textures/entity/creeper/creeper_armor.png");
    private final ClientCreeperRenderer renderer;
    private final CreeperEntityModel model;

    public ClientCreeperChargeLayer(ClientCreeperRenderer renderer) {
        this.renderer = renderer;
        this.model = new CreeperEntityModel(2.0F);
    }

    public void render(ClientCreeper creeperEntity, float f, float g, float h, float i, float j, float k, float l) {
        if (CONFIG.creeperSkin.equals("charged")) {
            boolean bl = creeperEntity.isInvisible();
            GL11.glDepthMask(!bl);
            MinecraftClient.getInstance().getTextureManager().bindTexture(SKIN);
            GL11.glMatrixMode(5890);
            GL11.glLoadIdentity();
            float m = (float) creeperEntity.ticksAlive + h;
            GL11.glTranslatef(m * 0.01F, m * 0.01F, 0.0F);
            GL11.glMatrixMode(5888);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glColor4f(0.5F, 0.5F, 0.5F, 1.0F);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glBlendFunc(1, 1);
            this.model.render(creeperEntity, f, g, i, j, k, l);
            GL11.glMatrixMode(5890);
            GL11.glLoadIdentity();
            GL11.glMatrixMode(5888);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glDepthMask(true);
        }
    }

    public boolean combineTextures() {
        return false;
    }
}