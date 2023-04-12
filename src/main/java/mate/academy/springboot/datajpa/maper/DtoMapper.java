package mate.academy.springboot.datajpa.maper;

public interface DtoMapper<M, R, D> {
    M toModel(R requestDto);
    D toDto(M model);
}
