package mate.academy.springboot.datajpa.mapper;

public interface DtoRequestMapper<D, T> {
    T toModel(D d);
}
