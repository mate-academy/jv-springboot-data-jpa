package mate.academy.springboot.datajpa.mapper;

public interface DtoRequestMapper<D, C> {
    C fromDto(D dto);
}
