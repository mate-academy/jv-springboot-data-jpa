package mate.academy.springboot.datajpa.mapper;

public interface DtoRequestMapper<T, K> {
    T toModel(K dto);
}
