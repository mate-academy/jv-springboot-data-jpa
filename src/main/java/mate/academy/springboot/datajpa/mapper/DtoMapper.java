package mate.academy.springboot.datajpa.mapper;

public interface DtoMapper<D, S, E> {
    D toModel(S dto);

    E toDto(D model);
}
