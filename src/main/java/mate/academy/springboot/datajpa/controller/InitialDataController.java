package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.stream.IntStream;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InitialDataController {
    private final CategoryService categoryService;
    private final ProductService productService;

    public InitialDataController(CategoryService categoryService,
                                 ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/inject")
    public String injectInitialData() {
        Category electronics = new Category();
        electronics.setName("Electronics");
        categoryService.save(electronics);
        Category computers = new Category();
        computers.setName("Computers");
        categoryService.save(computers);
        Category software = new Category();
        software.setName("Software");
        categoryService.save(software);
        Category toys = new Category();
        toys.setName("Toys");
        categoryService.save(toys);
        Category games = new Category();
        games.setName("Games");
        categoryService.save(games);

        IntStream.rangeClosed(1, 5)
                .forEach(i -> {
                    Product toy = new Product();
                    toy.setCategory(toys);
                    toy.setPrice(BigDecimal.valueOf(50L * i));
                    toy.setTitle("Toy " + i);
                    productService.save(toy);
                });

        IntStream.rangeClosed(1, 5)
                .forEach(i -> {
                    Product game = new Product();
                    game.setCategory(games);
                    game.setPrice(BigDecimal.valueOf(150L * i));
                    game.setTitle("Game " + i);
                    productService.save(game);
                });

        return "All data are injected!";
    }
}
