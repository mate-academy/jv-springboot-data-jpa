package mate.academy.springboot.datajpa.dto.mapper;

public interface DtoMapper<M, Q, S> {
    M mapToModel(Q requestDto);

    S mapToDto(M model);
}
