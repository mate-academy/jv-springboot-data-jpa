package mate.academy.springboot.datajpa.dto.mapper;

public interface DtoMapper<R, S, M> {
    M mapToModel(R requestDto);

    S mapToDto(M model);
}
