package mate.academy.springboot.datajpa.service;

import java.util.List;

public interface GeneralService<S, Q> {
    S save(Q model);

    S get(Long id);

    List<S> getAll();

    void remove(Long id);
}
