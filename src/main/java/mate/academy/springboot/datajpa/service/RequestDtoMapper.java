package mate.academy.springboot.datajpa.service;

public interface RequestDtoMapper<D, T> {
    T mapToModel(D dto);
}