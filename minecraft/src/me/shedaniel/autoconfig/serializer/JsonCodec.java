/*
 * This file is part of Cloth Config.
 * Copyright (C) 2020 - 2021 shedaniel
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package me.shedaniel.autoconfig.serializer;

import me.shedaniel.autoconfig.util.Utils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * A small reflective JSON mapper built on org.json, replacing Gson for config (de)serialization.
 */
public final class JsonCodec {
    private JsonCodec() {
    }

    public static String toJson(Object value, boolean pretty) {
        Object element = toElement(value);
        if (element instanceof JSONObject) {
            return ((JSONObject) element).toString(pretty ? 2 : 0);
        }
        if (element instanceof JSONArray) {
            return ((JSONArray) element).toString(pretty ? 2 : 0);
        }

        JSONObject wrapper = new JSONObject();
        wrapper.put("value", element);
        return wrapper.toString(pretty ? 2 : 0);
    }

    private static Object toElement(Object value) {
        if (value == null) {
            return JSONObject.NULL;
        }
        if (value instanceof String || value instanceof Number || value instanceof Boolean) {
            return value;
        }
        if (value instanceof Character) {
            return value.toString();
        }
        if (value instanceof Enum) {
            return ((Enum<?>) value).name();
        }
        if (value.getClass().isArray()) {
            JSONArray array = new JSONArray();
            int length = Array.getLength(value);
            for (int i = 0; i < length; i++) {
                array.put(toElement(Array.get(value, i)));
            }
            return array;
        }
        if (value instanceof Collection) {
            JSONArray array = new JSONArray();
            for (Object element : (Collection<?>) value) {
                array.put(toElement(element));
            }
            return array;
        }
        if (value instanceof Map) {
            JSONObject object = new JSONObject();
            for (Map.Entry<?, ?> entry : ((Map<?, ?>) value).entrySet()) {
                object.put(String.valueOf(entry.getKey()), toElement(entry.getValue()));
            }
            return object;
        }

        JSONObject object = new JSONObject();
        for (Field field : allFields(value.getClass())) {
            if (Modifier.isStatic(field.getModifiers()) || Modifier.isTransient(field.getModifiers())) {
                continue;
            }
            field.setAccessible(true);
            try {
                object.put(field.getName(), toElement(field.get(value)));
            } catch (Exception ignored) {
            }
        }
        return object;
    }

    public static <T> T fromJson(String json, Class<T> cls) {
        JSONObject object = new JSONObject(new JSONTokener(json));
        return fromObject(object, cls);
    }

    private static <T> T fromObject(JSONObject object, Class<T> cls) {
        T instance = Utils.constructUnsafely(cls);
        for (Field field : allFields(cls)) {
            if (Modifier.isStatic(field.getModifiers()) || Modifier.isTransient(field.getModifiers())) {
                continue;
            }
            if (!object.has(field.getName())) {
                continue;
            }
            field.setAccessible(true);
            try {
                field.set(instance, convert(object.get(field.getName()), field.getType()));
            } catch (Exception ignored) {
            }
        }
        return instance;
    }

    private static Object convert(Object value, Class<?> type) {
        if (value == null || value == JSONObject.NULL) {
            return null;
        }
        if (type == boolean.class || type == Boolean.class) {
            return value instanceof Boolean ? value : Boolean.valueOf(String.valueOf(value));
        }
        if (type == int.class || type == Integer.class) {
            return value instanceof Number ? ((Number) value).intValue() : Integer.valueOf(String.valueOf(value));
        }
        if (type == long.class || type == Long.class) {
            return value instanceof Number ? ((Number) value).longValue() : Long.valueOf(String.valueOf(value));
        }
        if (type == float.class || type == Float.class) {
            return value instanceof Number ? ((Number) value).floatValue() : Float.valueOf(String.valueOf(value));
        }
        if (type == double.class || type == Double.class) {
            return value instanceof Number ? ((Number) value).doubleValue() : Double.valueOf(String.valueOf(value));
        }
        if (type == String.class) {
            return String.valueOf(value);
        }
        if (type.isEnum()) {
            @SuppressWarnings({"unchecked", "rawtypes"})
            Object constant = Enum.valueOf((Class<Enum>) type, String.valueOf(value));
            return constant;
        }
        if (value instanceof JSONObject) {
            if (Collection.class.isAssignableFrom(type) || type.isArray()) {
                return null;
            }
            return fromObject((JSONObject) value, type);
        }
        if (value instanceof JSONArray) {
            JSONArray array = (JSONArray) value;
            if (List.class.isAssignableFrom(type)) {
                List<Object> list = new ArrayList<Object>();
                for (int i = 0; i < array.length(); i++) {
                    list.add(array.get(i));
                }
                return list;
            }
            if (type.isArray()) {
                int length = array.length();
                Object result = Array.newInstance(type.getComponentType(), length);
                for (int i = 0; i < length; i++) {
                    Array.set(result, i, convert(array.get(i), type.getComponentType()));
                }
                return result;
            }
        }
        return value;
    }

    private static List<Field> allFields(Class<?> cls) {
        List<Field> fields = new ArrayList<Field>();
        for (Class<?> current = cls; current != null && current != Object.class; current = current.getSuperclass()) {
            for (Field field : current.getDeclaredFields()) {
                fields.add(field);
            }
        }
        return fields;
    }
}