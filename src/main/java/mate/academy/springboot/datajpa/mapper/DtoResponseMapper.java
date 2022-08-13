package mate.academy.springboot.datajpa.mapper;

public interface DtoResponseMapper<D, M> {
    D toDto(M model);
}
