package mate.academy.springboot.datajpa.mapper;

public interface DtoMapper<D, R, T> extends RequestDtoMapper<D, T>,
        ResponseDtoMapper<T, R> {
}
