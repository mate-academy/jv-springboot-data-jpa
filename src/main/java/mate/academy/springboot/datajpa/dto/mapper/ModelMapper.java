package mate.academy.springboot.datajpa.dto.mapper;

public interface ModelMapper<T, E, K> {
    T toResponseDto(E e);

    E toModel(K k);
}
