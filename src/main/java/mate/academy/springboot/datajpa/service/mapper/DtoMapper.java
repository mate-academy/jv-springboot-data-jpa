package mate.academy.springboot.datajpa.service.mapper;

public interface DtoMapper<M, Q, S> {
    M mapToModel(Q q);

    S mapToDto(M m);
}
