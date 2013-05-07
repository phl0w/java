package org.phl0w.projectdestruction.bot;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.phl0w.projectdestruction.Main;
import org.phl0w.projectdestruction.bot.transformers.AbstractClassTransformer;
import org.phl0w.projectdestruction.bot.transformers.impl.ClientTransformer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;

public class Updater {

    private static final ArrayList<AbstractClassTransformer> classTransformers = new ArrayList<>();
    private static final HashMap<String, ClassNode> loadedClasses = new HashMap<>();
    private static JarFile jar = null;

    public Updater(final JarFile jf) {
        jar = jf;
        if (jar != null) {
            System.out.println("jar exists");
            loadClasses(jar);
            loadTransformers();
            runTransformers();
            printHooks();
            dumpClasses(loadedClasses, new File("C:\\Users\\Niels\\Desktop\\diversity_DUMP.jar"));
        }
    }

    private void loadClasses(final JarFile jf) {
        final Enumeration<JarEntry> entries = jf.entries();
        try {
            while (entries.hasMoreElements()) {
                final JarEntry entry = entries.nextElement();
                if (entry.getName().endsWith("class")) {
                    final ClassNode cn = new ClassNode();
                    final ClassReader cr = new ClassReader(jf.getInputStream(entry));
                    cr.accept(cn, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
                    System.out.println("Loaded class: " + cn.name);
                    loadedClasses.put(cn.name, cn);
                }
            }
            jar.close();
        } catch (final IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void dumpClasses(final HashMap<String, ClassNode> loadedClasses, final File output) {
        try {
            if (!output.exists()) {
                output.createNewFile();
            } else {
                output.delete();
                output.createNewFile();
            }
            final JarOutputStream jos = new JarOutputStream(new FileOutputStream(output));
            for (final ClassNode cn : loadedClasses.values()) {
                System.out.printf("Packing .class file: %s%n", cn.name);
                final JarEntry entry = new JarEntry(cn.name + ".class");
                jos.putNextEntry(entry);
                final ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
                cn.accept(cw);
                jos.write(cw.toByteArray());
            }
            jos.flush();
            jos.close();
        } catch (final Exception ioe) {
            ioe.printStackTrace();
        }
    }


    private void loadTransformers() {
        classTransformers.add(new ClientTransformer());
    }

    private void runTransformers() {
        for (final ClassNode cn : loadedClasses.values()) {
            for (final AbstractClassTransformer act : classTransformers) {
                if (act.accept(cn)) {
                    act.runTransform(cn);
                }
            }
        }
    }

    private void printHooks() {
        int i = 1;
        for (final Map.Entry<String, String> entrySet : Main.mappedHooks.entrySet()) {
            System.out.printf("Hook #%d: %s in %s.class", i, entrySet.getKey(), entrySet.getValue());
            i++;
        }
    }
}
