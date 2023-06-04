package mate.academy.springboot.datajpa.dto.mapper;

public interface ResponseDtoMapper<T, D> {
    D mapToDto(T t);
}
