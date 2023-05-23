package mate.academy.springboot.datajpa.dto.mapper;

public interface DtoMapper<M, R, D> {
    M toModel(R requestDto);

    D toDto(M model);
}
