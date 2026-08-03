package com.jeff.pets.client;

import com.jeff.pets.Agent;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.*;
import net.minecraft.server.packs.repository.BuiltInPackSource;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.world.level.validation.DirectoryValidator;
import org.jspecify.annotations.Nullable;

import java.io.File;
import java.util.Optional;
import java.util.function.Consumer;

import static com.jeff.pets.PetsInitializer.MOD_ID;

public class PetsPackSource extends BuiltInPackSource {

    public PetsPackSource(VanillaPackResources vanillaPack, DirectoryValidator validator) {
        super(PackType.CLIENT_RESOURCES, vanillaPack, Identifier.fromNamespaceAndPath(MOD_ID, "core"), validator);
    }

    @Override
    public Component getPackTitle(String id) {
        return Component.literal("PetsMod Resources");
    }

    @Override
    protected @Nullable Pack createBuiltinPack(String id, Pack.ResourcesSupplier resources, Component name) {
        return this.createPetsPack();
    }

    @Override
    public void loadPacks(Consumer<Pack> consumer) {
        Pack pack = createPetsPack();
        if (pack != null) {
            consumer.accept(pack);
        }
    }

    @Override
    protected Pack createVanillaPack(PackResources resources) {
        return createPetsPack();
    }

    private Pack createPetsPack() {
        try {
            File jar = new File(
                    Agent.class.getProtectionDomain().getCodeSource().getLocation().toURI()
            );

            Pack.ResourcesSupplier supplier;
            File resourcesDir = new File(jar.getParentFile().getAbsolutePath().replace("\\classes\\java", ""), "resources/main");

            if (jar.isDirectory()) {
                supplier = new PathPackResources.PathResourcesSupplier(resourcesDir.toPath());
            } else {
                supplier = new FilePackResources.FileResourcesSupplier(jar.toPath());
            }

            PackLocationInfo locationInfo = new PackLocationInfo(
                    "pets-mod-resources",
                    Component.literal("PetsMod Resources"),
                    PackSource.BUILT_IN,
                    Optional.empty()
            );

            return Pack.readMetaAndCreate(
                    locationInfo,
                    supplier,
                    PackType.CLIENT_RESOURCES,
                    new PackSelectionConfig(true, Pack.Position.TOP, false)
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            throw new NullPointerException("hi");
        }
    }
}