package mate.academy.springboot.datajpa.component.mapper;

public interface ResponseDtoMapper<M, D> {
    M mapToDto(D model);
}
