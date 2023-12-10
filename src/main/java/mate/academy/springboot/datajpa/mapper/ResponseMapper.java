package mate.academy.springboot.datajpa.mapper;

public interface ResponseMapper<D, M> {
    D toDto(M m);
}
