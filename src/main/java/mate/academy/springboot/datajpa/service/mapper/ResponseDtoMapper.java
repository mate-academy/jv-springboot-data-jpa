package mate.academy.springboot.datajpa.service.mapper;

public interface ResponseDtoMapper<M, R> {
    R mapToDto(M model);
}
