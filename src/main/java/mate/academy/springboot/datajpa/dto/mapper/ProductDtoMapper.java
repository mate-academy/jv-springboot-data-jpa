package mate.academy.springboot.datajpa.dto.mapper;

public interface ProductDtoMapper<D, R, P> {
    R toResponseDto(D product);

    D toModel(P requestDto);
}
