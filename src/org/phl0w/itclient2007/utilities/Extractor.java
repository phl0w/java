package org.phl0w.itclient2007.utilities;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Extractor {

    public void extractZip(final ZipFile zip) {
        try {
            String newPath = Settings.SAVE_DIRECTORY + "gamepack/";
            new File(newPath).mkdir();
            Enumeration zipFileEntries = zip.entries();
            while (zipFileEntries.hasMoreElements()) {
                final ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();
                String currentEntry = entry.getName();
                File destFile = new File(newPath, currentEntry);
                File destinationParent = destFile.getParentFile();
                destinationParent.mkdirs();
                if (!entry.isDirectory()) {
                    BufferedInputStream is = new BufferedInputStream(zip.getInputStream(entry));
                    int currentByte;
                    byte data[] = new byte[2048];
                    FileOutputStream fos = new FileOutputStream(destFile);
                    BufferedOutputStream dest = new BufferedOutputStream(fos, 2048);
                    while ((currentByte = is.read(data, 0, 2048)) != -1) {
                        dest.write(data, 0, currentByte);
                    }
                    dest.flush();
                    dest.close();
                    is.close();
                }
                if (currentEntry.endsWith(".zip")) {
                    extractZip(new ZipFile(destFile.getAbsolutePath()));
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    public boolean deleteFiles(final File dir) {
        for (final File f : dir.listFiles()) {
            if (!f.delete()) {
                return false;
            }
        }
        return true;
    }

}
