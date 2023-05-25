package mate.academy.springboot.datajpa.mapper;

public interface DtoMapper<D, M, R> {
    M toModel(R requestDto);

    D toDto(M model);
}
