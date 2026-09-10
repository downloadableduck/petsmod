package com.jeff.pets.client.mixin.client;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class EntityRenderStateClassVisitor extends ClassVisitor {

    private static final String INTERFACE = "com/jeff/pets/client/rendering/IPetRenderState";
    private static final String UTILS = "com/jeff/pets/client/Utils";

    public EntityRenderStateClassVisitor(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    @Override
    public void visit(int version, int access, String methodName, String signature, String superName, String[] interfaces) {
        String[] interfaces2;
        if (interfaces == null) {
            interfaces2 = new String[]{INTERFACE};
        } else {
            interfaces2 = new String[interfaces.length + 1];
            System.arraycopy(interfaces, 0, interfaces2, 0, interfaces.length);
            interfaces2[interfaces.length] = INTERFACE;
        }

        super.visit(version, access, methodName, signature, superName, interfaces2);
    }

    @Override
    public void visitEnd() {
        this.visitField(Opcodes.ACC_PRIVATE, "pets$isMyPet", "Z", null, null).visitEnd();
        this.visitField(Opcodes.ACC_PRIVATE, "pets$petSkin", "Ljava/lang/String;", null, null).visitEnd();

        this.createIsMyPet();
        this.createSetMyPet();
        this.createGetPetSkin();
        this.createSetPetSkin();
    }

    private void createIsMyPet() {
        MethodVisitor visitor = this.cv.visitMethod(Opcodes.ACC_PUBLIC, "pets$isMyPet", "()Z", null, null);
        visitor.visitCode();
        visitor.visitVarInsn(Opcodes.ALOAD, 0);
        visitor.visitFieldInsn(Opcodes.GETFIELD, "net/minecraft/client/renderer/entity/state/EntityRenderState", "pets$isMyPet", "Z");
        visitor.visitInsn(Opcodes.IRETURN);
        visitor.visitMaxs(1, 1);
        visitor.visitEnd();
    }

    private void createSetMyPet() {
        MethodVisitor visitor = this.cv.visitMethod(Opcodes.ACC_PUBLIC, "pets$setMyPet", "(Z)V", null, null);
        visitor.visitCode();
        visitor.visitVarInsn(Opcodes.ALOAD, 0);
        visitor.visitVarInsn(Opcodes.ILOAD, 1);
        visitor.visitFieldInsn(Opcodes.PUTFIELD, "net/minecraft/client/renderer/entity/state/EntityRenderState", "pets$isMyPet", "Z");
        visitor.visitInsn(Opcodes.RETURN);
        visitor.visitMaxs(2, 2);
        visitor.visitEnd();
    }

    private void createGetPetSkin() {
        MethodVisitor visitor = this.cv.visitMethod(Opcodes.ACC_PUBLIC, "pets$getPetSkin", "()Ljava/lang/String;", null, null);
        visitor.visitCode();
        visitor.visitVarInsn(Opcodes.ALOAD, 0);
        visitor.visitFieldInsn(Opcodes.GETFIELD, "net/minecraft/client/renderer/entity/state/EntityRenderState", "pets$isMyPet", "Z");
        Label label = new Label();
        visitor.visitJumpInsn(Opcodes.IFEQ, label);
        visitor.visitMethodInsn(Opcodes.INVOKESTATIC, UTILS, "getActivePetSkin", "()Ljava/lang/String;", false);
        visitor.visitInsn(Opcodes.ARETURN);
        visitor.visitLabel(label);
        visitor.visitVarInsn(Opcodes.ALOAD, 0);
        visitor.visitFieldInsn(Opcodes.GETFIELD, "net/minecraft/client/renderer/entity/state/EntityRenderState", "pets$petSkin", "Ljava/lang/String;");
        visitor.visitInsn(Opcodes.ARETURN);
        visitor.visitMaxs(2, 1);
        visitor.visitEnd();
    }

    private void createSetPetSkin() {
        MethodVisitor visitor = this.cv.visitMethod(Opcodes.ACC_PUBLIC, "pets$setPetSkin", "(Ljava/lang/String;)V", null, null);
        visitor.visitCode();
        visitor.visitVarInsn(Opcodes.ALOAD, 0);
        visitor.visitVarInsn(Opcodes.ALOAD, 1);
        visitor.visitFieldInsn(Opcodes.PUTFIELD, "net/minecraft/client/renderer/entity/state/EntityRenderState", "pets$petSkin", "Ljava/lang/String;");
        visitor.visitInsn(Opcodes.RETURN);
        visitor.visitMaxs(2, 2);
        visitor.visitEnd();
    }


}
