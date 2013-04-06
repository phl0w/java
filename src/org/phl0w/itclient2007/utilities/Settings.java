package org.phl0w.itclient2007.utilities;

public class Settings {

    public static String PAGE_URL = "http://oldschool1.runescape.com/";
    public static final String REGEX_1 = "document\\.write\\(\'";
    public static final String REGEX_2 = "\'\\);";
    public static final String PARAM_REGEX = "\" value=\"";
    public static final String PARAM_REGEX_2 = "\\<param name=\"";
    public static final String PARAM_REGEX_3 = "\"\\>";
    public static final double VERSION = 0.01;
    public static final String SAVE_DIRECTORY = System.getProperty("user.home") + "/itclient2007/";

    public static void setPageUrl(final String NEW_PAGE_URL) {
        PAGE_URL = NEW_PAGE_URL;
    }
}
