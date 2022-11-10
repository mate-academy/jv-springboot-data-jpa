package mate.academy.springboot.datajpa.mapper;

public interface RequestDtoMapper<D, C> {
    C toModel(D object);
}
