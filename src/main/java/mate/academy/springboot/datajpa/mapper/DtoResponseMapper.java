package mate.academy.springboot.datajpa.mapper;

public interface DtoResponseMapper<D, C> {
    D toDto(C object);
}
