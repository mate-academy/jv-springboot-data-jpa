package mate.academy.springboot.datajpa.mapper;

public interface DtoMapper<Q, S, M> extends RequestDtoMapper<Q, M>, ResponseDtoMapper<S, M> {
}
