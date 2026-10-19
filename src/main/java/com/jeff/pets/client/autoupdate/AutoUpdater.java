package com.jeff.pets.client.autoupdate;

import com.jeff.pets.client.Central;
import com.jeff.pets.client.autoupdate.impl.CustomClickEvent;
import com.jeff.pets.client.autoupdate.impl.Holder;
import com.jeff.pets.client.autoupdate.impl.PetsModToast;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static com.jeff.pets.client.Central.CONFIG;

public class AutoUpdater implements ClientModInitializer {

    private static final HttpClient client = HttpClient.newHttpClient();
    public static Logger LOGGER = LoggerFactory.getLogger("Pets Mod Updater");

    public static void checkForAutoUpdate() {
        HttpRequest request = HttpRequest.newBuilder(URI.create("https://petsmod.com/update.json")).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept((string) -> {
                    Holder holder = parseHolder(string);
                    if (isUpdateAvailable(holder)) {
                        if (!holder.getModVersion().equals(CONFIG.dismissedVersion)) {
                            CONFIG.hasNotifiedOfMostRecentUpdate = false;
                        }
                        if (!CONFIG.hasNotifiedOfMostRecentUpdate) {
                            holder.pingURI().thenRun(() -> Minecraft.getInstance().execute(() -> notifyPlayer(holder)));
                            CONFIG.hasNotifiedOfMostRecentUpdate = true;
                        }
                    }
                }).exceptionally((throwable) -> {
                    LOGGER.info("Ping failed, {}", String.valueOf(throwable));
                    return null;
                });
    }

    public static String getPetsModVersion() {
        return FabricLoader.getInstance().getModContainer("pets-mod").get().getMetadata().getVersion().getFriendlyString();
    }

    public static String getMinecraftVersion() {
        return FabricLoader.getInstance().getRawGameVersion();
    }

    public static Component getUpdateMessage(Holder holder) {
        MutableComponent message = Component.translatable("message.pets-mod.update-available", Component.literal(holder.getModVersion()).withStyle(ChatFormatting.GREEN));
        MutableComponent download = Component.translatable("message.pets-mod.auto-install").withStyle((s) ->
                s.withClickEvent(new CustomClickEvent(() -> downloadAndUpdate(holder, holder.getDownloadURL()))).withHoverEvent(new HoverEvent.ShowText(Component.translatable("message.description.pets-mod.auto-install"))));
        MutableComponent view = Component.translatable("message.pets-mod.view").withStyle((s) -> s.withClickEvent(new ClickEvent.OpenUrl(holder.getVersionPage())).withHoverEvent(new HoverEvent.ShowText(Component.translatable("message.description.pets-mod.view"))));
        MutableComponent dismiss = Component.translatable("message.pets-mod.dismiss").withStyle((s) -> s.withClickEvent(new CustomClickEvent(() -> {
            Objects.requireNonNull(Minecraft.getInstance().player).sendSystemMessage(Component.translatable("message.pets-mod.dismissed"));
            CONFIG.hasNotifiedOfMostRecentUpdate = true;
            CONFIG.dismissedVersion = holder.getModVersion();
            Central.saveConfig();
        })).withHoverEvent(new HoverEvent.ShowText(Component.translatable("message.description.pets-mod.dismiss"))));
        return message.append(download).append(view).append(dismiss);
    }

    public static Holder parseHolder(String string) {
        return Central.GSON.fromJson(string, Holder.class);
    }

    public static boolean isUpdateAvailable(Holder holder) {
        String[] versions = holder.getMinecraftVersions();
        String modVersion = holder.getModVersion();
        for (String version : versions) {
            if (Objects.equals(version, getMinecraftVersion()) && compare(modVersion, getPetsModVersion()) > 0) {
                return true;
            }
        }
        return false;
    }

    public static void notifyPlayer(Holder holder) {
        Objects.requireNonNull(Minecraft.getInstance().player).sendSystemMessage(getUpdateMessage(holder));
    }

    public static void downloadAndUpdate(Holder holder, URI uri) {
        String fileName = holder.putTogetherFileName();
        fileName = URLDecoder.decode(fileName, StandardCharsets.UTF_8);

        Path modsDir = Path.of(Minecraft.getInstance().gameDirectory.toPath().toAbsolutePath().toString(), "mods");
        Path path = modsDir.resolve(fileName);

        try (HttpClient client = HttpClient.newHttpClient()) {
            if (uri != null) {
                HttpRequest request = HttpRequest.newBuilder(uri).GET().build();

                try {
                    HttpResponse<Path> response = client.send(request, HttpResponse.BodyHandlers.ofFile(path));
                    if (response.statusCode() == 200) {
                        LOGGER.info(("Successfully downloaded update."));
                        successDownload();
                    } else {
                        failDownload(String.valueOf(response.statusCode()), Files.readString(path));
                    }
                } catch (IOException | InterruptedException e) {
                    failDownload(e.toString(), e.getMessage());
                }
            } else {
                failDownload("URI was null", "The download link for the file could not be found.");
            }
        }
        LOGGER.info("uri:" + holder.getDownloadURL());
    }

    public static int compare(String targetVersion, String modVersion) {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) return 1;
        String[] parts1 = targetVersion.split("\\.");
        String[] parts2 = modVersion.split("\\.");
        int length = Math.max(parts1.length, parts2.length);

        for (int i = 0; i < length; i++) {
            int p1 = i < parts1.length ? Integer.parseInt(parts1[i].replaceAll("\\D+", "")) : 0;
            int p2 = i < parts2.length ? Integer.parseInt(parts2[i].replaceAll("\\D+", "")) : 0;
            if (p1 != p2) {
                return Integer.compare(p1, p2);
            }
        }
        return 0;
    }

    protected static void failDownload(String reason, String description) {
        Component title = Component.literal("Download failed");
        Component message = Component.literal(reason + ", " + description);
        Toast toast = new PetsModToast(SystemToast.SystemToastId.PERIODIC_NOTIFICATION, title, message);
        Minecraft.getInstance().gui.toastManager().addToast(toast);

    }

    protected static void successDownload() {
        Component title = Component.literal("Successfully downloaded update");
        Component message = Component.literal("Restart your game to apply the changes.");
        Toast toast = new PetsModToast(SystemToast.SystemToastId.PERIODIC_NOTIFICATION, title, message);
        Minecraft.getInstance().gui.toastManager().addToast(toast);
    }

    private static void cleanUp() {
        File[] files = FabricLoader.getInstance().getGameDir().resolve("mods").toFile().listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().startsWith("pets-mod") && !file.getName().equals(getThisJarName())) {
                    boolean deleted = file.delete();
                    if (!deleted) {
                        LOGGER.info("Failed to delete stale Pets Mod file {}!", file.getName());
                    }
                }
            }
        }
    }

    private static String getThisJarName() {
        try {
            return Path.of(AutoUpdater.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getFileName().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onInitializeClient() {
        cleanUp();
        ClientPlayConnectionEvents.JOIN.register((_, _, _) -> {
            if (CONFIG.notifyForUpdates) checkForAutoUpdate();
        });
    }
}
