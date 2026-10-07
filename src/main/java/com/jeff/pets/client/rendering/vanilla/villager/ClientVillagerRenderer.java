package com.jeff.pets.client.rendering.vanilla.villager;

import com.jeff.pets.client.rendering.PetRenderer;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.entity.Entity; import net.minecraft.client.renderer.entity.RenderManager; import com.jeff.pets.client.PetsClientInitializer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class ClientVillagerRenderer extends PetRenderer {

    public static final ResourceLocation BUTCHER_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/butcher.png");
    public static final ResourceLocation FARMER_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/farmer.png");
    public static final ResourceLocation LIBRARIAN_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/librarian.png");
    public static final ResourceLocation NITWIT_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/villager.png");
    public static final ResourceLocation TOOLSMITH_LOCATION = new ResourceLocation("minecraft", "textures/entity/villager/toolsmith.png");

    public ClientVillagerRenderer(RenderManager renderManager, PetsClientInitializer.Context context) {
        super(new ModelVillager(0), 0.5F);
    }

    @Override
    public void preRenderCallback( final EntityLivingBase state, float f) {
        if (CONFIG.isBaby) {
            GL11.glScalef(0.5f, 0.5f, 0.5f);
        }

    }

    @Override
    public ResourceLocation getEntityTexture( final Entity villagerRenderState) {
        if (Objects.equals(CONFIG.villagerSkin, "butcher")) {
            return (BUTCHER_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "farmer")) {
            return (FARMER_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "librarian")) {
            return (LIBRARIAN_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "nitwit")) {
            return (NITWIT_LOCATION);
        } else if (Objects.equals(CONFIG.villagerSkin, "toolsmith") || Objects.equals(CONFIG.villagerSkin, "weaponsmith")) {
            return (TOOLSMITH_LOCATION);
        }
        return NITWIT_LOCATION;
    }
}

