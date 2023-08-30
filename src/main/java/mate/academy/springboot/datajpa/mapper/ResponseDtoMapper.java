package mate.academy.springboot.datajpa.mapper;

public interface ResponseDtoMapper<T, D> {
    D mapToDto(T entity);
}
