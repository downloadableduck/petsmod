package com.jeff.pets.client.api;

import java.nio.file.Path;
import java.util.Collection;

public interface Loader {
    Path configDir();

    String build();

    Collection<String> modsInstalled();

    boolean devEnv();
}
