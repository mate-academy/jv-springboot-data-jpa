package mate.academy.springboot.datajpa.mapper;

public abstract class BaseMapper<T, D> {

    public abstract T mapToEntity(D dto);

    public abstract D mapToDto(T entity);

    public abstract T mapUpdate(T source, T target);
}
