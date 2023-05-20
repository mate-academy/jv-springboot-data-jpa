package mate.academy.springboot.datajpa.service.mapper;

public interface DtoMapper<D, T, S> {
    D toModel(T requestDto);

    S toDto(D element);
}
