package com.jeff.pets.javaagent;

import com.jeff.pets.PetsInitializer;
import com.jeff.pets.client.Central;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.repository.RepositorySource;

import java.io.File;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Field;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.*;

public class PetsModJavaAgentInit {
    public static void agentmain(String string, Instrumentation instrumentation) {
        Central.checkForNullObjects();
        //Central.get().createPetsList();
        Central.updateSuggestions(Minecraft.getInstance());

        //DuckSpawns.addDuckSpawn();

        PetsInitializer.LOGGER.info("quack");
    }

    public static void loadResourcePack() {
        try {
            File jar = new File(PetsModJavaAgentInit.class.getProtectionDomain().getCodeSource().getLocation().toURI());

            Path path;
            if (jar.isDirectory()) {
                path = new File(jar.getParentFile().getAbsolutePath().replace("\\classes\\java", ""), "resources/main").toPath();
            } else {
                FileSystem zipFs = FileSystems.newFileSystem(jar.toPath(), (ClassLoader) null);
                path = zipFs.getPath("/");
            }

            PackLocationInfo locationInfo = new PackLocationInfo(
                    "pets-mod-resources",
                    Component.nullToEmpty("PetsMod Resources"),
                    PackSource.BUILT_IN,
                    Optional.empty()
            );

            Pack pack = Pack.readMetaAndCreate(
                    locationInfo,
                    new PathPackResources.PathResourcesSupplier(path),
                    PackType.CLIENT_RESOURCES,
                    new PackSelectionConfig(true, Pack.Position.TOP, false)
            );

            if (pack != null) {
                PackRepository repo = Minecraft.getInstance().getResourcePackRepository();

                Field sourcesField = PackRepository.class.getDeclaredField("sources");
                sourcesField.setAccessible(true);

                Set<RepositorySource> sources = (Set<RepositorySource>) sourcesField.get(repo);
                Set<RepositorySource> mutableSources = new HashSet<>(sources);
                mutableSources.add((consumer) -> consumer.accept(pack));
                sourcesField.set(repo, mutableSources);
                repo.reload();

                List<String> selected = new ArrayList<>(repo.getSelectedIds());
                if (!selected.contains("pets-mod-resources")) {
                    selected.add("pets-mod-resources");
                }
                repo.setSelected(selected);
                Minecraft.getInstance().reloadResourcePacks();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
