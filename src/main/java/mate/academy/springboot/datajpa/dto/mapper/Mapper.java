package mate.academy.springboot.datajpa.dto.mapper;

public interface Mapper <T, Q, V> {
    default V toResponseDto(T model) {
        return null;
    }

    default T toModel(Q requestDto) {
        return null;
    }
}
