package mate.academy.springboot.datajpa.service.mappers;

public interface RequestDtoMapper<D, T> {
    T mapToModel(D dto);
}
