package mate.academy.springboot.datajpa.service.mapper;

public interface RequestMapper<D, T> {
    T mapToModel(D dto);
}
