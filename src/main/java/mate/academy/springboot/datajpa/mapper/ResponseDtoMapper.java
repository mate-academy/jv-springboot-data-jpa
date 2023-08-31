package mate.academy.springboot.datajpa.mapper;

public interface ResponseDtoMapper<S, M> {
    S mapToDto(M t);
}
