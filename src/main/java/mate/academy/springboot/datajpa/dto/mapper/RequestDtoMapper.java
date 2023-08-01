package mate.academy.springboot.datajpa.dto.mapper;

public interface RequestDtoMapper<D, M> {
    M toModel(D d);
}
