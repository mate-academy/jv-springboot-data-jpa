package mate.academy.springboot.datajpa.mapper;

public interface RequestDtoMapper<E, D> {
    E toModel(D requestDto);
}
