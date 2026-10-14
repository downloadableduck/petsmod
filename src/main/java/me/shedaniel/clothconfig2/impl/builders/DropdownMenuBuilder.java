package me.shedaniel.clothconfig2.impl.builders;

import me.shedaniel.clothconfig2.gui.entries.DropdownBoxEntry;
import me.shedaniel.clothconfig2.gui.entries.DropdownBoxEntry.DefaultSelectionCellCreator;
import me.shedaniel.clothconfig2.gui.entries.DropdownBoxEntry.DefaultSelectionTopCellElement;
import me.shedaniel.clothconfig2.gui.entries.DropdownBoxEntry.SelectionCellCreator;
import me.shedaniel.clothconfig2.gui.entries.DropdownBoxEntry.SelectionTopCellElement;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.entity.ItemRenderer;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.ornithemc.osl.core.impl.util.NamespacedIdentifierImpl;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;


public class DropdownMenuBuilder<T> extends FieldBuilder<T, DropdownBoxEntry<T>> {
    /** 1.7.10 keeps no ItemRenderer on Minecraft, so dropdown cells share one instance. */
    static final ItemRenderer ITEM_RENDERER = new ItemRenderer();

    protected SelectionTopCellElement<T> topCellElement;
    protected SelectionCellCreator<T> cellCreator;
    protected Function<T, Optional<String[]>> tooltipSupplier = str -> Optional.empty();
    protected Consumer<T> saveConsumer = null;
    protected Iterable<T> selections = Collections.emptyList();
    protected boolean suggestionMode = true;
    
    public DropdownMenuBuilder(String resetButtonKey, String fieldNameKey, SelectionTopCellElement<T> topCellElement, SelectionCellCreator<T> cellCreator) {
        super(resetButtonKey, fieldNameKey);
        this.topCellElement = Objects.requireNonNull(topCellElement);
        this.cellCreator = Objects.requireNonNull(cellCreator);
    }
    
    public DropdownMenuBuilder<T> setSelections(Iterable<T> selections) {
        this.selections = selections;
        return this;
    }
    
    public DropdownMenuBuilder<T> setDefaultValue(Supplier<T> defaultValue) {
        this.defaultValue = defaultValue;
        return this;
    }
    
    public DropdownMenuBuilder<T> setDefaultValue(T defaultValue) {
        this.defaultValue = () -> Objects.requireNonNull(defaultValue);
        return this;
    }
    
    public DropdownMenuBuilder<T> setSaveConsumer(Consumer<T> saveConsumer) {
        this.saveConsumer = saveConsumer;
        return this;
    }
    
    public DropdownMenuBuilder<T> setTooltipSupplier(Supplier<Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = str -> tooltipSupplier.get();
        return this;
    }
    
    public DropdownMenuBuilder<T> setTooltipSupplier(Function<T, Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = tooltipSupplier;
        return this;
    }
    
    public DropdownMenuBuilder<T> setTooltip(Optional<String[]> tooltip) {
        this.tooltipSupplier = str -> tooltip;
        return this;
    }
    
    public DropdownMenuBuilder<T> setTooltip(String... tooltip) {
        this.tooltipSupplier = str -> Optional.ofNullable(tooltip);
        return this;
    }
    
    public DropdownMenuBuilder<T> requireRestart() {
        requireRestart(true);
        return this;
    }
    
    public DropdownMenuBuilder<T> setErrorSupplier(Function<T, Optional<String>> errorSupplier) {
        this.errorSupplier = errorSupplier;
        return this;
    }
    
    public DropdownMenuBuilder<T> setSuggestionMode(boolean suggestionMode) {
        this.suggestionMode = suggestionMode;
        return this;
    }
    
    public boolean isSuggestionMode() {
        return suggestionMode;
    }
    
    @NotNull
    @Override
    public DropdownBoxEntry<T> build() {
        DropdownBoxEntry<T> entry = new DropdownBoxEntry<>(getFieldNameKey(), getResetButtonKey(), null, false, defaultValue, saveConsumer, selections, topCellElement, cellCreator);
        entry.setTooltipSupplier(() -> tooltipSupplier.apply(entry.getValue()));
        if (errorSupplier != null)
            entry.setErrorSupplier(() -> errorSupplier.apply(entry.getValue()));
        entry.setSuggestionMode(suggestionMode);
        return entry;
    }
    
