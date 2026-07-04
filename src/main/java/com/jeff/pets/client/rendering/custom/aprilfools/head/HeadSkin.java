package com.jeff.pets.client.rendering.custom.aprilfools.head;

import net.minecraft.resources.ResourceLocation;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;


public class HeadSkin {

    public static ResourceLocation getHeadSkinFromMinotar(String ign) throws IOException {

        String url = "https://minotar.net/skin/" + ign;

        File dir = new File("resourcepacks/headpack/assets/minecraft");

        Files.createDirectories(dir.toPath());

        File file = new File(dir, "playerskin.png");

        File mcmeta = new File("resourcepacks/headpack/pack.mcmeta");

        if (!file.exists()) {
            file.createNewFile();
        }
        if (!mcmeta.exists()) {
            mcmeta.createNewFile();
        }
        try (FileWriter writer = new FileWriter(mcmeta)) {
            writer.write("{\n" +
                    "  \"pack\": {\n" +
                    "    \"pack_format\": 46,\n" +
                    "    \"description\": \"headpack\"\n" +
                    "  }\n" +
                    "}");
        }

        ResourceLocation identifier = new ResourceLocation("minecraft", "playerskin.png");

        try (BufferedInputStream inputStream = new BufferedInputStream(new URL(url).openStream())) {

            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] dataBuffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(dataBuffer, 0, 1024)) != -1) {
                outputStream.write(dataBuffer, 0, bytesRead);
            }
        }
        return identifier;
    }
} 