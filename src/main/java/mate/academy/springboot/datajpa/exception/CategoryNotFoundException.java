package mate.academy.springboot.datajpa.exception;

public class CategoryNotFoundException extends Exception {

    public CategoryNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
