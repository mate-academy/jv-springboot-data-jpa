package mate.academy.springboot.datajpa.dto.mapper;

public interface RequestDtoMapper<T, K> {
    T mapToModel(K k);
}
