package mate.academy.springboot.datajpa.service.mapper;

public interface DtoMapper<D, M, T> {
    M toDto(T model);

    T toModel(D requestDto);
}
