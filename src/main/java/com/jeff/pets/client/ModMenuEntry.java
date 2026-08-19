package com.jeff.pets.client;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ModMenuEntry implements ModMenuApi {
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return (_ -> new NewPetsConfigScreen());
    }
}
