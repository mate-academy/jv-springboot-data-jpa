package mate.academy.springboot.datajpa.service.mapper;

public interface DtoMapper<K, V, M> {
    M mapToModel(K dto);

    V mapToDto(M m);
}
