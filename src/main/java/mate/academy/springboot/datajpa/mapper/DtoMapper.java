package mate.academy.springboot.datajpa.mapper;

public interface DtoMapper<R, A, M> {
    M fromDto(R dto);

    A toDto(M object);
}