    public static class TopCellElementBuilder {
        public static final Function<String, String> String_FUNCTION = str -> {
            try {
                return (str);
            } catch (NumberFormatException e) {
                return null;
            }
        };
        public static final Function<String, String> ITEM_String_FUNCTION = str -> {
            try {
                String string = (str);
                if (Item.REGISTRY.get(new NamespacedIdentifierImpl("minecraft", string)) != null)
                    return string;
            } catch (Exception ignored) {
            }
            return null;
        };
        public static final Function<String, String> BLOCK_String_FUNCTION = str -> {
            try {
                String String = (str);
                if (Block.REGISTRY.get(new NamespacedIdentifierImpl("minecraft", String)) != null)
                    return String;
            } catch (Exception ignored) {
            }
            return null;
        };
        public static final Function<String, Item> ITEM_FUNCTION = str -> {
            try {
                return (Item) Item.REGISTRY.get((new NamespacedIdentifierImpl("minecraft", str)));
            } catch (Exception ignored) {
            }
            return null;
        };
        public static final Function<String, Block> BLOCK_FUNCTION = str -> {
            try {
                return (Block) Block.REGISTRY.get((new NamespacedIdentifierImpl("minecraft", str)));
            } catch (Exception ignored) {
            }
            return null;
        };
        private static final ItemStack BARRIER = new ItemStack(Block.BEDROCK);
        
        public static <T> SelectionTopCellElement<T> of(T value, Function<String, T> toObjectFunction) {
            return of(value, toObjectFunction, Object::toString);
        }
        
        public static <T> SelectionTopCellElement<T> of(T value, Function<String, T> toObjectFunction, Function<T, String> toStringFunction) {
            return new DefaultSelectionTopCellElement<>(value, toObjectFunction, toStringFunction);
        }
        
        public static SelectionTopCellElement<String> ofItemString(Item item) {
            return new DefaultSelectionTopCellElement<String>((Item.REGISTRY.getKey(item).toString()), ITEM_String_FUNCTION, String::toString) {
                @Override
                public void render(int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
                    textFieldWidget.x = x + 4;
                    textFieldWidget.y = y + 6;
                    // textFieldWidget.setWidth(width - 4 - 20); // Not in 1.13
                    textFieldWidget.setEditable(getParent().isEditable());
                    textFieldWidget.setEditableColor(getPreferredTextColor());
                    textFieldWidget.render();
                    ItemRenderer itemRenderer = DropdownMenuBuilder.ITEM_RENDERER;
                    ItemStack stack = hasConfigError() ? BARRIER : new ItemStack((Item) Item.REGISTRY.get(new NamespacedIdentifierImpl("minecraft", getValue())));
                    itemRenderer.renderGuiItem(Minecraft.getInstance().textRenderer, Minecraft.getInstance().textureManager, stack, x + width - 18, y + 2);
                }
            };
        }
        
        public static SelectionTopCellElement<String> ofBlockString(Block block) {
            return new DefaultSelectionTopCellElement<String>((Block.REGISTRY.getKey(block).toString()), BLOCK_String_FUNCTION, String::toString) {
                @Override
                public void render(int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
                    textFieldWidget.x = x + 4;
                    textFieldWidget.y = y + 6;
                    // textFieldWidget.setWidth(width - 4 - 20); // Not in 1.13
                    textFieldWidget.setEditable(getParent().isEditable());
                    textFieldWidget.setEditableColor(getPreferredTextColor());
                    textFieldWidget.render();
                    ItemRenderer itemRenderer = DropdownMenuBuilder.ITEM_RENDERER;
                    ItemStack stack = hasConfigError() ? BARRIER : new ItemStack((Block) Block.REGISTRY.get(new NamespacedIdentifierImpl("minecraft", getValue())));
                    itemRenderer.renderGuiItem(Minecraft.getInstance().textRenderer, Minecraft.getInstance().textureManager, stack, x + width - 18, y + 2);
                }
            };
        }
        
