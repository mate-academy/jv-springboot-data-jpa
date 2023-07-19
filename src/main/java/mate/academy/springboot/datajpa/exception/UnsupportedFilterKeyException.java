package mate.academy.springboot.datajpa.exception;

import mate.academy.springboot.datajpa.util.StringUtils;

public class UnsupportedFilterKeyException extends RuntimeException {
    public UnsupportedFilterKeyException(String message) {
        super(message);
    }

    public static UnsupportedFilterKeyException withKey(String key, Class<?> clazz) {
        return new UnsupportedFilterKeyException(String.format(
                "Key '%s' is not supported for filtering %s",
                key, StringUtils.toPlural(clazz.getSimpleName().toLowerCase())
        ));
    }
}
