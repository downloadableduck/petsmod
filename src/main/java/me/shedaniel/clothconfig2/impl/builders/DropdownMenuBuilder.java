package me.shedaniel.clothconfig2.impl.builders;

import me.shedaniel.clothconfig2.gui.entries.DropdownBoxEntry;
import net.minecraft.block.Block;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class DropdownMenuBuilder<T> extends FieldBuilder<T, DropdownBoxEntry<T>> {
    private static final RenderItem RENDER_ITEM = new RenderItem();
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
        public static final Function<String, ResourceLocation> ResourceLocation_FUNCTION = str -> {
            try {
                return new ResourceLocation(str);
            } catch (NumberFormatException e) {
                return null;
            }
        };
        public static final Function<String, ResourceLocation> ITEM_ResourceLocation_FUNCTION = str -> {
            try {
                ResourceLocation ResourceLocation = new ResourceLocation(str);
                if (Item.itemRegistry.containsKey(ResourceLocation))
                    return ResourceLocation;
            } catch (Exception ignored) {
            }
            return null;
        };
        public static final Function<String, ResourceLocation> BLOCK_ResourceLocation_FUNCTION = str -> {
            try {
                ResourceLocation ResourceLocation = new ResourceLocation(str);
                if (Block.blockRegistry.containsKey(ResourceLocation))
                    return ResourceLocation;
            } catch (Exception ignored) {
            }
            return null;
        };
        public static final Function<String, Item> ITEM_FUNCTION = str -> {
            try {
                return (Item) Item.itemRegistry.getObject(new ResourceLocation(str));
            } catch (Exception ignored) {
            }
            return null;
        };
        public static final Function<String, Block> BLOCK_FUNCTION = str -> {
            try {
                return (Block) Block.blockRegistry.getObject(str);
            } catch (Exception ignored) {
            }
            return null;
        };
        private static final ItemStack BARRIER = new ItemStack(Items.snowball);

        public static <T> DropdownBoxEntry.SelectionTopCellElement<T> of(T value, Function<String, T> toObjectFunction) {
            return of(value, toObjectFunction, Object::toString);
        }

        public static <T> DropdownBoxEntry.SelectionTopCellElement<T> of(T value, Function<String, T> toObjectFunction, Function<T, String> toStringFunction) {
            return new DropdownBoxEntry.DefaultSelectionTopCellElement<>(value, toObjectFunction, toStringFunction);
        }

        public static DropdownBoxEntry.SelectionTopCellElement<ResourceLocation> ofItemResourceLocation(Item item) {
            return new DropdownBoxEntry.DefaultSelectionTopCellElement<ResourceLocation>(new ResourceLocation(Item.itemRegistry.getNameForObject(item)), ITEM_ResourceLocation_FUNCTION, ResourceLocation::toString) {
                @Override
                public void render(int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
                    textFieldWidget.xPosition = x + 4;
                    textFieldWidget.yPosition = y + 6;
                    textFieldWidget.width = (width - 4 - 20);
                    textFieldWidget.setEnabled(getParent().isEditable());
                    textFieldWidget.setTextColor(getPreferredTextColor());
                    textFieldWidget.drawTextBox();
                                        ItemStack stack = hasConfigError() ? BARRIER : new ItemStack((Item) Item.itemRegistry.getObject(getValue()));
                    RENDER_ITEM.renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRendererObj, Minecraft.getMinecraft().getTextureManager(), stack, x + width - 18, y + 2);
                }
            };
        }

        public static DropdownBoxEntry.SelectionTopCellElement<ResourceLocation> ofBlockResourceLocation(Block block) {
            return new DropdownBoxEntry.DefaultSelectionTopCellElement<ResourceLocation>(new ResourceLocation(Block.blockRegistry.getNameForObject(block)), BLOCK_ResourceLocation_FUNCTION, ResourceLocation::toString) {
                @Override
                public void render(int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
                    textFieldWidget.xPosition = x + 4;
                    textFieldWidget.yPosition = y + 6;
                    textFieldWidget.width = (width - 4 - 20);
                    textFieldWidget.setEnabled(getParent().isEditable());
                    textFieldWidget.setTextColor(getPreferredTextColor());
                    textFieldWidget.drawTextBox();
                                        ItemStack stack = hasConfigError() ? BARRIER : new ItemStack((Block) Block.blockRegistry.getObject(getValue()));
                    RENDER_ITEM.renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRendererObj, Minecraft.getMinecraft().getTextureManager(), stack, x + width - 18, y + 2);
                }
            };
        }

        public static DropdownBoxEntry.SelectionTopCellElement<Item> ofItemObject(Item item) {
            return new DropdownBoxEntry.DefaultSelectionTopCellElement<Item>(item, ITEM_FUNCTION, i -> Item.itemRegistry.getNameForObject(i).toString()) {
                @Override
                public void render(int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
                    textFieldWidget.xPosition = x + 4;
                    textFieldWidget.yPosition = y + 6;
                    textFieldWidget.width = (width - 4 - 20);
                    textFieldWidget.setEnabled(getParent().isEditable());
                    textFieldWidget.setTextColor(getPreferredTextColor());
                    textFieldWidget.drawTextBox();
                                        ItemStack stack = hasConfigError() ? BARRIER : new ItemStack(getValue());
                    RENDER_ITEM.renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRendererObj, Minecraft.getMinecraft().getTextureManager(), stack, x + width - 18, y + 2);
                }
            };
        }

        public static DropdownBoxEntry.SelectionTopCellElement<Block> ofBlockObject(Block block) {
            return new DropdownBoxEntry.DefaultSelectionTopCellElement<Block>(block, BLOCK_FUNCTION, i -> Block.blockRegistry.getNameForObject(i).toString()) {
                @Override
                public void render(int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
                    textFieldWidget.xPosition = x + 4;
                    textFieldWidget.yPosition = y + 6;
                    textFieldWidget.width = (width - 4 - 20);
                    textFieldWidget.setEnabled(getParent().isEditable());
                    textFieldWidget.setTextColor(getPreferredTextColor());
                    textFieldWidget.drawTextBox();
                                        ItemStack stack = hasConfigError() ? BARRIER : new ItemStack(getValue());
                    RENDER_ITEM.renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRendererObj, Minecraft.getMinecraft().getTextureManager(), stack, x + width - 18, y + 2);
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

        public static DropdownBoxEntry.SelectionCellCreator<ResourceLocation> ofItemResourceLocation() {
            return ofItemResourceLocation(20, 146, 7);
        }

        public static DropdownBoxEntry.SelectionCellCreator<ResourceLocation> ofItemResourceLocation(int maxItems) {
            return ofItemResourceLocation(20, 146, maxItems);
        }

        public static DropdownBoxEntry.SelectionCellCreator<ResourceLocation> ofItemResourceLocation(int cellHeight, int cellWidth, int maxItems) {
            return new DropdownBoxEntry.DefaultSelectionCellCreator<ResourceLocation>() {
                @Override
                public DropdownBoxEntry.SelectionCellElement<ResourceLocation> create(ResourceLocation selection) {
                    ItemStack s = new ItemStack((Item) Item.itemRegistry.getObject(selection));
                    return new DropdownBoxEntry.DefaultSelectionCellElement<ResourceLocation>(selection, toStringFunction) {
                        @Override
                        public void render(int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
                            rendering = true;
                            this.x = x;
                            this.y = y;
                            this.width = width;
                            this.height = height;
                            boolean b = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
                            if (b)
                                Gui.drawRect(x + 1, y + 1, x + width - 1, y + height - 1, -15132391);
                            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(toStringFunction.apply(r), x + 6 + 18, y + 6, b ? 16777215 : 8947848);
                                                        RENDER_ITEM.renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRendererObj, Minecraft.getMinecraft().getTextureManager(), s, x + 4, y + 2);
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


        public static DropdownBoxEntry.SelectionCellCreator<ResourceLocation> ofBlockResourceLocation() {
            return ofBlockResourceLocation(20, 146, 7);
        }

        public static DropdownBoxEntry.SelectionCellCreator<ResourceLocation> ofBlockResourceLocation(int maxItems) {
            return ofBlockResourceLocation(20, 146, maxItems);
        }

        public static DropdownBoxEntry.SelectionCellCreator<ResourceLocation> ofBlockResourceLocation(int cellHeight, int cellWidth, int maxItems) {
            return new DropdownBoxEntry.DefaultSelectionCellCreator<ResourceLocation>() {
                @Override
                public DropdownBoxEntry.SelectionCellElement<ResourceLocation> create(ResourceLocation selection) {
                    ItemStack s = new ItemStack((Block) Block.blockRegistry.getObject(selection));
                    return new DropdownBoxEntry.DefaultSelectionCellElement<ResourceLocation>(selection, toStringFunction) {
                        @Override
                        public void render(int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
                            rendering = true;
                            this.x = x;
                            this.y = y;
                            this.width = width;
                            this.height = height;
                            boolean b = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
                            if (b)
                                Gui.drawRect(x + 1, y + 1, x + width - 1, y + height - 1, -15132391);
                            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(toStringFunction.apply(r), x + 6 + 18, y + 6, b ? 16777215 : 8947848);
                                                        RENDER_ITEM.renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRendererObj, Minecraft.getMinecraft().getTextureManager(), s, x + 4, y + 2);
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
            return new DropdownBoxEntry.DefaultSelectionCellCreator<Item>(i -> Item.itemRegistry.getNameForObject(i).toString()) {
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
                                Gui.drawRect(x + 1, y + 1, x + width - 1, y + height - 1, -15132391);
                            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(toStringFunction.apply(r), x + 6 + 18, y + 6, b ? 16777215 : 8947848);
                                                        RENDER_ITEM.renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRendererObj, Minecraft.getMinecraft().getTextureManager(), s, x + 4, y + 2);
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
            return new DropdownBoxEntry.DefaultSelectionCellCreator<Block>(i -> Block.blockRegistry.getNameForObject(i).toString()) {
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
                                Gui.drawRect(x + 1, y + 1, x + width - 1, y + height - 1, -15132391);
                            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(toStringFunction.apply(r), x + 6 + 18, y + 6, b ? 16777215 : 8947848);
                                                        RENDER_ITEM.renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRendererObj, Minecraft.getMinecraft().getTextureManager(), s, x + 4, y + 2);
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
