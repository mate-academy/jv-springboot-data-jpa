package mate.academy.springboot.datajpa.service.mapper.response;

public interface ResponseDtoMapper<D, T> {
    D mapToDto(T model);
}
