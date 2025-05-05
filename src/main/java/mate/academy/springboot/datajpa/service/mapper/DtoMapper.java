package mate.academy.springboot.datajpa.service.mapper;

public interface DtoMapper<E, I, O> {
    E toModel(I requestDto);

    O toResponseDto(E entity);
}
