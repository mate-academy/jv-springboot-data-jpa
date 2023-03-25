package mate.academy.springboot.datajpa.service.mapper;

public interface RequestDtoMapper<M, Q> {
    M mapToModel(Q dto);
}
