package mate.academy.springboot.datajpa.mapper;

public interface RequestDtoMapper<Q, M> {
    M mapToModel(Q dto);
}
