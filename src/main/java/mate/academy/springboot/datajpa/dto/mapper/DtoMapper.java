package mate.academy.springboot.datajpa.dto.mapper;

public interface DtoMapper<M, D, R> {
    D mapToDto(M model);

    M mapToModel(R dto);
}
