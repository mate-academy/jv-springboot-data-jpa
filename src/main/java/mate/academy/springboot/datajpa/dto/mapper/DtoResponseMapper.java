package mate.academy.springboot.datajpa.dto.mapper;

public interface DtoResponseMapper<D,C> {
    D toDto(C object);
}
