package mate.academy.springboot.datajpa.service;

public interface ResponseDtoMapper<D, T> {
    D toDto(T t);
}
