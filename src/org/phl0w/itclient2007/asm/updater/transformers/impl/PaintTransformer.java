package org.phl0w.itclient2007.asm.updater.transformers.impl;

import org.objectweb.asm.tree.ClassNode;
import org.phl0w.itclient2007.asm.updater.Updater;
import org.phl0w.itclient2007.asm.updater.transformers.AbstractClassTransformer;

public class PaintTransformer extends AbstractClassTransformer {

    public PaintTransformer(final Updater u) {
        super(u);
    }

    @Override
    public boolean allow(final ClassNode cn) {
        return cn.superName.equals("java/applet/Applet") && cn.interfaces.contains("java/lang/Runnable");
    }

    @Override
    public void runTransform(final ClassNode cn) {

    }

    @Override
    public String getTransformerName() {
        return "Paint";
    }
}
