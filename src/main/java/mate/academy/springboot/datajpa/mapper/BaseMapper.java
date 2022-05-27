package mate.academy.springboot.datajpa.mapper;

public abstract class BaseMapper<T, D, R> {

    public abstract T mapToEntity(D dto);

    public abstract R mapToDto(T entity);
}
