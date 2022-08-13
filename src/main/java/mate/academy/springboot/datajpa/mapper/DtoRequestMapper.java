package mate.academy.springboot.datajpa.mapper;

public interface DtoRequestMapper<D, M> {
    M fromDto(D dto);
}
