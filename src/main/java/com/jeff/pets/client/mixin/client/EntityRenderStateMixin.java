package com.jeff.pets.client.mixin.client;

import com.jeff.pets.client.Utils;
import com.jeff.pets.client.rendering.IPetRenderState;
import net.minecraft.client.renderer.entity.state.EntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(EntityRenderState.class)
public class EntityRenderStateMixin implements IPetRenderState {
    @Unique
    private boolean pets$isMyPet;
    
    @Unique
    private String pets$petSkin = "";

    @Override
    public boolean pets$isMyPet() {
        return this.pets$isMyPet;
    }

    @Override
    public void pets$setMyPet(boolean myPet) {
        this.pets$isMyPet = myPet;
    }

    @Override
    public String pets$getPetSkin() {
        if (this.pets$isMyPet) {
            return Utils.getActivePetSkin();
        }
        return this.pets$petSkin;
    }

    @Override
    public void pets$setPetSkin(String skin) {
        this.pets$petSkin = skin;
    }
}