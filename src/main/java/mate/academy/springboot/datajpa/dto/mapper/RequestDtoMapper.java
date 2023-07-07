package mate.academy.springboot.datajpa.dto.mapper;

public interface RequestDtoMapper<M, D> {
    M mapToModel(D dto);
}
