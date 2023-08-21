package mate.academy.springboot.datajpa.dto.mapper;

public abstract class DtoMapper<M, R, A> {
    public abstract A toDto(M model);

    public abstract M toModel(R requestDto);
}
