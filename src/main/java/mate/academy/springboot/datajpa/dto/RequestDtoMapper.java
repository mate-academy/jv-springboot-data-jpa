package mate.academy.springboot.datajpa.dto;

public interface RequestDtoMapper<D, M> {
    M toModel(D dto);
}
