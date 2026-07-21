package com.jeff.pets;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.fabricmc.loader.impl.FormattedException;
import net.fabricmc.loader.impl.gui.FabricGuiEntry;
import net.fabricmc.loader.impl.gui.FabricStatusTree;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import org.apache.logging.log4j.message.FormattedMessage;

import java.text.Format;

public class PreLaunchCheck implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        try {
            Class<?> clazz = Class.forName("net.minecraft.unmapped.C_9760061");
            FabricGuiEntry.displayError("Caught exception in thread " + Thread.currentThread().getName(), new FormattedException("Exception from mod Pets Mod", "You are using Calamus Gen1 mappings! You need to install Gen2 mappings for Pets Mod to work." +
                    "                    Since Ornithe does not natively give an easy way to use Gen2 mappings, please download the Ornithe Installer CLI from their page at https://ornithemc.net/download/," +
                    "                    and run the following command in your terminal: " + "\n" +
                    "                    ornithe-cli.exe client --minecraft-version <MINECRAFT_VERSION> --gen 2" + "\n" +
                    "(Click the command to copy)"), true);
        } catch (ClassNotFoundException e) {

        }
    }
}
