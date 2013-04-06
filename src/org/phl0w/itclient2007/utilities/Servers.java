package org.phl0w.itclient2007.utilities;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class Servers {

    public static ArrayList<String> retrieveWorlds() {
        BufferedReader br;
        final ArrayList<String> list = new ArrayList<>();
        try {
            final URL url = new URL("http://oldschool.runescape.com/slu.ws");
            final InputStreamReader in = new InputStreamReader(url.openStream());
            br = new BufferedReader(in);
            String str;
            while ((str = br.readLine()) != null) {
                if (str.startsWith("  e(")) {
                    str = str.substring(4, str.indexOf("\");"));
                    list.add(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<World> parseWorlds() {
        final ArrayList<String> list = retrieveWorlds();
        ArrayList<World> worlds = new ArrayList<>();
        for (String str : list) {
            str = str.replaceAll("\"", "");
            String[] splits = str.split(",");
            int worldId = -1;
            boolean members = true;
            int unknown = -1;
            String prefix = "";
            int population = -1;
            String location = "", country = "", name = "";
            for (int i = 0; i < splits.length; i++) {
                switch (i) {
                    case 0:   //World ID
                        worldId = Integer.parseInt(splits[i]);
                        break;
                    case 1:
                        members = Boolean.parseBoolean(splits[i]);
                        break;
                    case 2:
                        unknown = Integer.parseInt(splits[i]);
                        break;
                    case 3:
                        prefix = splits[i];
                        break;
                    case 4:
                        population = Integer.parseInt(splits[i]);
                        break;
                    case 5:
                        location = splits[i];
                        break;
                    case 6:
                        country = splits[i];
                        break;
                    case 7:
                        name = splits[i];
                        break;
                    default:
                        System.out.println("Invalid param: " + splits[i]);
                        break;
                }
            }
            final World w = new World(worldId, members, unknown, prefix, population, location, country, name);
            worlds.add(w);
        }
        return worlds;
    }

    public static World getRandom() {
        final ArrayList<World> worlds = parseWorlds();
        final Random r = new Random(System.currentTimeMillis());
        return worlds.get(r.nextInt(worlds.size()));
    }
}
