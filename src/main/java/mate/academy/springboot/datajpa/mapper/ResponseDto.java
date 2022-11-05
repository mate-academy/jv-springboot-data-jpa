package mate.academy.springboot.datajpa.mapper;

public interface ResponseDto<D, M> {
    D toDto(M entity);
}
