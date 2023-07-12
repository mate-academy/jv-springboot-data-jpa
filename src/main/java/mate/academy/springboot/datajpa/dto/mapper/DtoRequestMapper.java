package mate.academy.springboot.datajpa.dto.mapper;

public interface DtoRequestMapper<D, C> {
    C fromDto(D dto);
}
