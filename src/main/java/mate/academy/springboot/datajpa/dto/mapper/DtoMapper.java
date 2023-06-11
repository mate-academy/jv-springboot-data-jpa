package mate.academy.springboot.datajpa.dto.mapper;

public interface DtoMapper<E, T, R> {
    T toDto(E e);

    E toModel(R r);
}
