package mate.academy.springboot.datajpa.dto.mapper;

public interface ResponseDtoMapper<M, D> {
    D mapToResponseDto(M model);
}
