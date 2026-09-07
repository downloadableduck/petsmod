package me.shedaniel.forge.clothconfig2.gui.entries;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@SideOnly(Side.CLIENT)
public class EnumListEntry<T extends Enum<?>> extends SelectionListEntry<T> {

    public static final Function<Enum, String> DEFAULT_NAME_PROVIDER = t -> I18n.format(t instanceof Translatable ? ((Translatable) t).getKey() : t.toString());


    @Deprecated
    public EnumListEntry(String fieldName, Class<T> clazz, T value, Consumer<T> saveConsumer) {
        super(fieldName, clazz.getEnumConstants(), value, "text.cloth-config.reset_value", null, saveConsumer);
    }


    @Deprecated
    public EnumListEntry(String fieldName, Class<T> clazz, T value, String resetButtonKey, Supplier<T> defaultValue, Consumer<T> saveConsumer) {
        super(fieldName, clazz.getEnumConstants(), value, resetButtonKey, defaultValue, saveConsumer, DEFAULT_NAME_PROVIDER::apply);
    }


    @Deprecated
    public EnumListEntry(String fieldName, Class<T> clazz, T value, String resetButtonKey, Supplier<T> defaultValue, Consumer<T> saveConsumer, Function<Enum, String> enumNameProvider) {
        super(fieldName, clazz.getEnumConstants(), value, resetButtonKey, defaultValue, saveConsumer, enumNameProvider::apply, null);
    }


    @Deprecated
    public EnumListEntry(String fieldName, Class<T> clazz, T value, String resetButtonKey, Supplier<T> defaultValue, Consumer<T> saveConsumer, Function<Enum, String> enumNameProvider, Supplier<Optional<String[]>> tooltipSupplier) {
        super(fieldName, clazz.getEnumConstants(), value, resetButtonKey, defaultValue, saveConsumer, enumNameProvider::apply, tooltipSupplier, false);
    }


    @Deprecated
    public EnumListEntry(String fieldName, Class<T> clazz, T value, String resetButtonKey, Supplier<T> defaultValue, Consumer<T> saveConsumer, Function<Enum, String> enumNameProvider, Supplier<Optional<String[]>> tooltipSupplier, boolean requiresRestart) {
        super(fieldName, clazz.getEnumConstants(), value, resetButtonKey, defaultValue, saveConsumer, enumNameProvider::apply, tooltipSupplier, requiresRestart);
    }

}