        public static SelectionTopCellElement<Item> ofItemObject(Item item) {
            return new DefaultSelectionTopCellElement<Item>(item, ITEM_FUNCTION, i -> Item.REGISTRY.getKey(i).toString()) {
                @Override
                public void render(int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
                    textFieldWidget.x = x + 4;
                    textFieldWidget.y = y + 6;
                    // textFieldWidget.setWidth(width - 4 - 20); // Not in 1.13
                    textFieldWidget.setEditable(getParent().isEditable());
                    textFieldWidget.setEditableColor(getPreferredTextColor());
                    textFieldWidget.render();
                    ItemRenderer itemRenderer = DropdownMenuBuilder.ITEM_RENDERER;
                    ItemStack stack = hasConfigError() ? BARRIER : new ItemStack(getValue());
                    itemRenderer.renderGuiItem(Minecraft.getInstance().textRenderer, Minecraft.getInstance().textureManager, stack, x + width - 18, y + 2);
                }
            };
        }
        
        public static SelectionTopCellElement<Block> ofBlockObject(Block block) {
            return new DefaultSelectionTopCellElement<Block>(block, BLOCK_FUNCTION, i -> Block.REGISTRY.getKey(i).toString()) {
                @Override
                public void render(int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
                    textFieldWidget.x = x + 4;
                    textFieldWidget.y = y + 6;
                    // textFieldWidget.setWidth(width - 4 - 20); // Not in 1.13
                    textFieldWidget.setEditable(getParent().isEditable());
                    textFieldWidget.setEditableColor(getPreferredTextColor());
                    textFieldWidget.render();
                    ItemRenderer itemRenderer = DropdownMenuBuilder.ITEM_RENDERER;
                    ItemStack stack = hasConfigError() ? BARRIER : new ItemStack(getValue());
                    itemRenderer.renderGuiItem(Minecraft.getInstance().textRenderer, Minecraft.getInstance().textureManager, stack, x + width - 18, y + 2);
                }
            };
        }
    }
    
    public static class CellCreatorBuilder {
        public static <T> SelectionCellCreator<T> of() {
            return new DefaultSelectionCellCreator<>();
        }
        
        public static <T> SelectionCellCreator<T> of(Function<T, String> toStringFunction) {
            return new DefaultSelectionCellCreator<>(toStringFunction);
        }
        
        public static <T> SelectionCellCreator<T> ofWidth(int cellWidth) {
            return new DefaultSelectionCellCreator<T>() {
                @Override
                public int getCellWidth() {
                    return cellWidth;
                }
            };
        }
        
        public static <T> SelectionCellCreator<T> ofWidth(int cellWidth, Function<T, String> toStringFunction) {
            return new DefaultSelectionCellCreator<T>(toStringFunction) {
                @Override
                public int getCellWidth() {
                    return cellWidth;
                }
            };
        }
        
        public static <T> SelectionCellCreator<T> ofCellCount(int maxItems) {
            return new DefaultSelectionCellCreator<T>() {
                @Override
                public int getDropBoxMaxHeight() {
                    return getCellHeight() * maxItems;
                }
            };
        }
        
        public static <T> SelectionCellCreator<T> ofCellCount(int maxItems, Function<T, String> toStringFunction) {
            return new DefaultSelectionCellCreator<T>(toStringFunction) {
                @Override
                public int getDropBoxMaxHeight() {
                    return getCellHeight() * maxItems;
                }
            };
        }
        
        public static <T> SelectionCellCreator<T> of(int cellWidth, int maxItems) {
            return new DefaultSelectionCellCreator<T>() {
                @Override
                public int getCellWidth() {
                    return cellWidth;
                }
                
                @Override
                public int getDropBoxMaxHeight() {
                    return getCellHeight() * maxItems;
                }
            };
        }
        
        public static <T> SelectionCellCreator<T> of(int cellWidth, int maxItems, Function<T, String> toStringFunction) {
            return new DefaultSelectionCellCreator<T>(toStringFunction) {
                @Override
                public int getCellWidth() {
                    return cellWidth;
                }
                
                @Override
                public int getDropBoxMaxHeight() {
                    return getCellHeight() * maxItems;
                }
            };
        }
        
