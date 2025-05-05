package mate.academy.springboot.datajpa.service.mapper;

public interface DtoMapper<E, S, Q> {
    S mapToDto(E entity);

    E mapToEntity(Q dto);
}
