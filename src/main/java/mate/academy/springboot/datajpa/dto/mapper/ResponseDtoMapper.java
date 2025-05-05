package mate.academy.springboot.datajpa.dto.mapper;

public interface ResponseDtoMapper<K, T> {
    T mapToDto(K k);
}
