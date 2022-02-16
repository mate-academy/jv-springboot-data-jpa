package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.model.dto.mapper.ProductDtoMapper;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductDtoMapper productDtoMapper;
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductDtoMapper productDtoMapper,
                             ProductService productService,
                             CategoryService categoryService) {
        this.productDtoMapper = productDtoMapper;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/inject")
    public String saveCategories() {
        Category machinery = new Category();
        machinery.setName("MACHINERY");
        categoryService.create(machinery);

        Product lamp = new Product();
        lamp.setCategory(machinery);
        lamp.setPrice(BigDecimal.valueOf(200));
        lamp.setTitle("Lamp for a good light");
        productService.create(lamp);

        Category books = new Category();
        books.setName("BOOK");
        categoryService.create(books);

        Product harryPotterBook = new Product();
        harryPotterBook.setTitle("Harry Potter");
        harryPotterBook.setCategory(books);
        harryPotterBook.setPrice(BigDecimal.valueOf(100));
        productService.create(harryPotterBook);

        return "Product were added";
    }

    @PostMapping("/add")
    public ProductResponseDto  create(@RequestBody ProductRequestDto productRequestDto) {
        return productDtoMapper.toDto(productService.create(productDtoMapper.toModel(productRequestDto)));
    }

    @GetMapping("/{productId}")
    public ProductResponseDto getByID(@PathVariable Long productId){
        return productDtoMapper.toDto(productService.getById(productId));
    }

    @DeleteMapping("/{productId}")
    public void deleteByID(@PathVariable Long productId) {
        productService.deleteById(productId);
    }

    @PutMapping("/{productId}")
    public void update(@PathVariable Long productId,
                       @RequestBody ProductRequestDto productRequestDto) {
        productService.update(productId,
                productDtoMapper.toModel(productRequestDto));
    }

    @GetMapping("/price")
    public List<ProductResponseDto> getProductsByPriceBetween(@RequestParam BigDecimal from,
                                            @RequestParam BigDecimal to) {
       return productService.findAllByPriceBetween(from, to).stream()
                .map(productDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/getAll")
    public List<ProductResponseDto> getProductsByCategories(
            @RequestParam Map<String, String> params) {
        return productService.findAllByCategories(params).stream()
                .map(productDtoMapper::toDto)
                .collect(Collectors.toList());
    }
}
