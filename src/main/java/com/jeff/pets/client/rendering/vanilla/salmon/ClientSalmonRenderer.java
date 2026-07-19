package com.jeff.pets.client.rendering.vanilla.salmon;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.passive.ClientSalmon;
import net.minecraft.client.render.model.entity.SalmonModel;
import net.minecraft.resource.Identifier;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

public class ClientSalmonRenderer extends PetRenderer<@NotNull ClientSalmon, @NotNull SalmonModel<ClientSalmon>> {

    public ClientSalmonRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context) {
        super(context, new SalmonModel<>(), 0.4F);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientSalmon salmonRenderState) {
        return new Identifier("minecraft", "textures/entity/fish/salmon.png");
    }

    @Override
    protected void applyRotation(ClientSalmon salmonRenderState, float ageInTicks, float g, float a) {
        super.applyRotation(salmonRenderState, ageInTicks, g, a);
        float h = 1.0F;
        float i = 1.0F;

        float j = h * 4.3F * MathHelper.sin(i * 0.6F * ageInTicks);
        //GlStateManager.multMatrix(Vector3f.getY().getDegreesQuaternion(j));
    }
}
