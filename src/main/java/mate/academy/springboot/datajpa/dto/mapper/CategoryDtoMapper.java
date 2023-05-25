package mate.academy.springboot.datajpa.dto.mapper;

public interface CategoryDtoMapper<D, R, P> {
    R toResponseDto(D category);

    D toModel(P requestDto);
}
