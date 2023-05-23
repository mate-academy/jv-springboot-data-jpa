package mate.academy.springboot.datajpa.service.mapper;

public interface DtoMapper<M, R, S> {
    M mapToModel(R requestDto);

    S mapToDto(M model);
}
