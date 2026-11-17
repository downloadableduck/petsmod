package com.jeff.pets.client;

import net.danygames2014.modmenu.api.ConfigScreenFactory;
import net.danygames2014.modmenu.api.ModMenuApi;

public class ModMenuEntrypoint implements ModMenuApi {
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return (screen -> new PetsConfigScreen().getModConfigScreenFactory());
    }
}
