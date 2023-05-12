package mate.academy.springboot.datajpa.controller;

import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final ProductService productService;
    private final CategoryService categoryService;

    public DataInitializer(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PostConstruct
    public void inject() {
        Category telephoneCategory = new Category();
        telephoneCategory.setName("Telephone");
        Category tvCategory = new Category();
        tvCategory.setName("TV");
        Category notebookCategory = new Category();
        notebookCategory.setName("Notebook");
        categoryService.save(telephoneCategory);
        categoryService.save(tvCategory);
        categoryService.save(notebookCategory);
        Product firstProduct = new Product();
        firstProduct.setTitle("Iphone 11");
        firstProduct.setPrice(BigDecimal.valueOf(1100));
        firstProduct.setCategory(telephoneCategory);
        Product secondProduct = new Product();
        secondProduct.setTitle("Samsung Smart TV");
        secondProduct.setCategory(tvCategory);
        secondProduct.setPrice(BigDecimal.valueOf(1500));
        Product thirdProduct = new Product();
        thirdProduct.setTitle("ASUS k53s");
        thirdProduct.setCategory(notebookCategory);
        thirdProduct.setPrice(BigDecimal.valueOf(900));
        productService.save(firstProduct);
        productService.save(secondProduct);
        productService.save(thirdProduct);
    }
}
