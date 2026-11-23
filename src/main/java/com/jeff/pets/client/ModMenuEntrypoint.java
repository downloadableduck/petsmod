package com.jeff.pets.client;

import io.github.prospector.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.Screen;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModMenuEntrypoint implements ModMenuApi {

    @Override
    public String getModId() {
        return "pets-mod";
    }

    @Override
    public Function<Screen, ? extends Screen> getConfigScreenFactory() {
        return (screenFactory) -> new PetsConfigScreen<>().getModConfigScreenFactory();
    }

    @Override
    public Optional<Supplier<Screen>> getConfigScreen(Screen screen) {
        return Optional.of(() -> new PetsConfigScreen<>().getModConfigScreenFactory());
    }
}
