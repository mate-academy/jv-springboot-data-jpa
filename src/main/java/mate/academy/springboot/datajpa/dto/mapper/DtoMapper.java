package mate.academy.springboot.datajpa.dto.mapper;

public interface DtoMapper<I, M, O> {
    M mapToModel(I requestDto);

    O mapToDto(M model);
}
