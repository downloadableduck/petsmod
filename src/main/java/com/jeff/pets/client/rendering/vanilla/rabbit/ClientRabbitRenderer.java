//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.rabbit;

import net.minecraft.client.render.EntityRendererDispatcher;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.Mob;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.vanilla.passive.ClientRabbit;

public class ClientRabbitRenderer extends PetRenderer<ClientRabbit> {
    public String rabbitTextureLocation;

    public ClientRabbitRenderer(EntityRendererDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new ClientRabbitModel(), 0.3F);
    }

    protected void applyScale(Mob entity, float f) {
        ClientRabbit livingEntityRenderState = (ClientRabbit)entity;
        if (Central.CONFIG.isBaby) {
            GlStateManager.scalef(0.5F, 0.5F, 0.5F);
        }

    }

    public @NotNull String getTexture(Entity entity) {
        ClientRabbit rabbitRenderState = (ClientRabbit)entity;
        if (Central.CONFIG.activePet.equals("brown")) {
            this.rabbitTextureLocation = "/mob/rabbit/brown.png";
        } else if (Central.CONFIG.activePet.equals("white")) {
            this.rabbitTextureLocation = "/mob/rabbit/white.png";
        } else if (Central.CONFIG.activePet.equals("black")) {
            this.rabbitTextureLocation = "/mob/rabbit/black.png";
        } else if (Central.CONFIG.activePet.equals("gold")) {
            this.rabbitTextureLocation = "/mob/rabbit/gold.png";
        } else if (Central.CONFIG.activePet.equals("salt")) {
            this.rabbitTextureLocation = "/mob/rabbit/salt.png";
        } else if (Central.CONFIG.activePet.equals("splotched")) {
            this.rabbitTextureLocation = "/mob/rabbit/white_splotched.png";
        } else if (Central.CONFIG.activePet.equals("killer")) {
            this.rabbitTextureLocation = "/mob/rabbit/caerbannog.png";
        } else if (Central.CONFIG.activePet.equals("toast")) {
            this.rabbitTextureLocation = "/mob/rabbit/toast.png";
        } else {
            this.rabbitTextureLocation = "/mob/rabbit/brown.png";
        }

        return this.rabbitTextureLocation;
    }
}
