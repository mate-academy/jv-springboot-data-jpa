package mate.academy.springboot.datajpa.service.mapper;

public interface ResponseDtoMapper<T, D> {
    T mapToDto(D responseDto);
}
