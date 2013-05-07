package org.phl0w.projectdestruction.bot.transformers;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;
import org.phl0w.projectdestruction.Main;

import java.lang.reflect.Modifier;

public abstract class AbstractClassTransformer implements Opcodes {

    protected abstract boolean allow(ClassNode cn);

    public abstract void runTransform(ClassNode cn);

    public abstract String getTransformerName();

    public boolean accept(ClassNode cn) {
        return allow(cn);
    }

    protected void addGetter(ClassNode current, String fieldName, String name, String desc, boolean isStatic) {
        MethodNode mn = new MethodNode(ACC_PUBLIC, name, "()" + desc, null, null);
        InsnList nl = new InsnList();
        if (!isStatic) {
            nl.add(new VarInsnNode(ALOAD, 0));
        }
        nl.add(new FieldInsnNode(isStatic ? GETSTATIC : GETFIELD, current.name, fieldName, desc));
        nl.add(new InsnNode(getReturnOpcode(desc)));
        mn.instructions = nl;
        current.methods.add(mn);
        log(String.format("@ %s() --> %s.%s @", name, current.name, fieldName));
    }

    private int getReturnOpcode(String desc) {
        if (desc.endsWith("I") || desc.endsWith("Z")) {
            return IRETURN;
        }
        if (desc.endsWith("J")) {
            return LRETURN;
        }
        if (desc.endsWith("F")) {
            return FRETURN;
        }
        if (desc.endsWith("D")) {
            return DRETURN;
        }
        if (desc.endsWith(";")) {
            return ARETURN;
        }
        return RETURN;
    }

    protected void addInterface(ClassNode current, String interfaceName) {
        log(String.format("# implements %s #", interfaceName));
        current.interfaces.add(interfaceName);
    }

    protected void log(String log) {
        System.out.println(log);
    }

    protected void mapHook(String name, String className) {
        log("Adding hook: " + name + " (" + className + ").");
        Main.mapHook(name, className);
    }

    protected String getMappedHook(String name) {
        return Main.getMappedHook(name);
    }

    protected int getDescriptorCount(ClassNode cn, String desc) {
        int counter = 0;
        for (Object fn : cn.fields) {
            final FieldNode fieldNode = (FieldNode) fn;
            if (!Modifier.isStatic(fieldNode.access) && desc.equals(fieldNode.desc)) {
                counter++;
            }
        }
        return counter;
    }

}

