package mate.academy.springboot.datajpa.facade;

public abstract class BaseFacade<M> {

    public abstract M create(M dto);

    public abstract M findById(Long id);

    public abstract M update(Long id, M dto);

    public abstract void delete(Long id);
}
