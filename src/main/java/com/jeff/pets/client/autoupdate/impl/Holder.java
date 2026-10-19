package com.jeff.pets.client.autoupdate.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jeff.pets.client.autoupdate.AutoUpdater;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class Holder {

    private final String modVersion;
    private final String[] minecraftVersions;
    private URI downloadURI;
    private URI versionPageURI;

    private Holder(String modVersion, String[] minecraftVersions) {
        this.modVersion = modVersion;
        this.minecraftVersions = minecraftVersions;
    }

    public static Holder of(String modVersion, String[] minecraftVersions) {
        return new Holder(modVersion, minecraftVersions);
    }

    public String getModVersion() {
        return modVersion;
    }

    public String[] getMinecraftVersions() {
        return minecraftVersions;
    }

    public URI getDownloadURL() {
        return downloadURI;
    }

    public String putTogetherFileName() {
        return "pets-mod-" + this.modVersion + "-" + AutoUpdater.getMinecraftVersion() + "-neo.jar";
    }

    public CompletableFuture<Void> pingURI() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create("https://api.modrinth.com/v2/project/pets-mod/version")).build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept((string) -> {
                    JsonArray versions = JsonParser.parseString(string).getAsJsonArray();
                    for (JsonElement element : versions) {
                        JsonObject version = element.getAsJsonObject();
                        JsonArray files = version.getAsJsonArray("files");
                        for (JsonElement fileElement : files) {
                            JsonObject file = fileElement.getAsJsonObject();
                            if (file.get("filename").getAsString().equals(this.putTogetherFileName())) {
                                this.downloadURI = URI.create(file.get("url").getAsString());
                                String anotherString = this.getDownloadURL().toString();
                                String yetAnotherString = anotherString.replace("cdn.modrinth.com/data", "modrinth.com/mod");
                                String areWeDoneYet = yetAnotherString.replace("versions", "version");
                                this.versionPageURI = URI.create(areWeDoneYet.replace(this.putTogetherFileName(), ""));
                            }
                        }
                    }
                })
                .exceptionally((throwable -> {
                    AutoUpdater.LOGGER.info("An error occurred, {}", String.valueOf(throwable));
                    return null;
                }));
    }

    public URI getVersionPage() {
        return this.versionPageURI;
    }
}
