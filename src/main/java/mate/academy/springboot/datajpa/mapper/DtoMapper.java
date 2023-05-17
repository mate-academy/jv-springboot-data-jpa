package mate.academy.springboot.datajpa.mapper;

public interface DtoMapper<D, T, M> {
    D mapToModel(T dto);

    M mapToDto(D t);
}
