package mate.academy.springboot.datajpa.mapper;

public interface RequestDtoMapper<M, D> {
    M mapToModel(D d);
}
