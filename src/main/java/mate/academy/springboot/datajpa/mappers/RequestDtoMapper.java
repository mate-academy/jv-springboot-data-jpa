package mate.academy.springboot.datajpa.mappers;

public interface RequestDtoMapper<M, D> {
    M toModel(D dto);
}
