package mate.academy.springboot.datajpa.mappers;

public interface ResponseDtoMapper<D, T> {
    D mapToDto(T t);
}
