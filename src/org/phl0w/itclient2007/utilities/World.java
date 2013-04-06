package org.phl0w.itclient2007.utilities;

public class World {

    private int worldId, u, population;
    private boolean members;
    private String prefix, location, country, name;

    public World(final int worldId, final boolean members, final int u, final String prefix, final int population,
                 final String location, final String country, final String name) {
        this.worldId = worldId;
        this.members = members;
        this.u = u;
        this.prefix = prefix;
        this.population = population;
        this.location = location;
        this.country = country;
        this.name = name;
        this.worldId = worldId;
    }

    public String getSite() {
        return "http://".concat(prefix).concat(".runescape.com/");
    }

    public String trim() {
        return prefix.concat(".runescape.com");
    }

    public int getPopulation() {
        return population;
    }

    public int getWorldId() {
        return worldId;
    }

    public int getUnknown() {
        return u;
    }

    public boolean isMembers() {
        return members;
    }

    public String getLocation() {
        return "[" + country + "]: ".concat(location);
    }

    public String getName() {
        return getLocation().concat(" ").concat(name);
    }

    @Override
    public String toString() {
        return getName();
    }

}
