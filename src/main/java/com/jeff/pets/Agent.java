package com.jeff.pets;

import com.jeff.pets.client.Central;
import com.jeff.pets.client.PetsClientInitializer;
import com.jeff.pets.client.mixin.client.*;
import com.jeff.pets.client.rendering.custom.first.duck.DuckRenderer;
import com.jeff.pets.client.rendering.custom.first.penguin.PenguinModel;
import com.jeff.pets.mob.custom.first.Duck;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MapRenderer;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.resources.MapTextureManager;
import net.minecraft.client.resources.model.EquipmentAssetManager;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.DefaultAttributes;

import java.io.File;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

import static com.jeff.pets.PetsInitializer.ALLAY;
import static com.mojang.text2speech.Narrator.LOGGER;

public class Agent {

    public static void premain(String agentArgs, Instrumentation inst) {
        Agent.injectRenderersLive();
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
                    return ClientTickMixin.transform(classfileBuffer);
                }
                if ("net/minecraft/commands/Commands".equals(className)) {
                    return CommandManagerMixin.transform(classfileBuffer);
                } if ("net/minecraft/world/entity/EntityType".equals(className)) {
                    return ASMBootstrap.transform(classfileBuffer);
                } if ("net/minecraft/client/renderer/entity/EntityRenderers".equals(className)) {
                    return EntityRenderersTransformer.transform(classfileBuffer);
                } if ("net/minecraft/client/model/geom/ModelLayers".equals(className)) {
                    return ModelLayersTransformer.transform(classfileBuffer);
                }
                if ("net/minecraft/client/model/geom/LayerDefinitions".equals(className)) {
                    System.out.println("[Agent] FOUND LayerDefinitions! Transforming...");
                    return LayerDefinitionsTransformer.transform(classfileBuffer);
                }
                if ("net/minecraft/client/renderer/entity/EntityRenderDispatcher".equals(className)) {
                    byte[] buffer = EntityRenderDispatcherTransformer.transform(classfileBuffer);
                    buffer = ShouldRenderTransformer.transform(buffer);
                    return ExtractEntityTransformer.transform(buffer);
                }
                return ClassFileTransformer.super.transform(loader, className, classBeingRedefined, protectionDomain, classfileBuffer);
            }
        }, true);

        try {
            for (Class<?> clazz : inst.getAllLoadedClasses()) {
                if (clazz.getName().equals("net.minecraft.client.renderer.entity.EntityRenderDispatcher")
                        || clazz.getName().equals("net.minecraft.client.model.geom.LayerDefinitions")) {
                    LOGGER.info("[Agent] Triggering retransform for: " + clazz.getName());
                    inst.retransformClasses(clazz);
                }
            }
        } catch (Throwable t) {
            LOGGER.error("[Agent] Failed to retransform loaded classes:", t);
        }
    }

    public static void injectRenderersLive() {
        Thread thread = new Thread(() -> {
            System.out.println("[Agent] Waiting for Minecraft client instance to initialize...");

            // 1. Poll until Minecraft.getInstance() is created
            while (Minecraft.getInstance() == null) {
                try {
                    Thread.sleep(100); // Poll every 100ms
                } catch (InterruptedException ignored) {}
            }
        Minecraft.getInstance().execute(() -> {
            try {
                Central.checkForNullObjects();
                var dispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
                Minecraft minecraft = Minecraft.getInstance();
                EntityRendererProvider.Context context = new EntityRendererProvider.Context(minecraft.getEntityRenderDispatcher(), new BlockModelResolver(minecraft.getModelManager()), minecraft.getItemModelResolver(), new MapRenderer(minecraft.getAtlasManager(), new MapTextureManager(minecraft.getTextureManager())), minecraft.getResourceManager(), EntityModelSet.vanilla(), new EquipmentAssetManager(), minecraft.getAtlasManager(), minecraft.font, minecraft.playerSkinRenderCache());

                // Access the 'renderers' field reflectively
                var field = dispatcher.getClass().getDeclaredField("renderers");
                field.setAccessible(true);

                @SuppressWarnings("unchecked")
                var currentMap = new java.util.HashMap<>((java.util.Map<EntityType<?>, EntityRenderer<?, ?>>) field.get(dispatcher));

                // Put your custom entity renderers into currentMap
                currentMap.put(PetsInitializer.DUCK, new DuckRenderer(context));

                field.set(dispatcher, java.util.Map.copyOf(currentMap));
                LOGGER.info("[Agent] Successfully injected live entity renderers!");
            } catch (Throwable t) {
                LOGGER.error("[Agent] Failed to inject renderers live:", t);
            }
        });
    }, "agent");
        thread.setDaemon(true);
        thread.start();
    }
}