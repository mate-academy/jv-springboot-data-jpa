package mate.academy.springboot.datajpa.service.mapper;

public interface ResponceDtoMapper<D, T> {
    D mapToDto(T model);
}
