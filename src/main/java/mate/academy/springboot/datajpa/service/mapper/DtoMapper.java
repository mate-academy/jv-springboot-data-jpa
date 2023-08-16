package mate.academy.springboot.datajpa.service.mapper;

public interface DtoMapper<D, D1, T> extends RequestDtoMapper<D, T>,
        ResponseDtoMapper<T, D1> {
}
