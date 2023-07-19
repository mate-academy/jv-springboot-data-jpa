package mate.academy.springboot.datajpa.model.dto.mapper;

public interface ResponseMapper<D, E> {
    D toDto(E entity);
}
