package com.jeff.pets.fabric.client;

import com.jeff.pets.client.PetsConfigScreen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ModMenuEntry implements ModMenuApi {
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return (_ -> new PetsConfigScreen());
    }
}
