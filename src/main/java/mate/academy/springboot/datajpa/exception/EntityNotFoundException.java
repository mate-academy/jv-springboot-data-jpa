package mate.academy.springboot.datajpa.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }

    public static EntityNotFoundException withId(Class<?> clazz, Object id) {
        return new EntityNotFoundException(String.format(
                "%s with id %s not found", clazz.getSimpleName(), id
        ));
    }
}
