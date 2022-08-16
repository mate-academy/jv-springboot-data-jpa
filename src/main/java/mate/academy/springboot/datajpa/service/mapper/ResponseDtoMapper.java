package mate.academy.springboot.datajpa.service.mapper;

public interface ResponseDtoMapper<D, T> {
    T toDto(D dto);
}
