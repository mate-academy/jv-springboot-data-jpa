package mate.academy.springboot.datajpa.service.mappers;

public interface ResponseDtoMapper<D, T> {
    D mapToDto(T t);
}
