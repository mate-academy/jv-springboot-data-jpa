package mate.academy.springboot.datajpa.exception;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
