package mate.academy.springboot.datajpa.service.mapper.request;

public interface RequestDtoMapper<D, T> {
    T mapToModel(D dto);
}
