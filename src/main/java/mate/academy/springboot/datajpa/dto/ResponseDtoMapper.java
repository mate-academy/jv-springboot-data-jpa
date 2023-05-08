package mate.academy.springboot.datajpa.dto;

public interface ResponseDtoMapper<D, M> {
    D toDto(M model);
}
