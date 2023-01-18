package mate.academy.springboot.datajpa.component.mapper;

public interface RequestDtoMapper<D, M> {
    M mapToModel(D dto);
}
