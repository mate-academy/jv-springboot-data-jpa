package mate.academy.springboot.datajpa.mapper;

public interface DtoRequestMapper<D, T> {
    T fromDto(D d);
}
