package org.phl0w.itclient2007.asm.updater.transformers.impl;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.phl0w.itclient2007.asm.updater.Updater;
import org.phl0w.itclient2007.asm.updater.transformers.AbstractClassTransformer;

public class StringListTransformer extends AbstractClassTransformer {

    public StringListTransformer(final Updater u) {
        super(u);
    }

    @Override
    public boolean allow(final ClassNode cn) {
        int count = 0;
        for (Object o : cn.fields) {
            FieldNode fn = (FieldNode) o;
            if (fn.desc.contains("java/lang/String")) {
                count++;
            }
        }
        return count > 100;
    }

    @Override
    public void runTransform(final ClassNode cn) {
        addGetter(cn, "j", "getString", "Ljava/lang/String;", true);
        super.mapHook("StringList", cn.name);
    }

    @Override
    public String getTransformerName() {
        return "StringList";
    }
}
