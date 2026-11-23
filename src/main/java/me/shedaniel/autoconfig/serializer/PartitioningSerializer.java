//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.autoconfig.serializer;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.util.Utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class PartitioningSerializer<T extends PartitioningSerializer.GlobalData, M extends ConfigData> implements ConfigSerializer<T> {
    private final Class<T> configClass;
    private final Map<Field, ConfigSerializer<M>> serializers;

    private PartitioningSerializer(Config definition, Class<T> configClass, Factory<M> factory) {
        this.configClass = configClass;
        this.serializers = (Map)getModuleFields(configClass).stream().collect(Utils.toLinkedMap(Function.identity(), (field) -> {
            return factory.create(createDefinition(String.format("%s/%s", definition.name(), ((Config)field.getType().getAnnotation(Config.class)).name())), (Class<M>)(Class)field.getType());
        }));
    }

    public static <T extends GlobalData, M extends ConfigData> Factory<T> wrap(Factory<M> inner) {
        return (definition, configClass) -> {
            return new PartitioningSerializer(definition, configClass, inner);
        };
    }

    private static Config createDefinition(final String name) {
        return new Config() {
            public Class<? extends Annotation> annotationType() {
                return Config.class;
            }

            public String name() {
                return name;
            }

            public int hashCode() {
                return "name".hashCode() * 127 ^ this.name().hashCode();
            }

            public boolean equals(Object obj) {
                return obj instanceof Config && ((Config)obj).name().equals(this.name());
            }
        };
    }

    private static boolean isValidModule(Field field) {
        return ConfigData.class.isAssignableFrom(field.getType()) && field.getType().isAnnotationPresent(Config.class);
    }

    private static List<Field> getModuleFields(Class<?> configClass) {
        return (List)Arrays.stream(configClass.getDeclaredFields()).filter(PartitioningSerializer::isValidModule).collect(Collectors.toList());
    }

    public void serialize(T config) throws SerializationException {
        Iterator var2 = this.serializers.entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry<Field, ConfigSerializer<M>> entry = (Map.Entry)var2.next();
            ((ConfigSerializer)entry.getValue()).serialize(Utils.getUnsafely((Field)entry.getKey(), config));
        }

    }

    public T deserialize() throws SerializationException {
        T ret = this.createDefault();
        Iterator var2 = this.serializers.entrySet().iterator();

        while(var2.hasNext()) {
            Map.Entry<Field, ConfigSerializer<M>> entry = (Map.Entry)var2.next();
            Utils.setUnsafely((Field)entry.getKey(), ret, ((ConfigSerializer)entry.getValue()).deserialize());
        }

        return ret;
    }

    public T createDefault() {
        return (T)(Object)Utils.constructUnsafely(this.configClass);
    }

    public abstract static class GlobalData implements ConfigData {
        public GlobalData() {
            Arrays.stream(this.getClass().getDeclaredFields()).filter((field) -> {
                return !PartitioningSerializer.isValidModule(field);
            }).forEach((field) -> {
                throw new RuntimeException(String.format("Invalid module: %s", field));
            });
        }

        public final void validatePostLoad() throws ValidationException {
            Iterator var1 = PartitioningSerializer.getModuleFields(this.getClass()).iterator();

            while(var1.hasNext()) {
                Field moduleField = (Field)var1.next();
                ((ConfigData)Utils.getUnsafely(moduleField, this)).validatePostLoad();
            }

        }
    }
}
