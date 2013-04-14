package org.phl0w.logger.log;

public enum Level {

    INFO(0),
    SEVERE(1),
    ERROR(2),
    ANTIBAN(3),
    ALL(4);

    private int level;

    private Level(final int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return name();
    }
}
