package net.fabricmc.fabric.mixin.command.client;

import org.objectweb.asm.*;

import java.util.Arrays;

public class ClientPacketListenerMixin extends ClassVisitor implements Opcodes {

    public ClientPacketListenerMixin(ClassVisitor cv) {
        super(ASM9, cv);
    }

    public static byte[] transform(byte[] classfileBuffer) {
        ClassReader reader = new ClassReader(classfileBuffer);
        ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES);
        ClientPacketListenerMixin transformer = new ClientPacketListenerMixin(writer);
        reader.accept(transformer, 0);
        return writer.toByteArray();
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        String[] newInterfaces = Arrays.copyOf(interfaces, interfaces.length + 1);
        newInterfaces[newInterfaces.length - 1] = "net/fabricmc/fabric/impl/command/client/ClientCommandInternals$LastReceivedCommandsPacketAccessor";
        super.visit(version, access, name, signature, superName, newInterfaces);
    }

    @Override
    public void visitEnd() {
        super.visitField(ACC_PRIVATE, "lastReceivedCommandsPacket", "Lnet/minecraft/network/protocol/game/ClientboundCommandsPacket;", null, null).visitEnd();
        MethodVisitor mv = super.visitMethod(ACC_PUBLIC, "fabric_api$getLastReceivedCommandsPacket", "()Lnet/minecraft/network/protocol/game/ClientboundCommandsPacket;", null, null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitFieldInsn(GETFIELD, "net/minecraft/client/multiplayer/ClientPacketListener", "lastReceivedCommandsPacket", "Lnet/minecraft/network/protocol/game/ClientboundCommandsPacket;");
        mv.visitInsn(ARETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();

        super.visitEnd();
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        if (name.equals("<init>")) {
            return new MethodVisitor(ASM9, mv) {
                @Override
                public void visitInsn(int opcode) {
                    if (opcode == RETURN) {
                        mv.visitVarInsn(ALOAD, 0);
                        mv.visitFieldInsn(GETFIELD, "net/minecraft/client/multiplayer/ClientPacketListener", "suggestionsProvider", "Lnet/minecraft/client/multiplayer/ClientSuggestionProvider;");
                        mv.visitTypeInsn(CHECKCAST, "net/fabricmc/fabric/impl/command/client/ClientSuggestionProviderExtensions");
                        mv.visitMethodInsn(INVOKEINTERFACE, "net/fabricmc/fabric/impl/command/client/ClientSuggestionProviderExtensions", "fabric_markAttended", "()V", true);
                    }
                    super.visitInsn(opcode);
                }
            };
        }

        if (name.equals("handleLogin") && descriptor.equals("(Lnet/minecraft/network/protocol/game/ClientboundLoginPacket;)V")) {
            return new MethodVisitor(ASM9, mv) {
                @Override
                public void visitInsn(int opcode) {
                    if (opcode == RETURN) {
                        mv.visitTypeInsn(NEW, "com/mojang/brigadier/CommandDispatcher");
                        mv.visitInsn(DUP);
                        mv.visitMethodInsn(INVOKESPECIAL, "com/mojang/brigadier/CommandDispatcher", "<init>", "()V", false);
                        mv.visitVarInsn(ASTORE, 2);

                        mv.visitVarInsn(ALOAD, 2);
                        mv.visitMethodInsn(INVOKESTATIC, "net/fabricmc/fabric/impl/command/client/ClientCommandInternals", "setActiveDispatcher", "(Lcom/mojang/brigadier/CommandDispatcher;)V", false);

                        mv.visitFieldInsn(GETSTATIC, "net/fabricmc/fabric/api/client/command/v2/ClientCommandRegistrationCallback", "EVENT", "Lnet/fabricmc/fabric/api/event/Event;");
                        mv.visitMethodInsn(INVOKEVIRTUAL, "net/fabricmc/fabric/api/event/Event", "invoker", "()Ljava/lang/Object;", false);
                        mv.visitTypeInsn(CHECKCAST, "net/fabricmc/fabric/api/client/command/v2/ClientCommandRegistrationCallback");
                        mv.visitVarInsn(ALOAD, 2);

                        mv.visitVarInsn(ALOAD, 0);
                        mv.visitFieldInsn(GETFIELD, "net/minecraft/client/multiplayer/ClientPacketListener", "registryAccess", "Lnet/minecraft/core/RegistryAccess$Frozen;");
                        mv.visitVarInsn(ALOAD, 0);
                        mv.visitFieldInsn(GETFIELD, "net/minecraft/client/multiplayer/ClientPacketListener", "enabledFeatures", "Lnet/minecraft/world/flag/FeatureFlagSet;");
                        mv.visitMethodInsn(
                                INVOKESTATIC,
                                "net/minecraft/commands/CommandBuildContext",
                                "simple",
                                "(Lnet/minecraft/core/HolderLookup$Provider;Lnet/minecraft/world/flag/FeatureFlagSet;)Lnet/minecraft/commands/CommandBuildContext;",
                                true
                        );
                        mv.visitMethodInsn(INVOKEINTERFACE, "net/fabricmc/fabric/api/client/command/v2/ClientCommandRegistrationCallback", "register", "(Lcom/mojang/brigadier/CommandDispatcher;Lnet/minecraft/commands/CommandBuildContext;)V", true);

                        mv.visitMethodInsn(INVOKESTATIC, "net/fabricmc/fabric/impl/command/client/ClientCommandInternals", "finalizeInit", "()V", false);
                    }
                    super.visitInsn(opcode);
                }
            };
        }

        if (name.equals("handleCommands") && descriptor.equals("(Lnet/minecraft/network/protocol/game/ClientboundCommandsPacket;)V")) {
            return new MethodVisitor(ASM9, mv) {
                boolean injectedPacketSet = false;

                @Override
                public void visitMethodInsn(int opcode, String owner, String methodName, String descriptorStr, boolean isInterface) {
                    super.visitMethodInsn(opcode, owner, methodName, descriptorStr, isInterface);
                    if (!injectedPacketSet && methodName.equals("ensureRunningOnSameThread")) {
                        mv.visitVarInsn(ALOAD, 0);
                        mv.visitVarInsn(ALOAD, 1);
                        mv.visitFieldInsn(PUTFIELD, "net/minecraft/client/multiplayer/ClientPacketListener", "lastReceivedCommandsPacket", "Lnet/minecraft/network/protocol/game/ClientboundCommandsPacket;");
                        injectedPacketSet = true;
                    }
                }

                @Override
                public void visitInsn(int opcode) {
                    if (opcode == RETURN) {
                        mv.visitVarInsn(ALOAD, 0);
                        mv.visitFieldInsn(GETFIELD, "net/minecraft/client/multiplayer/ClientPacketListener", "commands", "Lcom/mojang/brigadier/CommandDispatcher;");
                        mv.visitVarInsn(ALOAD, 0);
                        mv.visitFieldInsn(GETFIELD, "net/minecraft/client/multiplayer/ClientPacketListener", "suggestionsProvider", "Lnet/minecraft/client/multiplayer/ClientSuggestionProvider;");
                        mv.visitTypeInsn(CHECKCAST, "net/fabricmc/fabric/api/client/command/v2/FabricClientCommandSource");
                        mv.visitMethodInsn(INVOKESTATIC, "net/fabricmc/fabric/impl/command/client/ClientCommandInternals", "addCommands", "(Lcom/mojang/brigadier/CommandDispatcher;Lnet/fabricmc/fabric/api/client/command/v2/FabricClientCommandSource;)V", false);
                    }
                    super.visitInsn(opcode);
                }
            };
        }

        if (name.equals("sendUnattendedCommand") && descriptor.equals("(Ljava/lang/String;Lnet/minecraft/client/gui/screens/Screen;)V")) {
            return new MethodVisitor(ASM9, mv) {
                @Override
                public void visitCode() {
                    super.visitCode();
                    mv.visitVarInsn(ALOAD, 1);
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitFieldInsn(GETFIELD, "net/minecraft/client/multiplayer/ClientPacketListener", "suggestionsProvider", "Lnet/minecraft/client/multiplayer/ClientSuggestionProvider;");
                    mv.visitTypeInsn(CHECKCAST, "net/fabricmc/fabric/api/client/command/v2/FabricClientCommandSource");
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitFieldInsn(GETFIELD, "net/minecraft/client/multiplayer/ClientPacketListener", "restrictedSuggestionsProvider", "Lnet/minecraft/client/multiplayer/ClientSuggestionProvider;");
                    mv.visitTypeInsn(CHECKCAST, "net/fabricmc/fabric/api/client/command/v2/FabricClientCommandSource");
                    mv.visitMethodInsn(INVOKESTATIC, "net/fabricmc/fabric/impl/command/client/ClientCommandInternals", "executeCommand", "(Ljava/lang/String;Lnet/fabricmc/fabric/api/client/command/v2/FabricClientCommandSource;Lnet/fabricmc/fabric/api/client/command/v2/FabricClientCommandSource;)Z", false);

                    Label label = new Label();
                    mv.visitJumpInsn(IFEQ, label);
                    mv.visitInsn(RETURN);
                    mv.visitLabel(label);
                }
            };
        }

        if (name.equals("sendCommand") && descriptor.equals("(Ljava/lang/String;)V")) {
            return new MethodVisitor(ASM9, mv) {
                @Override
                public void visitCode() {
                    super.visitCode();
                    mv.visitVarInsn(ALOAD, 1);
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitFieldInsn(GETFIELD, "net/minecraft/client/multiplayer/ClientPacketListener", "suggestionsProvider", "Lnet/minecraft/client/multiplayer/ClientSuggestionProvider;");
                    mv.visitTypeInsn(CHECKCAST, "net/fabricmc/fabric/api/client/command/v2/FabricClientCommandSource");
                    mv.visitInsn(ACONST_NULL);
                    mv.visitMethodInsn(INVOKESTATIC, "net/fabricmc/fabric/impl/command/client/ClientCommandInternals", "executeCommand", "(Ljava/lang/String;Lnet/fabricmc/fabric/api/client/command/v2/FabricClientCommandSource;Lnet/fabricmc/fabric/api/client/command/v2/FabricClientCommandSource;)Z", false);

                    Label label = new Label();
                    mv.visitJumpInsn(IFEQ, label);
                    mv.visitInsn(RETURN);
                    mv.visitLabel(label);
                }
            };
        }

        return mv;
    }
}