package mate.academy.springboot.datajpa.dto.mapper;

import org.springframework.stereotype.Component;

@Component
public interface DtoMapper<T, T1, T2> {
    T2 toResponseDto(T category);

    T toModel(T1 requestDto);
}
