package com.jeff.pets.client.rendering.vanilla.enderdragon;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.boss.ClientEnderDragon;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;
import static com.jeff.pets.client.Central.stingray;


public class ClientEnderDragonRenderer extends PetRenderer<ClientEnderDragon> {

    public ClientEnderDragonRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientEnderDragonModel(0), 0.75f);
    }

    @Override
<<<<<<< HEAD
    protected void applyScale(@NotNull ClientEnderDragon livingEntityRenderState, float f) {
        super.applyScale(livingEntityRenderState, f);
        if (CONFIG.isBaby) {
            net.minecraft.client.render.platform.GlStateManager.scalef(0.25f, 0.25f, 0.25f);
=======
    public void preRenderCallback(ClientEnderDragon livingEntityRenderState, float f) {
        if (livingEntityRenderState.isChild()) {
            net.minecraft.client.renderer.GlStateManager.scalef(0.25f, 0.25f, 0.25f);
>>>>>>> 908063cb (backport the 0.8.x updates to 1.8.9, as well as allow users to place and break blocks on top of the pet)
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientEnderDragon livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/enderdragon/dragon.png");
    }
}
