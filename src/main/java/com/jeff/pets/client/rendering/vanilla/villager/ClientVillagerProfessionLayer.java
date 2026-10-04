package com.jeff.pets.client.rendering.vanilla.villager;

import com.jeff.pets.client.rendering.PetLayer;
import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Villager profession overlay, drawn very slightly scaled up so it sits over the base
 * texture without z-fighting. 1.7.10 has no {@code LayerRenderer}, so this is driven from
 * {@link PetRenderer#renderModel}.
 */
public class ClientVillagerProfessionLayer implements PetLayer {
    public static final ResourceLocation ARMORER_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/armorer.png");
    public static final ResourceLocation BUTCHER_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/butcher.png");
    public static final ResourceLocation CARTOGRAPHER_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/cartographer.png");
    public static final ResourceLocation CLERIC_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/cleric.png");
    public static final ResourceLocation FARMER_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/farmer.png");
    public static final ResourceLocation FISHERMAN_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/fisherman.png");
    public static final ResourceLocation FLETCHER_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/fletcher.png");
    public static final ResourceLocation LEATHERWORKER_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/leatherworker.png");
    public static final ResourceLocation LIBRARIAN_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/librarian.png");
    public static final ResourceLocation MASON_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/mason.png");
    public static final ResourceLocation NITWIT_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/nitwit.png");
    public static final ResourceLocation SHEPHERD_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/shepherd.png");
    public static final ResourceLocation TOOLSMITH_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/toolsmith.png");
    public static final ResourceLocation WEAPONSMITH_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/profession/weaponsmith.png");

    private final PetRenderer parent;

    public ClientVillagerProfessionLayer(RenderManager context, PetRenderer parent) {
        this.parent = parent;
    }

    @Override
    public void render(EntityLivingBase villager, float limbSwing, float limbSwingAmount, float ageInTicks,
                       float netHeadYaw, float headPitch, float scale) {
        GL11.glPushMatrix();
        GL11.glScalef(1.001F, 1.001F, 1.001F);
        this.parent.getMainModel().render(villager, limbSwing, limbSwingAmount, ageInTicks,
                netHeadYaw, headPitch, scale);
        GL11.glPopMatrix();
    }
}
