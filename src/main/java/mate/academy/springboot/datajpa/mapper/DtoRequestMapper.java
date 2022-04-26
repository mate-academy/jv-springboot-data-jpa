package mate.academy.springboot.datajpa.mapper;

public interface DtoRequestMapper<D, C> {
    C toEntity(D dto);
}
