package mate.academy.springboot.datajpa.service.mapper;

public interface ResponseDtoMapper<T, D> {
    D mapToDto(T t);
}
