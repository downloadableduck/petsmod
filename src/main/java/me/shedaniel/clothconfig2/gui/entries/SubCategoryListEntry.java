//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.gui.entries;

import com.google.common.collect.Lists;
import com.jeff.pets.compat.GlStateManager;
import me.shedaniel.clothconfig2.api.AbstractConfigEntry;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.compat.GuiEventListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resource.language.I18n;

import java.awt.*;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class SubCategoryListEntry extends TooltipListEntry {
    private static final String CONFIG_TEX = "textures/gui/cloth_config.png";
    private String categoryName;
    private List<AbstractConfigListEntry> entries;
    private CategoryLabelWidget widget;
    private List<GuiEventListener> children;
    private boolean expended;

    public SubCategoryListEntry(String categoryName, List<AbstractConfigListEntry> entries, boolean defaultExpended, boolean requiresRestart) {
        super(categoryName, (Supplier)null, requiresRestart);
        this.categoryName = categoryName;
        this.entries = entries;
        this.expended = defaultExpended;
        this.widget = new CategoryLabelWidget();
        this.children = Lists.newArrayList(new GuiEventListener[]{this.widget});
        this.children.addAll(entries);
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public List<AbstractConfigListEntry> getEntries() {
        return this.entries;
    }

    public Object getValue() {
        return this.entries;
    }

    public Optional<Object> getDefaultValue() {
        return Optional.empty();
    }

    public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
        super.render(index, y, x, entryWidth, entryHeight, mouseX, mouseY, isSelected, delta);
        this.widget.rectangle.x = x - 19;
        this.widget.rectangle.y = y;
        this.widget.rectangle.width = entryWidth + 19;
        this.widget.rectangle.height = 24;
        Minecraft.INSTANCE.textureManager.getTextureId("textures/gui/cloth_config.png");
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.drawTexture(x - 15, y + 4, 24, this.expended ? 9 : 0, 9, 9);
        Minecraft.INSTANCE.textRenderer.drawWithShadow(I18n.getTranslation(this.categoryName), x, y + 5, -1);
        Iterator var10 = this.entries.iterator();

        while(var10.hasNext()) {
            AbstractConfigListEntry entry = (AbstractConfigListEntry)var10.next();
            entry.setParent(this.getParent());
            entry.setScreen(this.getScreen());
        }

        if (this.expended) {
            int yy = y + 24;

            AbstractConfigListEntry entry;
            for(Iterator var14 = this.entries.iterator(); var14.hasNext(); yy += entry.getItemHeight()) {
                entry = (AbstractConfigListEntry)var14.next();
                entry.render(-1, yy, x + 14, entryWidth - 14, entry.getItemHeight(), mouseX, mouseY, isSelected, delta);
            }
        }

    }

    public boolean isMouseInside(int mouseX, int mouseY, int x, int y, int entryWidth, int entryHeight) {
        this.widget.rectangle.x = x - 15;
        this.widget.rectangle.y = y;
        this.widget.rectangle.width = entryWidth + 15;
        this.widget.rectangle.height = 24;
        return this.widget.rectangle.contains(mouseX, mouseY) && this.getParent().isMouseOver((double)mouseX, (double)mouseY);
    }

    public int getItemHeight() {
        if (!this.expended) {
            return 24;
        } else {
            int i = 24;

            AbstractConfigListEntry entry;
            for(Iterator var2 = this.entries.iterator(); var2.hasNext(); i += entry.getItemHeight()) {
                entry = (AbstractConfigListEntry)var2.next();
            }

            return i;
        }
    }

    public List<? extends GuiEventListener> children() {
        return this.children;
    }

    public void save() {
        this.entries.forEach(AbstractConfigEntry::save);
    }

    public Optional<String> getError() {
        String error = null;
        Iterator var2 = this.entries.iterator();

        AbstractConfigListEntry entry;
        do {
            if (!var2.hasNext()) {
                return Optional.ofNullable(error);
            }

            entry = (AbstractConfigListEntry)var2.next();
        } while(!entry.getError().isPresent());

        if (error != null) {
            return Optional.ofNullable(I18n.getTranslation("text.cloth-config.multi_error"));
        } else {
            return entry.getError();
        }
    }

    public class CategoryLabelWidget implements GuiEventListener {
        private Rectangle rectangle = new Rectangle();

        public CategoryLabelWidget() {
        }

        public boolean mouseClicked(double double_1, double double_2, int int_1) {
            if (this.rectangle.contains(double_1, double_2)) {
                SubCategoryListEntry.this.expended = !SubCategoryListEntry.this.expended;
                Minecraft.INSTANCE.soundManager.playSound("gui.button.press", 1.0F, 1.0F);
                return true;
            } else {
                return false;
            }
        }
    }
}
