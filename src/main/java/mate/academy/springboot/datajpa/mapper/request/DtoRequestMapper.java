package mate.academy.springboot.datajpa.mapper.request;

public interface DtoRequestMapper<D, M> {
    M toModel(D dto);
}
