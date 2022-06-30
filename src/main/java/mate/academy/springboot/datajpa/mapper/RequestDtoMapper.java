package mate.academy.springboot.datajpa.mapper;

public interface RequestDtoMapper<D, E> {
    E mapToEntity(D d);
}
