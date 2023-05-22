package mate.academy.springboot.datajpa.service.mapper;

public abstract class DtoMapper<M, R, V> {
    public abstract M toModel(R request);

    public abstract V toDto(M model);
}
