package mate.academy.springboot.datajpa.dto.mapper;

public interface DtoMapper<M, D, R> {
    D toDto(M model);

    M toModel(R dto);

}
