package mate.academy.springboot.datajpa.mapper;

public interface ResponseDtoMapper<D, C> {
    D toDto(C object);
}
