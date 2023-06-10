package mate.academy.springboot.datajpa.dto.mapper;

public interface RequestDtoMapper<D, T> {
    T toModel(D dto);
}
