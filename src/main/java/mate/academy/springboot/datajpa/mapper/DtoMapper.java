package mate.academy.springboot.datajpa.mapper;

public interface DtoMapper<M, Q, S> extends RequestDtoMapper<M, Q>, ResponseDtoMapper<M, S> {
}
