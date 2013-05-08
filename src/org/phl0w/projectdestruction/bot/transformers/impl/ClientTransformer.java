package org.phl0w.projectdestruction.bot.transformers.impl;

import org.objectweb.asm.tree.ClassNode;
import org.phl0w.projectdestruction.Main;
import org.phl0w.projectdestruction.bot.transformers.AbstractClassTransformer;

public class ClientTransformer extends AbstractClassTransformer {

    @Override
    public boolean allow(final ClassNode cn) {
        return cn.name.contains("ClientHook");
    }

    @Override
    public void runTransform(final ClassNode cn) {
        log("ClassNode " + cn.name + " found, running transformer.");
        addGetter(cn, "server", "getServer", "Ljava/lang/String;", true);
        Main.mapHook("getServer", cn.name);
        addInterface(cn, "ClientHook");
    }

    @Override
    public String getTransformerName() {
        return "ClientHook Transformer";
    }


}
