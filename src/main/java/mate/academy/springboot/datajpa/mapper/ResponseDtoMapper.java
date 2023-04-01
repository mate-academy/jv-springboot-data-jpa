package mate.academy.springboot.datajpa.mapper;

public interface ResponseDtoMapper<D, T> {
    D toDto(T t);
}
