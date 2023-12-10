package mate.academy.springboot.datajpa.mapper;

public interface RequestMapper<D, M> {
    M toModel(D d);
}
