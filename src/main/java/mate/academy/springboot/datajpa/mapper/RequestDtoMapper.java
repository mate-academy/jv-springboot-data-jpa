package mate.academy.springboot.datajpa.mapper;

public interface RequestDtoMapper<D, T> {
    T toModel(D dto);
}
