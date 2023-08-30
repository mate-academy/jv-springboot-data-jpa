package mate.academy.springboot.datajpa.dto.mapper;

public interface DtoMapper<D, T, M> {
    T toDto(M m);

    M toModel(D dto);
}
