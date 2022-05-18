package mate.academy.springboot.datajpa.mappers;

public interface RequestDtoMapper<D, T> {
    T mapToModel(D dto);
}
