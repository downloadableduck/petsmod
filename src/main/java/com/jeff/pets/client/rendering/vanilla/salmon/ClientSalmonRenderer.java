package com.jeff.pets.client.rendering.vanilla.salmon;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientSalmon;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

public class ClientSalmonRenderer extends PetRenderer<@NotNull ClientSalmon> {

    public ClientSalmonRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new net.minecraft.client.render.entity.model.SquidEntityModel(), 0.4F);
    }

    @Override
    public @NotNull Identifier getTexture(ClientSalmon livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/fish/salmon.png");
    }

    @Override
    public void method_5777(ClientSalmon salmonRenderState, float ageInTicks, float g, float a) {
        super.method_5777(salmonRenderState, ageInTicks, g, a);
        float h = 1.0F;
        float i = 1.0F;
        float j = h * 4.3F * MathHelper.sin(i * 0.6F * ageInTicks);
    }
}