        public static <T> SelectionCellCreator<T> of(int cellHeight, int cellWidth, int maxItems) {
            return new DefaultSelectionCellCreator<T>() {
                @Override
                public int getCellHeight() {
                    return cellHeight;
                }
                
                @Override
                public int getCellWidth() {
                    return cellWidth;
                }
                
                @Override
                public int getDropBoxMaxHeight() {
                    return getCellHeight() * maxItems;
                }
            };
        }
        
        public static <T> SelectionCellCreator<T> of(int cellHeight, int cellWidth, int maxItems, Function<T, String> toStringFunction) {
            return new DefaultSelectionCellCreator<T>(toStringFunction) {
                @Override
                public int getCellHeight() {
                    return cellHeight;
                }
                
                @Override
                public int getCellWidth() {
                    return cellWidth;
                }
                
                @Override
                public int getDropBoxMaxHeight() {
                    return getCellHeight() * maxItems;
                }
            };
        }
        
        public static SelectionCellCreator<String> ofItemString() {
            return ofItemString(20, 146, 7);
        }
        
        public static SelectionCellCreator<String> ofItemString(int maxItems) {
            return ofItemString(20, 146, maxItems);
        }
        
        public static SelectionCellCreator<String> ofItemString(int cellHeight, int cellWidth, int maxItems) {
            return new DefaultSelectionCellCreator<String>() {
                @Override
                public DropdownBoxEntry.SelectionCellElement<String> create(String selection) {
                    ItemStack s = new ItemStack((Item) Item.REGISTRY.get(new NamespacedIdentifierImpl("minecraft", selection)));
                    return new DropdownBoxEntry.DefaultSelectionCellElement<String>(selection, toStringFunction) {
                        @Override
                        public void render(int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
                            rendering = true;
                            this.x = x;
                            this.y = y;
                            this.width = width;
                            this.height = height;
                            boolean b = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
                            if (b)
                                fill(x + 1, y + 1, x + width - 1, y + height - 1, -15132391);
                            Minecraft.getInstance().textRenderer.drawWithShadow(toStringFunction.apply(r), x + 6 + 18, y + 6, b ? 16777215 : 8947848);
                            ItemRenderer itemRenderer = DropdownMenuBuilder.ITEM_RENDERER;
                            itemRenderer.renderGuiItem(Minecraft.getInstance().textRenderer, Minecraft.getInstance().textureManager, s, x + 4, y + 2);
                        }
                    };
                }
                
                @Override
                public int getCellHeight() {
                    return cellHeight;
                }
                
                @Override
                public int getCellWidth() {
                    return cellWidth;
                }
                
                @Override
                public int getDropBoxMaxHeight() {
                    return getCellHeight() * maxItems;
                }
            };
        }
        
        
        public static SelectionCellCreator<String> ofBlockString() {
            return ofBlockString(20, 146, 7);
        }
        
        public static SelectionCellCreator<String> ofBlockString(int maxItems) {
            return ofBlockString(20, 146, maxItems);
        }
        
        public static SelectionCellCreator<String> ofBlockString(int cellHeight, int cellWidth, int maxItems) {
            return new DefaultSelectionCellCreator<String>() {
                @Override
                public DropdownBoxEntry.SelectionCellElement<String> create(String selection) {
                    ItemStack s = new ItemStack((Block) Block.REGISTRY.get(new NamespacedIdentifierImpl("minecraft", selection)));
                    return new DropdownBoxEntry.DefaultSelectionCellElement<String>(selection, toStringFunction) {
                        @Override
                        public void render(int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
                            rendering = true;
                            this.x = x;
                            this.y = y;
                            this.width = width;
                            this.height = height;
                            boolean b = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
                            if (b)
                                fill(x + 1, y + 1, x + width - 1, y + height - 1, -15132391);
                            Minecraft.getInstance().textRenderer.drawWithShadow(toStringFunction.apply(r), x + 6 + 18, y + 6, b ? 16777215 : 8947848);
                            ItemRenderer itemRenderer = DropdownMenuBuilder.ITEM_RENDERER;
                            itemRenderer.renderGuiItem(Minecraft.getInstance().textRenderer, Minecraft.getInstance().textureManager, s, x + 4, y + 2);
                        }
                    };
                }
                
                @Override
                public int getCellHeight() {
                    return cellHeight;
                }
                
                @Override
                public int getCellWidth() {
                    return cellWidth;
                }
                
                @Override
                public int getDropBoxMaxHeight() {
                    return getCellHeight() * maxItems;
                }
            };
        }
        
