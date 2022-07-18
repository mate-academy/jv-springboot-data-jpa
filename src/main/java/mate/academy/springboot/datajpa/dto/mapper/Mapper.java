package mate.academy.springboot.datajpa.dto.mapper;

public interface Mapper<T, Q, V> {
    V toResponseDto(T model);

    T toModel(Q requestDto);
}
