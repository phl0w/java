package org.phl0w.itclient2007.utilities;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashMap;

public class WebCrawler {

    private static HashMap<String, String> hm = null;

    public static HashMap<String, String> crawl() {
        if (hm != null) {
            return hm;
        }
        hm = new HashMap<>();
        try {
            final Document d = Jsoup.connect(Settings.PAGE_URL).get();
            final Element e = d.getElementById("deployJava");
            for (final String s : e.toString().split("\n")) {
                if (s.contains("archive=")) {
                    hm.put("gamepack", s.replaceAll(Settings.REGEX_1, "").replaceAll(Settings.REGEX_2, "")
                            .split("=")[1]);
                }
                if (s.contains(Settings.PARAM_REGEX)) {
                    final String[] s1 = s.replaceAll(Settings.REGEX_1, "").replaceAll(Settings.REGEX_2, "").replaceAll(
                            Settings.PARAM_REGEX_2, "").replaceAll(Settings.PARAM_REGEX_3, "").split(Settings.
                            PARAM_REGEX);
                    hm.put(s1[0].trim(), s1[1].trim());
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return hm;
    }

    public static String get(final String key) {
        return hm == null ? crawl().get(key) : hm.get(key);
    }

}
