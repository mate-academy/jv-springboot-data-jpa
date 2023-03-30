package mate.academy.springboot.datajpa.dto.mapper;

public interface ResponseDtoMapper<D, M> {
    D toDto(M model);
}
