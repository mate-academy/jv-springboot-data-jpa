package mate.academy.springboot.datajpa.service.mapper;

public interface ResponseDtoMapper<D,T> {
    D toDto(T t);
}
