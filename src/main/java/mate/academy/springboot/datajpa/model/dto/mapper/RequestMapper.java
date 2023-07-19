package mate.academy.springboot.datajpa.model.dto.mapper;

public interface RequestMapper<D, E> {
    E toEntity(D dto);
}
