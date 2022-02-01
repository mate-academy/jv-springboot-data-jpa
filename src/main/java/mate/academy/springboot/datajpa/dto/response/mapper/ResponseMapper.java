package mate.academy.springboot.datajpa.dto.response.mapper;

public interface ResponseMapper <R, I>{
    R mapToDto(I model);
}
