package mate.academy.springboot.datajpa.service.mapper;

public interface RequestDtoMapper<T, D> {
    D mapToModel(T requestDto);
}
