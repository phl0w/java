package org.phl0w.it07.methods;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Download {

    public static boolean download(final File output, final String source) {
        if (doDownload(output)) {
            try {
                if (output.exists() && !output.delete()) {
                    throw new RuntimeException("Output directory exists and can not be removed.");
                }
                if (!output.createNewFile()) {
                    throw new RuntimeException("Could not create new JAR file.");
                }
                final ReadableByteChannel rbc = Channels.newChannel(new URL(source).openStream());
                final FileOutputStream fos = new FileOutputStream(output);
                fos.getChannel().transferFrom(rbc, 0, 1 << 24);
                fos.close();
                return true;
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean doDownload(final File output) {
        try {
            if (output.exists()) {
                /**
                 * further jar checking to find version here
                 */
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}