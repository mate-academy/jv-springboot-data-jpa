package mate.academy.springboot.datajpa.service.mapper;

public interface RequestDtoMapper<D, T> {
    T toModel(D dto);
}
