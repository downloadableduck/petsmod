package com.jeff.pets.client.rendering;

public interface IPetRenderState {
    boolean pets$isMyPet();
    void pets$setMyPet(boolean myPet);

    String pets$getPetSkin();
    void pets$setPetSkin(String skin);
}