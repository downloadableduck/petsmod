package com.jeff.pets.client.rendering.custom.aprilfools.head;

import net.minecraft.src.Entity;
import com.jeff.pets.client.rendering.PetRenderer;

import java.io.IOException;

import static com.jeff.pets.client.Central.CONFIG;

/**
 * 1.7.10 originally resolved the head pet's skin through the Mojang AuthLib
 * (GameProfileRepository / SkinManager), which does not exist in 1.5.2. The
 * port instead pulls the skin straight from Minotar into a "headpack" texture
 * pack folder and binds it like any other 1.5.2 texture.
 */
public class HeadRenderer extends PetRenderer {

    public HeadRenderer() {
        super(new HeadModel(), 0.3F);
    }

    @Override
    public String getEntityTexture(final Entity state) {
        try {
            return HeadSkin.getHeadSkinFromMinotar(CONFIG.headSkin);
        } catch (IOException e) {
            return "/mob/char.png";
        }
    }
}