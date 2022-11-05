package mate.academy.springboot.datajpa.mapper;

public interface RequestDto<D, M>{
    M toModel(D data);
}
