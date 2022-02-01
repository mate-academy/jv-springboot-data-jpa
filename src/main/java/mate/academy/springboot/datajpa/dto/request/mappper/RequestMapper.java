package mate.academy.springboot.datajpa.dto.request.mappper;

public interface RequestMapper <R, I>{
    R mapToModel(I dto);
}
