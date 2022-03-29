package mate.academy.springboot.datajpa.service;

import mate.academy.springboot.datajpa.model.Product;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class ProductRatingSort implements Comparator<Product> {
    @Override
    public int compare(Product product, Product t1) {
        return product.getId() > t1.getId() ? 1 : 0;
    }
}
