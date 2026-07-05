package com.jeff.pets.yacl3;

import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.controller.ValueFormattableController;

public interface EnumDropdownControllerBuilder<E extends Enum<E>> extends ValueFormattableController<E, EnumDropdownControllerBuilder<E>> {
    static <E extends Enum<E>> EnumDropdownControllerBuilder<E> create(Option<E> option) {
        return new EnumDropdownControllerBuilderImpl<>(option);
    }
}