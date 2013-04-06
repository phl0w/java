package org.phl0w.itclient2007.asm.updater;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.phl0w.itclient2007.asm.updater.transformers.AbstractClassTransformer;
import org.phl0w.itclient2007.asm.updater.transformers.impl.PaintTransformer;
import org.phl0w.itclient2007.asm.updater.transformers.impl.StringListTransformer;
import org.phl0w.itclient2007.utilities.Settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Updater implements Opcodes {

    private final HashMap<String, String> mappedHooks = new HashMap<>();
    private final List<AbstractClassTransformer> transformerList = new ArrayList<>();

    public Updater() {
        transformerList.add(new PaintTransformer(this));
        transformerList.add(new StringListTransformer(this));
    }

    public void update() {
        File dir = new File(Settings.SAVE_DIRECTORY + "gamepack/");
        for (File f : dir.listFiles()) {
            if (f.getName().endsWith(".class")) {
                try {
                    FileInputStream fis = new FileInputStream(f);
                    ClassReader cr = new ClassReader(fis);
                    ClassNode cn = new ClassNode();
                    cr.accept(cn, ASM4);
                    for (final AbstractClassTransformer act : transformerList.toArray(new AbstractClassTransformer[transformerList.size()])) {
                        if (act.accept(cn)) {
                            log(String.format("[ - %s - ]", act.getTransformerName()));
                            log(String.format("^ identified as %s ^", cn.name));
                            act.runTransform(cn);
                            transformerList.remove(act);
                            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassReader.SKIP_FRAMES);
                            cn.accept(cw);
                            FileOutputStream fos = new FileOutputStream(f);
                            fos.write(cw.toByteArray());
                            fos.flush();
                            fos.close();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void mapHook(String s1, String s2) {
        mappedHooks.put(s1, s2);
    }

    public String getMappedHook(String s1) {
        return mappedHooks.get(s1);
    }

    private void log(String msg) {
        System.out.println(msg);
    }

    public void addNewTransformer(AbstractClassTransformer act) {
        this.transformerList.add(act);
    }

}
