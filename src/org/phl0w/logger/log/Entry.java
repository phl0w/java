package org.phl0w.logger.log;

import org.phl0w.logger.Utilities;

import java.util.Date;

public class Entry {

    private Date time;
    private Level level;
    private String message;

    public Entry(final Date time, final Level level, final String message) {
        this.time = time;
        this.level = level;
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public Level getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return Utilities.getTimestamp(getTime()).concat(" [").concat(getLevel().toString()).concat("]: ").concat(getMessage());
    }
}
