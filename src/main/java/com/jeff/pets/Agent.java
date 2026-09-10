package com.jeff.pets;

import com.jeff.pets.client.Central;
import com.jeff.pets.client.mixin.client.*;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.FilePackResources;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackSelectionConfig;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.KnownPack;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.security.ProtectionDomain;
import java.util.Optional;

public class Agent {

    public static void premain(String agentArgs, Instrumentation inst) {

        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                if ("net/minecraft/world/entity/ai/attributes/DefaultAttributes".equals(className)) {
                    return AttributeSupplierMixin.transform(classfileBuffer);
                }
                if ("net/minecraft/client/multiplayer/ClientPacketListener".equals(className)) {
                    return ClientPlayJoinHandlerMixin.transform(classfileBuffer);
                }
                if ("net/minecraft/client/renderer/entity/state/WitherRenderState".equals(className)) {
                    return WitherRenderStateAccessor.transform(classfileBuffer);
                }
                if ("net/minecraft/client/Minecraft".equals(className)) {
                    byte[] byt =  ClientTickMixin.transform(classfileBuffer);
                    checkforNullObjects();
                    return byt;
                }
                if ("net/minecraft/commands/Commands".equals(className)) {
                    return CommandManagerMixin.transform(classfileBuffer);
                }
                if ("net/minecraft/world/entity/EntityType".equals(className)) {
                    return ASMBootstrap.transform(classfileBuffer);
                }
                if ("net/minecraft/client/renderer/entity/EntityRenderers".equals(className)) {
                    return EntityRenderersTransformer.transform(classfileBuffer);
                }
                if ("net/minecraft/client/model/geom/ModelLayers".equals(className)) {
                    return ModelLayersTransformer.transform(classfileBuffer);
                }
                if ("net/minecraft/client/model/geom/LayerDefinitions".equals(className)) {
                    return LayerDefinitionsTransformer.transform(classfileBuffer);
                }
                if ("net/minecraft/client/renderer/entity/EntityRenderDispatcher".equals(className)) {
                    return EntityRenderDispatcherTransformer.transform(classfileBuffer);
                } if ("net/minecraft/client/gui/components/EditBox".equals(className)) {
                    ClassReader reader = new ClassReader(classfileBuffer);
                    ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES);
                    ButtonClassVisitor transformer = new ButtonClassVisitor(Opcodes.ASM9, writer);
                    reader.accept(transformer, 0);
                    return writer.toByteArray();
                } if ("net/minecraft/client/renderer/entity/state/EntityRenderState".equals(className)) {
                    ClassReader reader = new ClassReader(classfileBuffer);
                    ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_FRAMES);
                    EntityRenderStateClassVisitor visitor = new EntityRenderStateClassVisitor(Opcodes.ASM9, writer);
                    reader.accept(visitor, 0);
                    return writer.toByteArray();
                }
                return ClassFileTransformer.super.transform(loader, className, classBeingRedefined, protectionDomain, classfileBuffer);
            }
        }, true);

        try {
            for (Class<?> clazz : inst.getAllLoadedClasses()) {
                if (clazz.getName().equals("net.minecraft.client.renderer.entity.EntityRenderDispatcher")
                        || clazz.getName().equals("net.minecraft.client.model.geom.LayerDefinitions")) {
                    inst.retransformClasses(clazz);
                }
            }
        } catch (Throwable t) {
        }
    }

    public static void checkforNullObjects() {
        Thread thread = new Thread(() -> {
            Class<?> minecraftClass = null;
            while (minecraftClass == null) {
                try {
                    minecraftClass = Class.forName("net.minecraft.client.Minecraft");
                } catch (ClassNotFoundException ignored) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ignored2) {}
                }
            }
            try {
                Method getInstanceMethod = minecraftClass.getMethod("getInstance");
                Method executeMethod = minecraftClass.getMethod("execute", Runnable.class);

                Object mcInstance = null;
                while (mcInstance == null) {
                    mcInstance = getInstanceMethod.invoke(null);
                    if (mcInstance == null) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ignored) {}
                    }
                }

                executeMethod.invoke(mcInstance, (Runnable) () -> {
                    try {
                        Central.checkForNullObjects();
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                });

            } catch (Throwable t) {
                t.printStackTrace();
            }
        }, "agent");

        thread.setDaemon(true);
        thread.start();
    }
}