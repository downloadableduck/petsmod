//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.impl;

import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.api.ConfigCategory;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ConfigCategoryImpl implements ConfigCategory {
    private final Supplier<List<ConfigPair<String, Object>>> listSupplier;
    private final Consumer<String> backgroundConsumer;
    private final Runnable destroyCategory;
    private final String categoryKey;

    ConfigCategoryImpl(String categoryKey, Consumer<String> backgroundConsumer, Supplier<List<ConfigPair<String, Object>>> listSupplier, Runnable destroyCategory) {
        this.listSupplier = listSupplier;
        this.backgroundConsumer = backgroundConsumer;
        this.categoryKey = categoryKey;
        this.destroyCategory = destroyCategory;
    }

    public String getCategoryKey() {
        return this.categoryKey;
    }

    public List<Object> getEntries() {
        return (List)((List)this.listSupplier.get()).stream().map(p -> ((ConfigPair)p).getRight()).collect(Collectors.toList());
    }

    public ConfigCategory addEntry(AbstractConfigListEntry entry) {
        ((List)this.listSupplier.get()).add(new ConfigPair(entry.getFieldName(), entry));
        return this;
    }

    public ConfigCategory setCategoryBackground(String identifier) {
        this.backgroundConsumer.accept(identifier);
        return this;
    }

    public void removeCategory() {
        this.destroyCategory.run();
    }
}