        public static SelectionCellCreator<Item> ofItemObject() {
            return ofItemObject(20, 146, 7);
        }
        
        public static SelectionCellCreator<Item> ofItemObject(int maxItems) {
            return ofItemObject(20, 146, maxItems);
        }
        
        public static SelectionCellCreator<Item> ofItemObject(int cellHeight, int cellWidth, int maxItems) {
            return new DefaultSelectionCellCreator<Item>(i -> Item.REGISTRY.getKey(i).toString()) {
                @Override
                public DropdownBoxEntry.SelectionCellElement<Item> create(Item selection) {
                    ItemStack s = new ItemStack(selection);
                    return new DropdownBoxEntry.DefaultSelectionCellElement<Item>(selection, toStringFunction) {
                        @Override
                        public void render(int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
                            rendering = true;
                            this.x = x;
                            this.y = y;
                            this.width = width;
                            this.height = height;
                            boolean b = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
                            if (b)
                                fill(x + 1, y + 1, x + width - 1, y + height - 1, -15132391);
                            Minecraft.getInstance().textRenderer.drawWithShadow(toStringFunction.apply(r), x + 6 + 18, y + 6, b ? 16777215 : 8947848);
                            ItemRenderer itemRenderer = DropdownMenuBuilder.ITEM_RENDERER;
                            itemRenderer.renderGuiItem(Minecraft.getInstance().textRenderer, Minecraft.getInstance().textureManager, s, x + 4, y + 2);
                        }
                    };
                }
                
                @Override
                public int getCellHeight() {
                    return cellHeight;
                }
                
                @Override
                public int getCellWidth() {
                    return cellWidth;
                }
                
                @Override
                public int getDropBoxMaxHeight() {
                    return getCellHeight() * maxItems;
                }
            };
        }
        
        public static SelectionCellCreator<Block> ofBlockObject() {
            return ofBlockObject(20, 146, 7);
        }
        
        public static SelectionCellCreator<Block> ofBlockObject(int maxItems) {
            return ofBlockObject(20, 146, maxItems);
        }
        
        public static SelectionCellCreator<Block> ofBlockObject(int cellHeight, int cellWidth, int maxItems) {
            return new DefaultSelectionCellCreator<Block>(i -> Block.REGISTRY.getKey(i).toString()) {
                @Override
                public DropdownBoxEntry.SelectionCellElement<Block> create(Block selection) {
                    ItemStack s = new ItemStack(selection);
                    return new DropdownBoxEntry.DefaultSelectionCellElement<Block>(selection, toStringFunction) {
                        @Override
                        public void render(int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
                            rendering = true;
                            this.x = x;
                            this.y = y;
                            this.width = width;
                            this.height = height;
                            boolean b = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
                            if (b)
                                fill(x + 1, y + 1, x + width - 1, y + height - 1, -15132391);
                            Minecraft.getInstance().textRenderer.drawWithShadow(toStringFunction.apply(r), x + 6 + 18, y + 6, b ? 16777215 : 8947848);
                            ItemRenderer itemRenderer = DropdownMenuBuilder.ITEM_RENDERER;
                            itemRenderer.renderGuiItem(Minecraft.getInstance().textRenderer, Minecraft.getInstance().textureManager, s, x + 4, y + 2);
                        }
                    };
                }
                
                @Override
                public int getCellHeight() {
                    return cellHeight;
                }
                
                @Override
                public int getCellWidth() {
                    return cellWidth;
                }
                
                @Override
                public int getDropBoxMaxHeight() {
                    return getCellHeight() * maxItems;
                }
            };
        }
    }
}
