package mate.academy.springboot.datajpa.exception;

import java.util.NoSuchElementException;

public class NoSuchProductException extends NoSuchElementException {

    public NoSuchProductException(String s) {
        super(s);
    }
}
