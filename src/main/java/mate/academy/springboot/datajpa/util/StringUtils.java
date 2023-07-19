package mate.academy.springboot.datajpa.util;

public class StringUtils {
    private StringUtils() {
    }

    public static String toPlural(String string) {
        if (string.endsWith("s")
                || string.endsWith("x")
                || string.endsWith("z")
                || string.endsWith("ch")
                || string.endsWith("sh")) {
            return string + "es";
        }
        if (string.endsWith("y")) {
            return string.substring(0, string.length() - 1) + "ies";
        }
        return string + "s";
    }
}
