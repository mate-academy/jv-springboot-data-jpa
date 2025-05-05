package mate.academy.springboot.datajpa.mapper.response;

public interface DtoResponseMapper<M, D> {
    D toDto(M model);
}
