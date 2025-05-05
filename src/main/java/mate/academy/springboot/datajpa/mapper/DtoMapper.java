package mate.academy.springboot.datajpa.mapper;

public interface DtoMapper<E, K, V> {
    V toDto(E entity);

    E toModel(K requestDto);
}
