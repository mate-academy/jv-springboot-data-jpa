package mate.academy.springboot.datajpa.mapper;

public interface ResponseDtoMapper<M, D> {
    D mapToDto(M m);
}
