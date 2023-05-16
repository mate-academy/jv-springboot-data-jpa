package mate.academy.springboot.datajpa.service.mapper;

public interface DtoMapper<D, T, V> {
    D mapToModel(T dto);

    V mapToDto(D t);
}
