package mate.academy.springboot.datajpa.mapper;

public interface RequestDtoMapper<D, M> {
    M toModel(D data);
}
