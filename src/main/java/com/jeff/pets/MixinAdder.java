package com.jeff.pets;

import org.dimdev.riftloader.listener.InitializationListener;
import org.spongepowered.asm.mixin.Mixins;

public class MixinAdder implements InitializationListener {
    @Override
    public void onInitialization() {
        Mixins.addConfiguration("mixins.pets_mod.json");
    }
}
