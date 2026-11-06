package com.jeff.pets.client.mixin;

import com.jeff.pets.client.screen.buttons.SearchBox;
import net.minecraft.client.gui.components.EditBox;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(EditBox.class)
public abstract class EditBoxMixin {

    @Shadow
    public abstract String getValue();

    @Shadow
    public abstract void setValue(String value);

    @Redirect(at = @At(value = "INVOKE", target = "Ljava/lang/StringBuilder;toString()Ljava/lang/String;"), method = "insertText")
    private String pets$onInsertText(StringBuilder instance) {
        EditBox box = (EditBox) (Object) this;
        if (box instanceof SearchBox) {
            return this.getValue();
        }
        return instance.toString();
    }

    @ModifyArgs(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;III)V"), method = "extractWidgetRenderState")
    private void pets$modifyText(Args args) {
        EditBox box = (EditBox) (Object) this;
        if (box instanceof SearchBox searchBox) {
            args.set(4, searchBox.color);
        }
    }
}
