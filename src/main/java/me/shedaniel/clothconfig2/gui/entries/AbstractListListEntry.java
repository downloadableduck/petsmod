//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.gui.entries;

import org.jetbrains.annotations.ApiStatus.Internal;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class AbstractListListEntry<T, C extends AbstractListListEntry.AbstractListCell<T, C, SELF>, SELF extends AbstractListListEntry<T, C, SELF>> extends BaseListEntry<T, C, SELF> {
    protected final BiFunction<T, SELF, C> createNewCell;
    protected Function<T, Optional<String>> cellErrorSupplier;

    @Internal
    public AbstractListListEntry(String fieldName, List<T> value, boolean defaultExpanded, Supplier<Optional<String[]>> tooltipSupplier, Consumer<List<T>> saveConsumer, Supplier<List<T>> defaultValue, String resetButtonKey, boolean requiresRestart, boolean deleteButtonEnabled, boolean insertInFront, BiFunction<T, SELF, C> createNewCell) {
        super(fieldName, tooltipSupplier, defaultValue, (abstractListListEntry) -> {
            return (C)(Object)createNewCell.apply(null, (SELF)abstractListListEntry);
        }, saveConsumer, resetButtonKey, requiresRestart, deleteButtonEnabled, insertInFront);
        this.createNewCell = createNewCell;
        Iterator<T> var12 = value.iterator();

        while(var12.hasNext()) {
            T f = var12.next();
            this.cells.add((C)(Object)createNewCell.apply(f, (SELF)this.self()));
        }

        this.widgets.addAll(this.cells);
        this.expanded = defaultExpanded;
    }

    public Function<T, Optional<String>> getCellErrorSupplier() {
        return this.cellErrorSupplier;
    }

    public void setCellErrorSupplier(Function<T, Optional<String>> cellErrorSupplier) {
        this.cellErrorSupplier = cellErrorSupplier;
    }

    public List<T> getValue() {
        return (List)this.cells.stream().map(AbstractListCell::getValue).collect(Collectors.toList());
    }

    protected C getFromValue(T value) {
        return (C)(Object)this.createNewCell.apply(value, (SELF)this.self());
    }

    @Internal
    public abstract static class AbstractListCell<T, SELF extends AbstractListCell<T, SELF, OUTER_SELF>, OUTER_SELF extends AbstractListListEntry<T, SELF, OUTER_SELF>> extends BaseListCell {
        protected final OUTER_SELF listListEntry;

        public AbstractListCell(@Nullable T value, OUTER_SELF listListEntry) {
            this.listListEntry = listListEntry;
            this.setErrorSupplier(() -> {
                return Optional.ofNullable(listListEntry.cellErrorSupplier).flatMap((cellErrorFn) -> {
                    return (Optional)cellErrorFn.apply(this.getValue());
                });
            });
        }

        public abstract T getValue();
    }
}
