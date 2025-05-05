package mate.academy.springboot.datajpa.dto.mapper;

public interface DtoMapper<D, R, M> {
    M mapToModel(D dto);

    R mapToDto(M model);
}
