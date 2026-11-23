package net.minecraft.client.resource.language;

/**
 * Modern Cloth Config translation entry point backed by BTA's I18n.
 */
public final class I18n {
    private I18n() {
    }

    public static String getTranslation(String key) {
        return net.minecraft.core.lang.I18n.getInstance().translateKey(key);
    }

    public static String getTranslation(String key, Object... args) {
        if (args == null || args.length == 0) {
            return getTranslation(key);
        }
        return net.minecraft.core.lang.I18n.getInstance().translateKeyAndFormat(key, args);
    }
}
