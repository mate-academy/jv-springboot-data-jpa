package mate.academy.springboot.datajpa.mappers;

public interface ResponseDtoMapper<M, D> {
    D toDto(M model);
}
