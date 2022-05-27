package mate.academy.springboot.datajpa.service.mapper;

public interface RequestDtoMapper<M, D> {
    M mapToModel(D dto);
}
