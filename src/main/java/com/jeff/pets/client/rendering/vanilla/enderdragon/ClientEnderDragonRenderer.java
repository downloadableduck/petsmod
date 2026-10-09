package com.jeff.pets.client.rendering.vanilla.enderdragon;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.boss.ClientEnderDragon;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientEnderDragonRenderer extends PetRenderer {

    public ClientEnderDragonRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientEnderDragonModel(0), 0.75f);
    }

    protected void preRenderCallback(@NotNull ClientEnderDragon livingEntityRenderState, float f) {
        super.scale(livingEntityRenderState, f);
        if (CONFIG.isBaby) {
            GL11.glScalef(0.25f, 0.25f, 0.25f);
        }
    }

    public @NotNull Identifier getTexture(net.minecraft.entity.Entity __e) {
        ClientEnderDragon livingEntityRenderState = (ClientEnderDragon) __e;
        return new Identifier("minecraft", "textures/entity/enderdragon/dragon.png");
    }
}