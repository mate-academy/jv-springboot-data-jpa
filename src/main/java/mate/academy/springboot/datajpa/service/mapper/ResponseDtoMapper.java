package mate.academy.springboot.datajpa.service.mapper;

public interface ResponseDtoMapper<M, S> {
    S mapToDto(M m);
}
