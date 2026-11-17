//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jeff.pets.client.rendering.vanilla.elderguardian;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.client.rendering.vanilla.guardian.ClientGuardianModel;
import com.jeff.pets.compat.GlStateManager;
import com.jeff.pets.mob.vanilla.hostile.ClientElderGuardian;

public class ClientElderGuardianRenderer extends PetRenderer<ClientElderGuardian> {
    public ClientElderGuardianRenderer(EntityRenderDispatcher context, PetsClientInitializer.Context context2) {
        super(context, new ClientGuardianModel(), 0.75F);
    }

    public void applyScale(LivingEntity entity, float f) {
        ClientElderGuardian elderGuardian = (ClientElderGuardian)entity;
        GlStateManager.scalef(2.35F, 2.35F, 2.35F);
    }

    public @NotNull String getTexture(Entity entity) {
        ClientElderGuardian livingEntityRenderState = (ClientElderGuardian)entity;
        return "textures/mob/guardian_elder.png";
    }
}
