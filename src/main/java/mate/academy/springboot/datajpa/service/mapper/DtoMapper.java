package mate.academy.springboot.datajpa.service.mapper;

public interface DtoMapper<E, Q, S> {
    E toModel(Q requestDto);

    S toDto(E element);
}
