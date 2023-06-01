package mate.academy.springboot.datajpa.dto.maper;

public interface DtoMapper<D,T,V> {
    D mapToModel(T dto);

    V modelToMap(D t);
}
