package com.jeff.pets.client.rendering.vanilla.magmacube;

import com.jeff.pets.client.rendering.PetRenderer;
import com.jeff.pets.mob.vanilla.hostile.ClientMagmaCube;
import net.minecraft.client.render.model.entity.SlimeModel;
import net.minecraft.resource.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientMagmaCubeRenderer extends PetRenderer<ClientMagmaCube> {

    public ClientMagmaCubeRenderer(net.minecraft.client.render.entity.EntityRenderDispatcher context, com.jeff.pets.client.PetsClientInitializer.Context context2) {
        super(context, new SlimeModel(0), 0.75f);
    }

    @Override
<<<<<<< HEAD
    protected void applyScale(ClientMagmaCube slimeRenderState, float a) {
        int magmaCubeapplyScale;
        switch (CONFIG.magmaCubeSkin) {
=======
    public void preRenderCallback(ClientMagmaCube slimeRenderState, float a) {
        int magmaCubeScale;
        switch (slimeRenderState.petSkin) {
>>>>>>> 908063cb (backport the 0.8.x updates to 1.8.9, as well as allow users to place and break blocks on top of the pet)
            case "small":
                magmaCubeapplyScale = 1;
                break;
            case "medium":
                magmaCubeapplyScale = 2;
                break;
            case "large":
                magmaCubeapplyScale = 4;
                break;
            default:
                magmaCubeapplyScale = 1;
                break;
        }
        net.minecraft.client.render.platform.GlStateManager.scalef(magmaCubeapplyScale, magmaCubeapplyScale, magmaCubeapplyScale);
    }

    @Override
    public @NotNull Identifier getTextureLocation(ClientMagmaCube livingEntityRenderState) {
        return new Identifier("minecraft", "textures/entity/slime/magmacube.png");
    }
}
