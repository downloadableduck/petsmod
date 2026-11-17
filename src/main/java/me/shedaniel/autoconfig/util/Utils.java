//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.autoconfig.util;

import net.fabricmc.loader.api.FabricLoader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Utils {
    private Utils() {
    }

    public static Path getConfigFolder() {
        return FabricLoader.getInstance().getConfigDir();
    }

    public static <V> V constructUnsafely(Class<V> cls) {
        try {
            Constructor<V> constructor = cls.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (ReflectiveOperationException var2) {
            ReflectiveOperationException e = var2;
            throw new RuntimeException(e);
        }
    }

    public static <V> V getUnsafely(Field field, Object obj) {
        if (obj == null) {
            return null;
        } else {
            try {
                field.setAccessible(true);
                return (V)field.get(obj);
            } catch (ReflectiveOperationException var3) {
                ReflectiveOperationException e = var3;
                throw new RuntimeException(e);
            }
        }
    }

    public static <V> V getUnsafely(Field field, Object obj, V defaultValue) {
        V ret = getUnsafely(field, obj);
        if (ret == null) {
            ret = defaultValue;
        }

        return ret;
    }

    public static void setUnsafely(Field field, Object obj, Object newValue) {
        if (obj != null) {
            try {
                field.setAccessible(true);
                field.set(obj, newValue);
            } catch (ReflectiveOperationException var4) {
                ReflectiveOperationException e = var4;
                throw new RuntimeException(e);
            }
        }
    }

    public static <T, K, U> Collector<T, ?, Map<K, U>> toLinkedMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends U> valueMapper) {
        return Collectors.toMap(keyMapper, valueMapper, (u, v) -> {
            throw new IllegalStateException(String.format("Duplicate key %s", u));
        }, LinkedHashMap::new);
    }
}
