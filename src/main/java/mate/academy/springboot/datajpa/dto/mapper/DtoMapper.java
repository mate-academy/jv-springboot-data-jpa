package mate.academy.springboot.datajpa.dto.mapper;

public interface DtoMapper<M, R, S> {
    M toModel(R requestDto);

    S toResponseDto(M model);
}
