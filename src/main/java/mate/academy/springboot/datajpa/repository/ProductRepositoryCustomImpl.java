package mate.academy.springboot.datajpa.repository;

import java.util.List;
import java.util.Map;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Product> findAll(Map<String, String> params) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        Predicate predicate = cb.conjunction();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            CriteriaBuilder.In<Object> in = cb.in(root.get(entry.getKey()));
            for (String value : entry.getValue().split(",")) {
                in.value(value);
            }
            predicate = cb.and(predicate, in);
        }
        query.where(predicate);
        return em.createQuery(query).getResultList();
    }
}
