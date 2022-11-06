package mate.academy.springboot.datajpa.service.mapper;

public interface ResponseDtoMapper<D, M> {
    D mapToDto(M model);
}
