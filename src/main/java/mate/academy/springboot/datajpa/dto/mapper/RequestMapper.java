package mate.academy.springboot.datajpa.dto.mapper;

public interface RequestMapper<D, T> {
    T toModel(D d);
}
