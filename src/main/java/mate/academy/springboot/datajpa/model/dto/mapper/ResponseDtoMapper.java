package mate.academy.springboot.datajpa.model.dto.mapper;

public interface ResponseDtoMapper<D, M> {
    D toDto(M model);
}
