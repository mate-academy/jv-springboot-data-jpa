package mate.academy.springboot.datajpa.dto.mapper;

public interface DtoMapper<RQ, RS, M> {
    M mapToModel(RQ requestDto);
    RS mapToDto(M model);
}
