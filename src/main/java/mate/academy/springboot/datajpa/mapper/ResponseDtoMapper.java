package mate.academy.springboot.datajpa.mapper;

public interface ResponseDtoMapper<E, D> {
    D toDto(E entity);
}
