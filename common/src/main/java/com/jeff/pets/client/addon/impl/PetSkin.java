package com.jeff.pets.client.addon.impl;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Small holder class to contain skin information. skinField will be serialized, and
 * the values from the list will be considered the possible inputs that
 * a player can input.
 */
public record PetSkin(List<String> skins, Field skinField) {
}
