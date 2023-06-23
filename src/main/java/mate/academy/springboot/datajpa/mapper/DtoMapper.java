package mate.academy.springboot.datajpa.mapper;

public interface DtoMapper<E, Q, A> {
    E mapToModel(Q dto);

    A mapToDto(E entity);
}
