package com.jeff.pets.client.mixin.client;

import net.minecraft.client.renderer.debug.EntityHitboxDebugRenderer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.lang.reflect.Method;

import static com.jeff.pets.client.Central.CONFIG;

public class NoHitboxMixin {
    public static byte[] transform(byte[] basicClass) {
        ClassReader reader = new ClassReader(basicClass);
        ClassNode classNode = new ClassNode();
        reader.accept(classNode, 0);

        for (MethodNode method : classNode.methods) {
            if ("showHitboxes".equals(method.name)) {
                InsnList isn = new InsnList();
                LabelNode node = new LabelNode();
                isn.add(new FieldInsnNode(
                        Opcodes.GETSTATIC,
                        "com/jeff/pets/client/Central",
                        "CONFIG",
                        "Lcom/jeff/pets/client/PetsConfig;"

                ));

                isn.add(new FieldInsnNode(
                        Opcodes.GETFIELD,
                        "com/jeff/pets/client/PetsConfig",
                        "renderPetHitbox",
                        "Z"
                ));

                isn.add(new JumpInsnNode(Opcodes.IFNE, node));
                isn.add(new InsnNode(Opcodes.RETURN));
                isn.add(node);
                method.instructions.insert(isn);
            }
        }
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        classNode.accept(writer);
        return writer.toByteArray();
    }
}
