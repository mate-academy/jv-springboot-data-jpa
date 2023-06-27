package mate.academy.springboot.datajpa.service.mapper;

public interface DtoMapper<R, T, M> {
    M mapToModel(T requestDto);

    R mapToDto(M model);
}
