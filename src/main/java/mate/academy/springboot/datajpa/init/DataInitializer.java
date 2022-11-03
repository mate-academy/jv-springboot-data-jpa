package mate.academy.springboot.datajpa.init;

import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final CategoryService categoryService;
    private final ProductService productService;

    public DataInitializer(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @PostConstruct
    public void inject() {
        Category clothes = new Category();
        clothes.setName("Clothes");
        categoryService.save(clothes);
        Category food = new Category();
        food.setName("Food");
        categoryService.save(food);
        Category jewelry = new Category();
        jewelry.setName("Jewelry");
        categoryService.save(jewelry);
        Category pets = new Category();
        pets.setName("Pets");
        categoryService.save(pets);
        Category books = new Category();
        books.setName("Books");
        categoryService.save(books);
        Category electronics = new Category();
        electronics.setName("Electronics");
        categoryService.save(electronics);

        for (int i = 1; i < 6; i++) {
            Product book = new Product();
            book.setCategory(books);
            book.setPrice(BigDecimal.valueOf(10 * i));
            book.setTitle("Book " + i);
            productService.save(book);

            Product phone = new Product();
            phone.setCategory(electronics);
            phone.setPrice(BigDecimal.valueOf(100 * i));
            phone.setTitle("Phone " + i);
            productService.save(phone);
        }
    }
}
