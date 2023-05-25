package mate.academy.springboot.datajpa.service.mapper;

public interface DtoMapper<M, R, V> {
    M toModel(R request);

    V toDto(M model);
}
