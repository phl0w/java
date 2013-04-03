package org.phl0w.it07.loader;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;
import org.phl0w.it07.utilities.Logging;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Loader {

    private URLClassLoader url;

    public HashMap<String, ClassNode> loadedClassNodes = new HashMap<String, ClassNode>();
    public HashMap<String, Class> classes = new HashMap<String, Class>();

    private String jarPath;

    public Loader(final String jarPath) {
        try {
            Logging.log("Path to JAR: " + jarPath, Loader.class);
            url = new URLClassLoader(new URL[]{new URL("file:" + jarPath)});
            this.jarPath = jarPath;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean loadClasses() {
        int count = 0;
        try {
            p("---------------------------------------------------------------------");
            p("--------------------      Jar Loader     ----------------------------");
            p("File: " + jarPath);
            p("JC Hash: " + getClass().hashCode());
            JarFile jf = new JarFile(jarPath);
            p("JarFile Size = " + jf.size());
            p("---------------------------------------------------------------------");
            final Enumeration<JarEntry> entries = jf.entries();
            while (entries.hasMoreElements()) {
                JarEntry next = entries.nextElement();

                if (next.getClass() != null) {
                    if (next.getName().endsWith(".class")) {
                        ++count;
                        p("Class #" + count + ": " + next.getName());
                        p("Decompressed File Size: " + next.getSize() + " bytes" + "\n");
                        ClassReader cr = new ClassReader(jf.getInputStream(next));
                        ClassNode cn = new ClassNode();
                        cr.accept(cn, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
                        loadedClassNodes.put(cn.name, cn);
                        classes.put(cn.name, next.getClass());
                    }
                }

            }
            System.out.println(count + " classes were loaded and stored!");
            p("-----------------------------------------------------------------");
            p("-----------------------------------------------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void p(final String message) {
        Logging.log(message, Loader.class);
    }


}
