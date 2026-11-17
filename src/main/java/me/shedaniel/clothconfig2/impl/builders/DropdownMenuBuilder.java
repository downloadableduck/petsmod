//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.impl.builders;

import me.shedaniel.clothconfig2.gui.entries.DropdownBoxEntry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class DropdownMenuBuilder<T> extends FieldBuilder<T, DropdownBoxEntry<T>> {
    static final ItemRenderer ITEM_RENDERER = new ItemRenderer();
    protected DropdownBoxEntry.SelectionTopCellElement<T> topCellElement;
    protected DropdownBoxEntry.SelectionCellCreator<T> cellCreator;
    protected Function<T, Optional<String[]>> tooltipSupplier = (str) -> {
        return Optional.empty();
    };
    protected Consumer<T> saveConsumer = null;
    protected Iterable<T> selections = Collections.emptyList();
    protected boolean suggestionMode = true;

    public DropdownMenuBuilder(String resetButtonKey, String fieldNameKey, DropdownBoxEntry.SelectionTopCellElement<T> topCellElement, DropdownBoxEntry.SelectionCellCreator<T> cellCreator) {
        super(resetButtonKey, fieldNameKey);
        this.topCellElement = (DropdownBoxEntry.SelectionTopCellElement)Objects.requireNonNull(topCellElement);
        this.cellCreator = (DropdownBoxEntry.SelectionCellCreator)Objects.requireNonNull(cellCreator);
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
        this.defaultValue = () -> {
            return Objects.requireNonNull(defaultValue);
        };
        return this;
    }

    public DropdownMenuBuilder<T> setSaveConsumer(Consumer<T> saveConsumer) {
        this.saveConsumer = saveConsumer;
        return this;
    }

    public DropdownMenuBuilder<T> setTooltipSupplier(Supplier<Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = (str) -> {
            return (Optional)tooltipSupplier.get();
        };
        return this;
    }

    public DropdownMenuBuilder<T> setTooltipSupplier(Function<T, Optional<String[]>> tooltipSupplier) {
        this.tooltipSupplier = tooltipSupplier;
        return this;
    }

    public DropdownMenuBuilder<T> setTooltip(Optional<String[]> tooltip) {
        this.tooltipSupplier = (str) -> {
            return tooltip;
        };
        return this;
    }

    public DropdownMenuBuilder<T> setTooltip(String... tooltip) {
        this.tooltipSupplier = (str) -> {
            return Optional.ofNullable(tooltip);
        };
        return this;
    }

    public DropdownMenuBuilder<T> requireRestart() {
        this.requireRestart(true);
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
        return this.suggestionMode;
    }

    public @NotNull DropdownBoxEntry<T> build() {
        DropdownBoxEntry<T> entry = new DropdownBoxEntry(this.getFieldNameKey(), this.getResetButtonKey(), (Supplier)null, false, this.defaultValue, this.saveConsumer, this.selections, this.topCellElement, this.cellCreator);
        entry.setTooltipSupplier(() -> {
            return (Optional)this.tooltipSupplier.apply(entry.getValue());
        });
        if (this.errorSupplier != null) {
            entry.setErrorSupplier(() -> {
                return (Optional)this.errorSupplier.apply(entry.getValue());
            });
        }

        entry.setSuggestionMode(this.suggestionMode);
        return entry;
    }

    public static class CellCreatorBuilder {
        public CellCreatorBuilder() {
        }

        public static <T> DropdownBoxEntry.SelectionCellCreator<T> of() {
            return new DropdownBoxEntry.DefaultSelectionCellCreator();
        }

        public static <T> DropdownBoxEntry.SelectionCellCreator<T> of(Function<T, String> toStringFunction) {
            return new DropdownBoxEntry.DefaultSelectionCellCreator(toStringFunction);
        }

        public static <T> DropdownBoxEntry.SelectionCellCreator<T> ofWidth(final int cellWidth) {
            return new DropdownBoxEntry.DefaultSelectionCellCreator<T>() {
                public int getCellWidth() {
                    return cellWidth;
                }
            };
        }

        public static <T> DropdownBoxEntry.SelectionCellCreator<T> ofWidth(final int cellWidth, Function<T, String> toStringFunction) {
            return new DropdownBoxEntry.DefaultSelectionCellCreator<T>(toStringFunction) {
                public int getCellWidth() {
                    return cellWidth;
                }
            };
        }

        public static <T> DropdownBoxEntry.SelectionCellCreator<T> ofCellCount(final int maxItems) {
            return new DropdownBoxEntry.DefaultSelectionCellCreator<T>() {
                public int getDropBoxMaxHeight() {
                    return this.getCellHeight() * maxItems;
                }
            };
        }

        public static <T> DropdownBoxEntry.SelectionCellCreator<T> ofCellCount(final int maxItems, Function<T, String> toStringFunction) {
            return new DropdownBoxEntry.DefaultSelectionCellCreator<T>(toStringFunction) {
                public int getDropBoxMaxHeight() {
                    return this.getCellHeight() * maxItems;
                }
            };
        }

        public static <T> DropdownBoxEntry.SelectionCellCreator<T> of(final int cellWidth, final int maxItems) {
            return new DropdownBoxEntry.DefaultSelectionCellCreator<T>() {
                public int getCellWidth() {
                    return cellWidth;
                }

                public int getDropBoxMaxHeight() {
                    return this.getCellHeight() * maxItems;
                }
            };
        }

        public static <T> DropdownBoxEntry.SelectionCellCreator<T> of(final int cellWidth, final int maxItems, Function<T, String> toStringFunction) {
            return new DropdownBoxEntry.DefaultSelectionCellCreator<T>(toStringFunction) {
                public int getCellWidth() {
                    return cellWidth;
                }

                public int getDropBoxMaxHeight() {
                    return this.getCellHeight() * maxItems;
                }
            };
        }

        public static <T> DropdownBoxEntry.SelectionCellCreator<T> of(final int cellHeight, final int cellWidth, final int maxItems) {
            return new DropdownBoxEntry.DefaultSelectionCellCreator<T>() {
                public int getCellHeight() {
                    return cellHeight;
                }

                public int getCellWidth() {
                    return cellWidth;
                }

                public int getDropBoxMaxHeight() {
                    return this.getCellHeight() * maxItems;
                }
            };
        }

        public static <T> DropdownBoxEntry.SelectionCellCreator<T> of(final int cellHeight, final int cellWidth, final int maxItems, Function<T, String> toStringFunction) {
            return new DropdownBoxEntry.DefaultSelectionCellCreator<T>(toStringFunction) {
                public int getCellHeight() {
                    return cellHeight;
                }

                public int getCellWidth() {
                    return cellWidth;
                }

                public int getDropBoxMaxHeight() {
                    return this.getCellHeight() * maxItems;
                }
            };
        }

        public static DropdownBoxEntry.SelectionCellCreator<String> ofItemString() {
            return ofItemString(20, 146, 7);
        }

        public static DropdownBoxEntry.SelectionCellCreator<String> ofItemString(int maxItems) {
            return ofItemString(20, 146, maxItems);
        }

        public static DropdownBoxEntry.SelectionCellCreator<String> ofItemString(final int cellHeight, final int cellWidth, final int maxItems) {
            return new DropdownBoxEntry.DefaultSelectionCellCreator<String>() {
                public DropdownBoxEntry.SelectionCellElement<String> create(String selection) {
                    final ItemStack stack = new ItemStack((Item)Item.APPLE);
                    return new DropdownBoxEntry.DefaultSelectionCellElement<String>(selection, this.toStringFunction) {
                        public void render(int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
                            this.rendering = true;
                            this.x = x;
                            this.y = y;
                            this.width = width;
                            this.height = height;
                            boolean b = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
                            if (b) {
                                this.fill(x + 1, y + 1, x + width - 1, y + height - 1, -15132391);
                            }

                            Minecraft.INSTANCE.textRenderer.drawWithShadow((String)this.toStringFunction.apply((String)this.r), x + 6 + 18, y + 6, b ? 16777215 : 8947848);
                            ItemRenderer itemRenderer = DropdownMenuBuilder.ITEM_RENDERER;
                            itemRenderer.renderGuiItem(Minecraft.INSTANCE.textRenderer, Minecraft.INSTANCE.textureManager, stack.getItem().id, stack.getDamage(), stack.getTextureId(), x + width - 18, y + 2);
                        }
                    };
                }

                public int getCellHeight() {
                    return cellHeight;
                }

                public int getCellWidth() {
                    return cellWidth;
                }

                public int getDropBoxMaxHeight() {
                    return this.getCellHeight() * maxItems;
                }
            };
        }

        public static DropdownBoxEntry.SelectionCellCreator<String> ofBlockString() {
            return ofBlockString(20, 146, 7);
        }

        public static DropdownBoxEntry.SelectionCellCreator<String> ofBlockString(int maxItems) {
            return ofBlockString(20, 146, maxItems);
        }

        public static DropdownBoxEntry.SelectionCellCreator<String> ofBlockString(final int cellHeight, final int cellWidth, final int maxItems) {
            return new DropdownBoxEntry.DefaultSelectionCellCreator<String>() {
                public DropdownBoxEntry.SelectionCellElement<String> create(String selection) {
                    final ItemStack stack = new ItemStack(((Block)null));
                    return new DropdownBoxEntry.DefaultSelectionCellElement<String>(selection, this.toStringFunction) {
                        public void render(int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
                            this.rendering = true;
                            this.x = x;
                            this.y = y;
                            this.width = width;
                            this.height = height;
                            boolean b = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
                            if (b) {
                                this.fill(x + 1, y + 1, x + width - 1, y + height - 1, -15132391);
                            }

                            Minecraft.INSTANCE.textRenderer.drawWithShadow((String)this.toStringFunction.apply((String)this.r), x + 6 + 18, y + 6, b ? 16777215 : 8947848);
                            ItemRenderer itemRenderer = DropdownMenuBuilder.ITEM_RENDERER;
                            itemRenderer.renderGuiItem(Minecraft.INSTANCE.textRenderer, Minecraft.INSTANCE.textureManager, stack.getItem().id, stack.getDamage(), stack.getTextureId(), x + width - 18, y + 2);
                        }
                    };
                }

                public int getCellHeight() {
                    return cellHeight;
                }

                public int getCellWidth() {
                    return cellWidth;
                }

                public int getDropBoxMaxHeight() {
                    return this.getCellHeight() * maxItems;
                }
            };
        }

        public static DropdownBoxEntry.SelectionCellCreator<Item> ofItemObject() {
            return ofItemObject(20, 146, 7);
        }

        public static DropdownBoxEntry.SelectionCellCreator<Item> ofItemObject(int maxItems) {
            return ofItemObject(20, 146, maxItems);
        }

        public static DropdownBoxEntry.SelectionCellCreator<Item> ofItemObject(final int cellHeight, final int cellWidth, final int maxItems) {
            return new DropdownBoxEntry.DefaultSelectionCellCreator<Item>((i) -> {
                return String.valueOf(i);
            }) {
                public DropdownBoxEntry.SelectionCellElement<Item> create(Item selection) {
                    final ItemStack stack = new ItemStack(selection);
                    return new DropdownBoxEntry.DefaultSelectionCellElement<Item>(selection, this.toStringFunction) {
                        public void render(int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
                            this.rendering = true;
                            this.x = x;
                            this.y = y;
                            this.width = width;
                            this.height = height;
                            boolean b = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
                            if (b) {
                                this.fill(x + 1, y + 1, x + width - 1, y + height - 1, -15132391);
                            }

                            Minecraft.INSTANCE.textRenderer.drawWithShadow((String)this.toStringFunction.apply((Item)this.r), x + 6 + 18, y + 6, b ? 16777215 : 8947848);
                            ItemRenderer itemRenderer = DropdownMenuBuilder.ITEM_RENDERER;
                            itemRenderer.renderGuiItem(Minecraft.INSTANCE.textRenderer, Minecraft.INSTANCE.textureManager, stack.getItem().id, stack.getDamage(), stack.getTextureId(), x + width - 18, y + 2);
                        }
                    };
                }

                public int getCellHeight() {
                    return cellHeight;
                }

                public int getCellWidth() {
                    return cellWidth;
                }

                public int getDropBoxMaxHeight() {
                    return this.getCellHeight() * maxItems;
                }
            };
        }

        public static DropdownBoxEntry.SelectionCellCreator<Block> ofBlockObject() {
            return ofBlockObject(20, 146, 7);
        }

        public static DropdownBoxEntry.SelectionCellCreator<Block> ofBlockObject(int maxItems) {
            return ofBlockObject(20, 146, maxItems);
        }

        public static DropdownBoxEntry.SelectionCellCreator<Block> ofBlockObject(final int cellHeight, final int cellWidth, final int maxItems) {
            return new DropdownBoxEntry.DefaultSelectionCellCreator<Block>((i) -> {
                return String.valueOf(i);
            }) {
                public DropdownBoxEntry.SelectionCellElement<Block> create(Block selection) {
                    final ItemStack stack = new ItemStack(selection);
                    return new DropdownBoxEntry.DefaultSelectionCellElement<Block>(selection, this.toStringFunction) {
                        public void render(int mouseX, int mouseY, int x, int y, int width, int height, float delta) {
                            this.rendering = true;
                            this.x = x;
                            this.y = y;
                            this.width = width;
                            this.height = height;
                            boolean b = mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
                            if (b) {
                                this.fill(x + 1, y + 1, x + width - 1, y + height - 1, -15132391);
                            }

                            Minecraft.INSTANCE.textRenderer.drawWithShadow((String)this.toStringFunction.apply((Block)this.r), x + 6 + 18, y + 6, b ? 16777215 : 8947848);
                            ItemRenderer itemRenderer = DropdownMenuBuilder.ITEM_RENDERER;
                            itemRenderer.renderGuiItem(Minecraft.INSTANCE.textRenderer, Minecraft.INSTANCE.textureManager, stack.getItem().id, stack.getDamage(), stack.getTextureId(), x + width - 18, y + 2);
                        }
                    };
                }

                public int getCellHeight() {
                    return cellHeight;
                }

                public int getCellWidth() {
                    return cellWidth;
                }

                public int getDropBoxMaxHeight() {
                    return this.getCellHeight() * maxItems;
                }
            };
        }
    }

    public static class TopCellElementBuilder {
        public static final Function<String, String> String_FUNCTION = (str) -> {
            try {
                return str;
            } catch (NumberFormatException var2) {
                return null;
            }
        };
        public static final Function<String, String> ITEM_String_FUNCTION = (str) -> {
            try {
                String string = str;
                if (false) {
                    return string;
                }
            } catch (Exception var2) {
            }

            return null;
        };
        public static final Function<String, String> BLOCK_String_FUNCTION = (str) -> {
            try {
                String String = str;
                if (false) {
                    return String;
                }
            } catch (Exception var2) {
            }

            return null;
        };
        public static final Function<String, Item> ITEM_FUNCTION = (str) -> {
            try {
                return null;
            } catch (Exception var2) {
                return null;
            }
        };
        public static final Function<String, Block> BLOCK_FUNCTION = (str) -> {
            try {
                return null;
            } catch (Exception var2) {
                return null;
            }
        };
        private static final ItemStack BARRIER;

        public TopCellElementBuilder() {
        }

        public static <T> DropdownBoxEntry.SelectionTopCellElement<T> of(Screen screen, T value, Function<String, T> toObjectFunction) {
            return of(screen, value, toObjectFunction, Object::toString);
        }

        public static <T> DropdownBoxEntry.SelectionTopCellElement<T> of(Screen screen, T value, Function<String, T> toObjectFunction, Function<T, String> toStringFunction) {
            return new DropdownBoxEntry.DefaultSelectionTopCellElement(screen, value, toObjectFunction, toStringFunction);
        }

        static {
            BARRIER = new ItemStack(Block.BEDROCK);
        }
    }
}
