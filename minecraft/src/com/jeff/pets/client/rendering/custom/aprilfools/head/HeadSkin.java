package com.jeff.pets.client.rendering.custom.aprilfools.head;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

public class HeadSkin {

    public static String getHeadSkinFromMinotar(String ign) throws IOException {

        String url = "https://minotar.net/skin/" + ign;

        // 1.5.2 uses a "texturepacks" folder of folders/zips; a folder-based pack is
        // selected from that directory. The head skin is dumped into a pack named
        // "headpack" so the rest of the mod can activate it like any other pack.
        File dir = new File("texturepacks/headpack/textures/entity/head");

        Files.createDirectories(dir.toPath());

        File file = new File(dir, "head.png");

        if (!file.exists()) {
            file.createNewFile();
        }

        try (BufferedInputStream inputStream = new BufferedInputStream(new URL(url).openStream())) {

            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] dataBuffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(dataBuffer, 0, 1024)) != -1) {
                outputStream.write(dataBuffer, 0, bytesRead);
            }
        }

        return "textures/entity/head/head.png";
    }
}