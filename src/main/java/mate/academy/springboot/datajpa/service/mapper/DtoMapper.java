package mate.academy.springboot.datajpa.service.mapper;

public interface DtoMapper<T, D, R> {
    D toDto(T entity);

    T toModel(R dto);
}
