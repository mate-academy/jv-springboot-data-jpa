package mate.academy.springboot.datajpa.service.mapper;

public interface DtoMapper<R, S, M> {
    M toModel(R requestDto);

    S toDto(M model);
}
