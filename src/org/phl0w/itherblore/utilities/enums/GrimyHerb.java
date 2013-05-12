package org.phl0w.itherblore.utilities.enums;

/**
 * iTHerblore 2
 * GrimyHerb.java
 * Purpose: provide an enum containing all grimy herbs.
 *
 * @author _phl0w
 * @version 1.0
 * @since 17/4/2013
 */
public enum GrimyHerb {
    GUAM(199),
    MARRENTIL(201),
    TARROMIN(203),
    HARRALANDER(205),
    RANARR(207),
    IRIT(209),
    AVANTOE(211),
    KWUARM(213),
    CADANTINE(215),
    LANTADYME(2485),
    DWARF_WEED(217),
    TORSTOL(221),
    TOADFLAX(3049),
    SPIRIT_WEED(12174),
    WERGALI(14836),
    SNAPDRAGON(3051),
    FELLSTALK(21626);

    private int id;

    private GrimyHerb(final int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }
}
