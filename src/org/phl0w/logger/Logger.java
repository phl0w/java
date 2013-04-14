package org.phl0w.logger;

import org.phl0w.logger.log.Entry;
import org.phl0w.logger.log.Level;

import java.util.Date;
import java.util.LinkedList;

public class Logger {

    private LinkedList<Entry> logEntries = new LinkedList<>();
    private Level level;

    public Logger() {
        setLevel(Level.INFO);
    }

    public void setLevel(final Level level) {
        this.level = level;
    }

    public void log(final String message, final Level... l) {
        final Entry entry = new Entry(new Date(), l.length == 1 ? l[0] : level, message);
        logEntries.add(entry);
        System.out.println(entry);
    }

    public LinkedList<Entry> getEntriesByLevel(final Level level) {
        final LinkedList<Entry> entries = new LinkedList<>();
        for (final Entry entry : logEntries) {
            if (entry.getLevel().equals(level) || level.equals(Level.ALL)) {
                entries.add(entry);
            }
        }
        return entries;
    }

    public void dump(final Level level, final Date... limit) {
        System.out.println("----- DUMPING LOG (Date limit: " + (limit.length == 1 ? "YES" : "NO") + ".) -----");
        for (final Entry entry : getEntriesByLevel(level)) {
            if (limit.length == 1) {
                if (entry.getTime().after(limit[0])) {
                    System.out.println(entry);
                }
            } else {
                System.out.println(entry);
            }
        }
    }
}
