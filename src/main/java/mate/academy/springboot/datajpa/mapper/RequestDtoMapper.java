package mate.academy.springboot.datajpa.mapper;

public interface RequestDtoMapper <D, T> {
    T mapToModel(D dto);
}
