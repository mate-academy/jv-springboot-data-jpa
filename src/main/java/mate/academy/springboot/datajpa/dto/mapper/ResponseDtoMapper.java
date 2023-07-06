package mate.academy.springboot.datajpa.dto.mapper;

public interface ResponseDtoMapper <M, D> {
    D mapToDto(M model);
}
