package mate.academy.springboot.datajpa.dto.mapper;

public interface ResponseMapper<D, T> {
    D toDto(T t);
}
