package mate.academy.springboot.datajpa.service.mappers;

public interface DtoMapper<D, E, M> {
    M mapToModel(D dto);

    E mapToDto(M model);
}
