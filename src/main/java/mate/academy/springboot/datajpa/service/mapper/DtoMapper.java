package mate.academy.springboot.datajpa.service.mapper;

public interface DtoMapper<E, S, Q> {
    S toDto(E entity);

    E toEntity(Q dto);
}
