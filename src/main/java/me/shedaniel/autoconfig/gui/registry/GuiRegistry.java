//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.autoconfig.gui.registry;

import me.shedaniel.autoconfig.gui.registry.api.GuiProvider;
import me.shedaniel.autoconfig.gui.registry.api.GuiRegistryAccess;
import me.shedaniel.autoconfig.gui.registry.api.GuiTransformer;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class GuiRegistry implements GuiRegistryAccess {
    private final Map<Priority, List<ProviderEntry>> providers = new HashMap();
    private final List<TransformerEntry> transformers = new ArrayList();

    public GuiRegistry() {
        Priority[] var1 = me.shedaniel.autoconfig.gui.registry.GuiRegistry.Priority.values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Priority priority = var1[var3];
            this.providers.put(priority, new ArrayList());
        }

    }

    private static <T> Optional<T> firstPresent(Stream<Supplier<Optional<T>>> optionals) {
        return (Optional)optionals.map(Supplier::get).filter(Optional::isPresent).findFirst().orElse(Optional.empty());
    }

    public List<AbstractConfigListEntry> get(String i18n, Field field, Object config, Object defaults, GuiRegistryAccess registry) {
        Optional<ProviderEntry> found = Arrays.stream(GuiRegistry.Priority.values())
            .map(priority -> ((List<ProviderEntry>)this.providers.get(priority)).stream().filter(entry -> entry.predicate.test(field)).findFirst())
            .filter(Optional::isPresent)
            .map(Optional::get)
            .findFirst();
        return found.map(entry -> entry.provider.get(i18n, field, config, defaults, registry)).orElse(null);
    }

    public List<AbstractConfigListEntry> transform(List<AbstractConfigListEntry> guis, String i18n, Field field, Object config, Object defaults, GuiRegistryAccess registry) {
        List<GuiTransformer> matchedTransformers = this.transformers.stream().filter((entry) -> {
            return entry.predicate.test(field);
        }).map((entry) -> {
            return entry.transformer;
        }).collect(Collectors.toList());

        GuiTransformer transformer;
        for(Iterator var8 = matchedTransformers.iterator(); var8.hasNext(); guis = transformer.transform(guis, i18n, field, config, defaults, registry)) {
            transformer = (GuiTransformer)var8.next();
        }

        return guis;
    }

    private void registerProvider(Priority priority, GuiProvider provider, Predicate<Field> predicate) {
        ((List)this.providers.computeIfAbsent(priority, (p) -> {
            return new ArrayList();
        })).add(new ProviderEntry(predicate, provider));
    }

    public void registerTypeProvider(GuiProvider provider, Class... types) {
        Class[] var3 = types;
        int var4 = types.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Class type = var3[var5];
            this.registerProvider(me.shedaniel.autoconfig.gui.registry.GuiRegistry.Priority.LAST, provider, (field) -> {
                return type == field.getType();
            });
        }

    }

    public void registerPredicateProvider(GuiProvider provider, Predicate<Field> predicate) {
        this.registerProvider(me.shedaniel.autoconfig.gui.registry.GuiRegistry.Priority.NORMAL, provider, predicate);
    }

    @SafeVarargs
    public final void registerAnnotationProvider(GuiProvider provider, Class<? extends Annotation>... types) {
        Class[] var3 = types;
        int var4 = types.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Class<? extends Annotation> type = var3[var5];
            this.registerProvider(me.shedaniel.autoconfig.gui.registry.GuiRegistry.Priority.FIRST, provider, (field) -> {
                return field.isAnnotationPresent(type);
            });
        }

    }

    @SafeVarargs
    public final void registerAnnotationProvider(GuiProvider provider, Predicate<Field> predicate, Class<? extends Annotation>... types) {
        Class[] var4 = types;
        int var5 = types.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Class<? extends Annotation> type = var4[var6];
            this.registerProvider(me.shedaniel.autoconfig.gui.registry.GuiRegistry.Priority.FIRST, provider, (field) -> {
                return predicate.test(field) && field.isAnnotationPresent(type);
            });
        }

    }

    public void registerPredicateTransformer(GuiTransformer transformer, Predicate<Field> predicate) {
        this.transformers.add(new TransformerEntry(predicate, transformer));
    }

    @SafeVarargs
    public final void registerAnnotationTransformer(GuiTransformer transformer, Class<? extends Annotation>... types) {
        this.registerAnnotationTransformer(transformer, (field) -> {
            return true;
        }, types);
    }

    @SafeVarargs
    public final void registerAnnotationTransformer(GuiTransformer transformer, Predicate<Field> predicate, Class<? extends Annotation>... types) {
        Class[] var4 = types;
        int var5 = types.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Class<? extends Annotation> type = var4[var6];
            this.registerPredicateTransformer(transformer, (field) -> {
                return predicate.test(field) && field.isAnnotationPresent(type);
            });
        }

    }

    private static enum Priority {
        FIRST,
        NORMAL,
        LAST;

        private Priority() {
        }
    }

    private static class ProviderEntry {
        final Predicate<Field> predicate;
        final GuiProvider provider;

        ProviderEntry(Predicate<Field> predicate, GuiProvider provider) {
            this.predicate = predicate;
            this.provider = provider;
        }
    }

    private static class TransformerEntry {
        final Predicate<Field> predicate;
        final GuiTransformer transformer;

        TransformerEntry(Predicate<Field> predicate, GuiTransformer transformer) {
            this.predicate = predicate;
            this.transformer = transformer;
        }
    }
}
