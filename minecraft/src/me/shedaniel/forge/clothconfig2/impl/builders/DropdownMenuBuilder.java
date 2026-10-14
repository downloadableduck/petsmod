package me.shedaniel.forge.clothconfig2.impl.builders;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.RenderItem;
import me.shedaniel.forge.clothconfig2.gui.entries.DropdownBoxEntry;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static net.minecraft.src.Gui.drawRect;


public class DropdownMenuBuilder<T> extends FieldBuilder<T, DropdownBoxEntry<T>> {
    protected DropdownBoxEntry.SelectionTopCellElement<T> topCellElement;
    protected DropdownBoxEntry.SelectionCellCreator<T> cellCreator;
    protected Function<T, Optional<String[]>> tooltipSupplier = str -> Optional.empty();
    protected Consumer<T> saveConsumer = null;
    protected Iterable<T> selections = Collections.emptyList();

    public DropdownMenuBuilder(String resetButtonKey, String fieldNameKey, DropdownBoxEntry.SelectionTopCellElement<T> topCellElement, DropdownBoxEntry.SelectionCellCreator<T> cellCreator) {
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


    @Override
    public DropdownBoxEntry<T> build() {
        DropdownBoxEntry<T> entry = new DropdownBoxEntry<>(getFieldNameKey(), getResetButtonKey(), null, isRequireRestart(), defaultValue, saveConsumer, selections, topCellElement, cellCreator);
        entry.setTooltipSupplier(() -> tooltipSupplier.apply(entry.getValue()));
        if (errorSupplier != null)
            entry.setErrorSupplier(() -> errorSupplier.apply(entry.getValue()));
        return entry;
    }

    public static class TopCellElementBuilder {
        public static final Function<String, Item> ITEM_FUNCTION = str -> {
            try {
                int id = str.indexOf(':') >= 0 ? Integer.parseInt(str.substring(str.indexOf(':') + 1)) : Integer.parseInt(str);
                if (id >= 0 && id < Item.itemsList.length && Item.itemsList[id] != null) return Item.itemsList[id];
            } catch (Exception ignored) {
            }
            return null;
        };
        public static final Function<String, Block> BLOCK_FUNCTION = str -> {
            try {
                int id = str.indexOf(':') >= 0 ? Integer.parseInt(str.substring(str.indexOf(':') + 1)) : Integer.parseInt(str);
                if (id >= 0 && id < Block.blocksList.length && Block.blocksList[id] != null) return Block.blocksList[id];
            } catch (Exception ignored) {
            }
            return null;
        };
        public static final Function<String, String> IDENTIFIER_FUNCTION = str -> str;
        public static final Function<String, String> ITEM_IDENTIFIER_FUNCTION = str -> ITEM_FUNCTION.apply(str) == null ? null : str;
        public static final Function<String, String> BLOCK_IDENTIFIER_FUNCTION = str -> BLOCK_FUNCTION.apply(str) == null ? null : str;
        private static final ItemStack BARRIER = new ItemStack(Item.snowball);

        public static <T> DropdownBoxEntry.SelectionTopCellElement<T> of(T value, Function<String, T> toObjectFunction) {
            return of(value, toObjectFunction, Object::toString);
        }

        public static <T> DropdownBoxEntry.SelectionTopCellElement<T> of(T value, Function<String, T> toObjectFunction, Function<T, String> toStringFunction) {
            return new DropdownBoxEntry.DefaultSelectionTopCellElement<>(value, toObjectFunction, toStringFunction);
        }

        public static DropdownBoxEntry.SelectionTopCellElement<Item> ofItemObject(Item item) {
            return new DropdownBoxEntry.DefaultSelectionTopCellElement<Item>(item, ITEM_FUNCTION, Item::getUnlocalizedName) {
                @Override
                public void render(int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
                    textFieldWidget.setXPosition(x + 4);
                    textFieldWidget.setYPosition(y + 6);
                    textFieldWidget.setWidth(width - 4 - 20);
                    textFieldWidget.setEnabled(getParent().isEditable());
                    textFieldWidget.setTextColor(getPreferredTextColor());
                    textFieldWidget.drawTextBox();
                    RenderItem itemRenderer = new RenderItem();
                    ItemStack stack = hasConfigError() ? BARRIER : new ItemStack(getValue());
                    itemRenderer.renderItemIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().renderEngine, stack, x + width - 18, y + 2);
                }
            };
        }

        public static DropdownBoxEntry.SelectionTopCellElement<Block> ofBlockObject(Block block) {
            return new DropdownBoxEntry.DefaultSelectionTopCellElement<Block>(block, BLOCK_FUNCTION, Block::getUnlocalizedName) {
                @Override
                public void render(int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
                    textFieldWidget.setXPosition(x + 4);
                    textFieldWidget.setYPosition(y + 6);
                    textFieldWidget.setWidth(width - 4 - 20);
                    textFieldWidget.setEnabled(getParent().isEditable());
                    textFieldWidget.setTextColor(getPreferredTextColor());
                    textFieldWidget.drawTextBox();
                    RenderItem itemRenderer = new RenderItem();
                    ItemStack stack = hasConfigError() ? BARRIER : new ItemStack(getValue());
                    itemRenderer.renderItemIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().renderEngine, stack, x + width - 18, y + 2);
                }
            };
        }
    }

    public static class CellCreatorBuilder {
        public static <T> DropdownBoxEntry.SelectionCellCreator<T> of() {
            return new DropdownBoxEntry.DefaultSelectionCellCreator<>();
        }

        public static <T> DropdownBoxEntry.SelectionCellCreator<T> of(Function<T, String> toStringFunction) {
            return new DropdownBoxEntry.DefaultSelectionCellCreator<>(toStringFunction);
        }

        public static <T> DropdownBoxEntry.SelectionCellCreator<T> ofWidth(int cellWidth) {
            return new DropdownBoxEntry.DefaultSelectionCellCreator<T>() {
                @Override
                public int getCellWidth() {
                    return cellWidth;
                }
            };
        }

        public static <T> DropdownBoxEntry.SelectionCellCreator<T> ofWidth(int cellWidth, Function<T, String> toStringFunction) {
            return new DropdownBoxEntry.DefaultSelectionCellCreator<T>(toStringFunction) {
                @Override
                public int getCellWidth() {
                    return cellWidth;
                }
            };
        }

        public static <T> DropdownBoxEntry.SelectionCellCreator<T> ofCellCount(int maxItems) {
            return new DropdownBoxEntry.DefaultSelectionCellCreator<T>() {
                @Override
                public int getDropBoxMaxHeight() {
                    return getCellHeight() * maxItems;
                }
            };
        }

        public static <T> DropdownBoxEntry.SelectionCellCreator<T> ofCellCount(int maxItems, Function<T, String> toStringFunction) {
            return new DropdownBoxEntry.DefaultSelectionCellCreator<T>(toStringFunction) {
                @Override
                public int getDropBoxMaxHeight() {
                    return getCellHeight() * maxItems;
                }
            };
        }

        public static <T> DropdownBoxEntry.SelectionCellCreator<T> of(int cellWidth, int maxItems) {
            return new DropdownBoxEntry.DefaultSelectionCellCreator<T>() {
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

        public static <T> DropdownBoxEntry.SelectionCellCreator<T> of(int cellWidth, int maxItems, Function<T, String> toStringFunction) {
            return new DropdownBoxEntry.DefaultSelectionCellCreator<T>(toStringFunction) {
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

        public static <T> DropdownBoxEntry.SelectionCellCreator<T> of(int cellHeight, int cellWidth, int maxItems) {
            return new DropdownBoxEntry.DefaultSelectionCellCreator<T>() {
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

        public static <T> DropdownBoxEntry.SelectionCellCreator<T> of(int cellHeight, int cellWidth, int maxItems, Function<T, String> toStringFunction) {
            return new DropdownBoxEntry.DefaultSelectionCellCreator<T>(toStringFunction) {
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

        public static DropdownBoxEntry.SelectionCellCreator<String> ofItemIdentifier() {
            return ofItemIdentifier(20, 146, 7);
        }

        public static DropdownBoxEntry.SelectionCellCreator<String> ofItemIdentifier(int maxItems) {
            return ofItemIdentifier(20, 146, maxItems);
        }

        public static DropdownBoxEntry.SelectionCellCreator<String> ofItemIdentifier(int cellHeight, int cellWidth, int maxItems) {
            return new DropdownBoxEntry.DefaultSelectionCellCreator<String>() {
                @Override
                public DropdownBoxEntry.SelectionCellElement<String> create(String selection) {
                    ItemStack s = new ItemStack(TopCellElementBuilder.ITEM_FUNCTION.apply(selection));
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
                                drawRect(x + 1, y + 1, x + width - 1, y + height - 1, -15132391);
                            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(toStringFunction.apply(r), x + 6 + 18, y + 6, b ? 16777215 : 8947848);
                            RenderItem itemRenderer = new RenderItem();
                            itemRenderer.renderItemIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().renderEngine, s, x + 4, y + 2);
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


        public static DropdownBoxEntry.SelectionCellCreator<String> ofBlockIdentifier() {
            return ofBlockIdentifier(20, 146, 7);
        }

        public static DropdownBoxEntry.SelectionCellCreator<String> ofBlockIdentifier(int maxItems) {
            return ofBlockIdentifier(20, 146, maxItems);
        }

        public static DropdownBoxEntry.SelectionCellCreator<String> ofBlockIdentifier(int cellHeight, int cellWidth, int maxItems) {
            return new DropdownBoxEntry.DefaultSelectionCellCreator<String>() {
                @Override
                public DropdownBoxEntry.SelectionCellElement<String> create(String selection) {
                    ItemStack s = new ItemStack(TopCellElementBuilder.BLOCK_FUNCTION.apply(selection));
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
                                drawRect(x + 1, y + 1, x + width - 1, y + height - 1, -15132391);
                            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(toStringFunction.apply(r), x + 6 + 18, y + 6, b ? 16777215 : 8947848);
                            RenderItem itemRenderer = new RenderItem();
                            itemRenderer.renderItemIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().renderEngine, s, x + 4, y + 2);
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

        public static DropdownBoxEntry.SelectionCellCreator<Item> ofItemObject() {
            return ofItemObject(20, 146, 7);
        }

        public static DropdownBoxEntry.SelectionCellCreator<Item> ofItemObject(int maxItems) {
            return ofItemObject(20, 146, maxItems);
        }

        public static DropdownBoxEntry.SelectionCellCreator<Item> ofItemObject(int cellHeight, int cellWidth, int maxItems) {
            return new DropdownBoxEntry.DefaultSelectionCellCreator<Item>(Item::getUnlocalizedName) {
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
                                drawRect(x + 1, y + 1, x + width - 1, y + height - 1, -15132391);
                            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(toStringFunction.apply(r), x + 6 + 18, y + 6, b ? 16777215 : 8947848);
                            RenderItem itemRenderer = new RenderItem();
                            itemRenderer.renderItemIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().renderEngine, s, x + 4, y + 2);
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

        public static DropdownBoxEntry.SelectionCellCreator<Block> ofBlockObject() {
            return ofBlockObject(20, 146, 7);
        }

        public static DropdownBoxEntry.SelectionCellCreator<Block> ofBlockObject(int maxItems) {
            return ofBlockObject(20, 146, maxItems);
        }

        public static DropdownBoxEntry.SelectionCellCreator<Block> ofBlockObject(int cellHeight, int cellWidth, int maxItems) {
            return new DropdownBoxEntry.DefaultSelectionCellCreator<Block>(Block::getUnlocalizedName) {
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
                                drawRect(x + 1, y + 1, x + width - 1, y + height - 1, -15132391);
                            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(toStringFunction.apply(r), x + 6 + 18, y + 6, b ? 16777215 : 8947848);
                            RenderItem itemRenderer = new RenderItem();
                            itemRenderer.renderItemIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().renderEngine, s, x + 4, y + 2);
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
