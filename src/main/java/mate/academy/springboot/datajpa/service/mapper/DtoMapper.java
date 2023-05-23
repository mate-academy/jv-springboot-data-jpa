package mate.academy.springboot.datajpa.service.mapper;

public interface DtoMapper<D, M, T> {
    D toDto(M model);

    M toModel(T requestDto);
}
