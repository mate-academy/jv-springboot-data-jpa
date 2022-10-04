package mate.academy.springboot.datajpa.service.mapper;

public interface RequestDtoMapper<D, M> {
    M mapToModel(D requestDto);
}
