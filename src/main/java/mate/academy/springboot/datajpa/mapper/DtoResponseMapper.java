package mate.academy.springboot.datajpa.mapper;

public interface DtoResponseMapper<T, K> {
    K toDto(T model);
}
