package mate.academy.springboot.datajpa.mapper;

public interface Mapper<S, U, V> {
    U toDto(V model);

    V toModel(S requestDto);
}
