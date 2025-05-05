package mate.academy.springboot.datajpa.dto.mapper;

public interface DtoMapper<M, Q, S> {
    M mapToModel(Q dto);

    S mapToDto(M model);
}
