package org.phl0w.it07.methods;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashMap;

public class Crawler {

    private static HashMap<String, String> hm = null;
    private static final String REGEX1 = "document\\.write\\(\'";
    private static final String REGEX2 = "\'\\);";
    private static final String PARAMREGEX = "\" value=\"";
    private static final String PARAMREGEX2 = "\\<param name=\"";
    private static final String PARAMREGEX3 = "\"\\>";

    private static String url;

    public Crawler(final String url) {
        this.url = url;
    }

    public static HashMap<String, String> crawl() {
        if (hm != null) {
            return hm;
        }
        hm = new HashMap<String, String>();
        try {
            Document d = Jsoup.connect(url).get();
            Element e = d.getElementById("deployJava");
            for (String s : e.toString().split("\n")) {
                if (s.contains("archive=")) {
                    hm.put("gamepack", s.replaceAll(REGEX1, "").replaceAll(REGEX2, "").split("=")[1]);
                }
                if (s.contains(PARAMREGEX)) {
                    String[] s1 = s.replaceAll(REGEX1, "").replaceAll(REGEX2, "").replaceAll(PARAMREGEX2, "").replaceAll(PARAMREGEX3, "").split(PARAMREGEX);
                    hm.put(s1[0].trim(), s1[1].trim());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ;
        }
        return hm;
    }

    public static String get(String key) {
        return hm == null ? crawl().get(key) : hm.get(key);
    }

}
