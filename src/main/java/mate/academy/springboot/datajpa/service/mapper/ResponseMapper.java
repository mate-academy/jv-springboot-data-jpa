package mate.academy.springboot.datajpa.service.mapper;

public interface ResponseMapper<D, T> {
    D mapToDto(T t);
}
