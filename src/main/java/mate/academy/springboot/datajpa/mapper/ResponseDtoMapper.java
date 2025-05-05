package mate.academy.springboot.datajpa.mapper;

public interface ResponseDtoMapper<D, M> {
    D toDto(M entity);
}
