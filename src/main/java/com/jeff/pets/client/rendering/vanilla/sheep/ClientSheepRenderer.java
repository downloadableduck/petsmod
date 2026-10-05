package com.jeff.pets.client.rendering.vanilla.sheep;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientSheep;
import net.minecraft.client.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientSheepRenderer extends PetRenderer<ClientSheep> {

    public ClientSheepRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new ClientSheepModel(), 0.7F);
        this.addLayer(new ClientSheepWoolLayer(this));
    }

    @Override
    protected void applyScale(net.minecraft.entity.living.LivingEntity entity, float f) {
        ClientSheep livingEntityRenderState = (ClientSheep) entity;
        if (CONFIG.isBaby) {
            com.jeff.pets.compat.GlStateManager.scalef(0.5f, 0.5f, 0.5f);
        }
    }

    @Override
    public @NotNull Identifier getTextureLocation(net.minecraft.entity.Entity entity) {
        ClientSheep livingEntityRenderState = (ClientSheep) entity;
        return new Identifier("minecraft", "textures/entity/sheep/sheep.png");
    }

    /*@Override
    public void extractRenderState(Sheep sheep, SheepRenderState state, float f) {
        super.extractRenderState(sheep, state, f);
        sheep.sheared = false;
    }*/
}
