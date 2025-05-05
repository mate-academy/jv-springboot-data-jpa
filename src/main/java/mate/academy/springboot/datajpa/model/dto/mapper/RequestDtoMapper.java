package mate.academy.springboot.datajpa.model.dto.mapper;

public interface RequestDtoMapper<D, M> {
    M toModel(D dto);
}
