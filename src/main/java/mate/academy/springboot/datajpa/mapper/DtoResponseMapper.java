package mate.academy.springboot.datajpa.mapper;

public interface DtoResponseMapper<D, T> {
    D toDto(T t);
}
