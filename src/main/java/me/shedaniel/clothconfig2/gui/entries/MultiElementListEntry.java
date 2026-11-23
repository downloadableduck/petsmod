//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package me.shedaniel.clothconfig2.gui.entries;
import net.minecraft.client.font.TextRenderer;

import com.google.common.collect.Lists;
import com.jeff.pets.compat.GlStateManager;
import me.shedaniel.clothconfig2.api.AbstractConfigEntry;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import me.shedaniel.clothconfig2.compat.GuiEventListener;
import me.shedaniel.math.Rectangle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resource.language.I18n;
import org.jetbrains.annotations.ApiStatus.Internal;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class MultiElementListEntry<T> extends TooltipListEntry<T> {
    private static final String CONFIG_TEX = "textures/gui/cloth_config.png";
    private final T object;
    private String categoryName;
    private List<AbstractConfigListEntry<?>> entries;
    private CategoryLabelWidget widget;
    private List<GuiEventListener> children;
    private boolean expanded;

    /** @deprecated */
    @Deprecated
    @Internal
    public MultiElementListEntry(String categoryName, T object, List<AbstractConfigListEntry<?>> entries, boolean defaultExpanded, boolean requiresRestart) {
        super(categoryName, (Supplier)null, requiresRestart);
        this.categoryName = categoryName;
        this.object = object;
        this.entries = entries;
        this.expanded = defaultExpanded;
        this.widget = new CategoryLabelWidget();
        this.children = Lists.newArrayList(new GuiEventListener[]{this.widget});
        this.children.addAll(entries);
    }

    public boolean isRequiresRestart() {
        Iterator var1 = this.entries.iterator();

        AbstractConfigListEntry entry;
        do {
            if (!var1.hasNext()) {
                return false;
            }

            entry = (AbstractConfigListEntry)var1.next();
        } while(!entry.isRequiresRestart());

        return true;
    }

    public void setRequiresRestart(boolean requiresRestart) {
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public T getValue() {
        return this.object;
    }

    public Optional<T> getDefaultValue() {
        return Optional.empty();
    }

    public void render(int index, int y, int x, int entryWidth, int entryHeight, int mouseX, int mouseY, boolean isSelected, float delta) {
        super.render(index, y, x, entryWidth, entryHeight, mouseX, mouseY, isSelected, delta);
        this.widget.rectangle.x = x - 19;
        this.widget.rectangle.y = y;
        this.widget.rectangle.width = entryWidth + 19;
        this.widget.rectangle.height = 24;
        Minecraft.getMinecraft().textureManager.bindTexture(Minecraft.getMinecraft().textureManager.loadTexture("textures/gui/cloth_config.png"));
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.drawTexture(x - 15, y + 4, 24, (this.widget.rectangle.contains(mouseX, mouseY) ? 18 : 0) + (this.expanded ? 9 : 0), 9, 9);
        TextRenderer.of(Minecraft.getMinecraft().font).drawWithShadow(I18n.getTranslation(this.categoryName), x, y + 5, this.widget.rectangle.contains(mouseX, mouseY) ? -1638890 : -1);
        Iterator var10 = this.entries.iterator();

        while(var10.hasNext()) {
            AbstractConfigListEntry<?> entry = (AbstractConfigListEntry)var10.next();
            entry.setParent(this.getParent());
            entry.setScreen(this.getScreen());
        }

        if (this.expanded) {
            int yy = y + 24;

            AbstractConfigListEntry entry;
            for(Iterator var14 = this.entries.iterator(); var14.hasNext(); yy += Math.max(0, entry.getMorePossibleHeight())) {
                entry = (AbstractConfigListEntry)var14.next();
                entry.render(-1, yy, x + 14, entryWidth - 14, entry.getItemHeight(), mouseX, mouseY, isSelected, delta);
                yy += entry.getItemHeight();
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
        if (!this.expanded) {
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

    public void updateSelected(boolean isSelected) {
        Iterator var2 = this.entries.iterator();

        while(var2.hasNext()) {
            AbstractConfigListEntry<?> entry = (AbstractConfigListEntry)var2.next();
            entry.updateSelected(this.expanded && isSelected && this.getFocused() == entry);
        }

    }

    public void lateRender(int mouseX, int mouseY, float delta) {
        if (this.expanded) {
            Iterator var4 = this.entries.iterator();

            while(var4.hasNext()) {
                AbstractConfigListEntry<?> entry = (AbstractConfigListEntry)var4.next();
                entry.lateRender(mouseX, mouseY, delta);
            }
        }

    }

    public int getMorePossibleHeight() {
        if (!this.expanded) {
            return -1;
        } else {
            List<Integer> list = new ArrayList();
            int i = 24;
            Iterator var3 = this.entries.iterator();

            while(var3.hasNext()) {
                AbstractConfigListEntry<?> entry = (AbstractConfigListEntry)var3.next();
                i += entry.getItemHeight();
                if (entry.getMorePossibleHeight() >= 0) {
                    list.add(i + entry.getMorePossibleHeight());
                }
            }

            list.add(i);
            return (Integer)list.stream().max(Integer::compare).orElse(0) - this.getItemHeight();
        }
    }

    public List<? extends GuiEventListener> children() {
        return this.expanded ? this.children : Collections.singletonList(this.widget);
    }

    public void save() {
        this.entries.forEach(AbstractConfigEntry::save);
    }

    public Optional<String> getError() {
        List<String> errors = (List)this.entries.stream().map(AbstractConfigEntry::getConfigError).filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());
        return errors.size() > 1 ? Optional.of(I18n.getTranslation("text.cloth-config.multi_error")) : errors.stream().findFirst();
    }

    public class CategoryLabelWidget implements GuiEventListener {
        private Rectangle rectangle = new Rectangle();

        public CategoryLabelWidget() {
        }

        public boolean mouseClicked(double double_1, double double_2, int int_1) {
            if (this.rectangle.contains(double_1, double_2)) {
                MultiElementListEntry.this.expanded = !MultiElementListEntry.this.expanded;
                Minecraft.getMinecraft().sndManager.playSound("gui.button.press", net.minecraft.core.sound.SoundCategory.GUI_SOUNDS, 1.0F, 1.0F);
                return true;
            } else {
                return false;
            }
        }
    }
}
