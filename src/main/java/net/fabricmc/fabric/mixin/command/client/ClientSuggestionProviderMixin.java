package net.fabricmc.fabric.mixin.command.client;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.Arrays;

public class ClientSuggestionProviderMixin extends ClassVisitor implements Opcodes {

    public ClientSuggestionProviderMixin(ClassVisitor cv) {
        super(ASM9, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        String[] newInterfaces = Arrays.copyOf(interfaces, interfaces.length + 2);
        newInterfaces[newInterfaces.length - 2] = "net/fabricmc/fabric/api/client/command/v2/FabricClientCommandSource";
        newInterfaces[newInterfaces.length - 1] = "net/fabricmc/fabric/impl/command/client/ClientSuggestionProviderExtensions";
        super.visit(version, access, name, signature, superName, newInterfaces);
    }

    @Override
    public void visitEnd() {
        super.visitField(ACC_PRIVATE, "attended", "Z", null, Boolean.FALSE).visitEnd();

        {
            MethodVisitor mv = super.visitMethod(ACC_PUBLIC, "sendFeedback", "(Lnet/minecraft/network/chat/Component;)V", null, null);
            mv.visitCode();

            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/multiplayer/ClientSuggestionProvider", "minecraft", "Lnet/minecraft/client/Minecraft;");
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/Minecraft", "gui", "Lnet/minecraft/client/gui/Gui;");
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/gui/Gui", "hud", "Lnet/minecraft/client/gui/Hud;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/gui/Hud", "getChat", "()Lnet/minecraft/client/gui/components/ChatComponent;", false);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/gui/components/ChatComponent", "addClientSystemMessage", "(Lnet/minecraft/network/chat/Component;)V", false);

            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/multiplayer/ClientSuggestionProvider", "minecraft", "Lnet/minecraft/client/Minecraft;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/Minecraft", "getNarrator", "()Lnet/minecraft/client/GameNarrator;", false);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/GameNarrator", "saySystemChatQueued", "(Lnet/minecraft/network/chat/Component;)V", false);

            mv.visitInsn(RETURN);
            mv.visitMaxs(3, 2);
            mv.visitEnd();
        }

        {
            MethodVisitor mv = super.visitMethod(ACC_PUBLIC, "sendError", "(Lnet/minecraft/network/chat/Component;)V", null, null);
            mv.visitCode();

            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESTATIC, "net/minecraft/network/chat/Component", "empty", "()Lnet/minecraft/network/chat/MutableComponent;", true);
            mv.visitVarInsn(ALOAD, 1);
            mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/network/chat/MutableComponent", "append", "(Lnet/minecraft/network/chat/Component;)Lnet/minecraft/network/chat/MutableComponent;", false);
            mv.visitFieldInsn(GETSTATIC, "net/minecraft/ChatFormatting", "RED", "Lnet/minecraft/ChatFormatting;");
            mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/network/chat/MutableComponent", "withStyle", "(Lnet/minecraft/ChatFormatting;)Lnet/minecraft/network/chat/MutableComponent;", false);
            mv.visitMethodInsn(INVOKEVIRTUAL, "net/minecraft/client/multiplayer/ClientSuggestionProvider", "sendFeedback", "(Lnet/minecraft/network/chat/Component;)V", false);

            mv.visitInsn(RETURN);
            mv.visitMaxs(2, 2);
            mv.visitEnd();
        }

        {
            MethodVisitor mv = super.visitMethod(ACC_PUBLIC, "getClient", "()Lnet/minecraft/client/Minecraft;", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/multiplayer/ClientSuggestionProvider", "minecraft", "Lnet/minecraft/client/Minecraft;");
            mv.visitInsn(ARETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }

        {
            MethodVisitor mv = super.visitMethod(ACC_PUBLIC, "getPlayer", "()Lnet/minecraft/client/player/LocalPlayer;", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/multiplayer/ClientSuggestionProvider", "minecraft", "Lnet/minecraft/client/Minecraft;");
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/Minecraft", "player", "Lnet/minecraft/client/player/LocalPlayer;");
            mv.visitInsn(ARETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }

        {
            MethodVisitor mv = super.visitMethod(ACC_PUBLIC, "getLevel", "()Lnet/minecraft/client/multiplayer/ClientLevel;", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/multiplayer/ClientSuggestionProvider", "minecraft", "Lnet/minecraft/client/Minecraft;");
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/Minecraft", "level", "Lnet/minecraft/client/multiplayer/ClientLevel;");
            mv.visitInsn(ARETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }

        {
            MethodVisitor mv = super.visitMethod(ACC_PUBLIC, "attended", "()Z", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitFieldInsn(GETFIELD, "net/minecraft/client/multiplayer/ClientSuggestionProvider", "attended", "Z");
            mv.visitInsn(IRETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }

        {
            MethodVisitor mv = super.visitMethod(ACC_PUBLIC, "fabric_markAttended", "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitInsn(ICONST_1);
            mv.visitFieldInsn(PUTFIELD, "net/minecraft/client/multiplayer/ClientSuggestionProvider", "attended", "Z");
            mv.visitInsn(RETURN);
            mv.visitMaxs(2, 1);
            mv.visitEnd();
        }

        super.visitEnd();
    }
}