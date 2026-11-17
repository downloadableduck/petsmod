//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.api;

public abstract class AbstractConfigListEntry<T> extends AbstractConfigEntry<T> {
    private String fieldName;
    private boolean editable = true;
    public boolean requiresRestart;

    public AbstractConfigListEntry(String fieldName, boolean requiresRestart) {
        this.fieldName = fieldName;
        this.requiresRestart = requiresRestart;
    }

    public boolean isRequiresRestart() {
        return this.requiresRestart;
    }

    public void setRequiresRestart(boolean requiresRestart) {
        this.requiresRestart = requiresRestart;
    }

    public boolean isEditable() {
        return this.getScreen().isEditable() && this.editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public final int getPreferredTextColor() {
        return this.getConfigError().isPresent() ? 16733525 : 16777215;
    }

    public String getFieldName() {
        return this.fieldName;
    }
}
