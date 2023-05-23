package mate.academy.springboot.datajpa.service.mapper;

public interface Mapper<M, D, F> {
    M toModel(D requestDto);

    F toDto(M model);
}
