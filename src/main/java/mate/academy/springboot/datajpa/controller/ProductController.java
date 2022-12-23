package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.request.ProductResponseDto;
import mate.academy.springboot.datajpa.dto.response.ProductRequestDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mappers.ProductMapper;
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
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final CategoryService categoryService;

    public ProductController(ProductService productService,
                             ProductMapper productMapper,
                             CategoryService categoryService) {
        this.productService = productService;
        this.productMapper = productMapper;
        this.categoryService = categoryService;
    }

    @PostMapping()
    public ProductResponseDto create(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.save(productMapper.mapToModel(productRequestDto));
        return productMapper.mapToDto(product);
    }

    @GetMapping("/inject")
    public String saveProducts() {
        Category category1 = new Category();
        category1.setName("Phone");
        categoryService.save(category1);

        Category category2 = new Category();
        category2.setName("TV");
        categoryService.save(category2);

        Product product1 = new Product();
        product1.setPrice(BigDecimal.valueOf(1999));
        product1.setTitle("Samsung Galaxy a20");
        product1.setCategory(category1);
        productService.save(product1);

        Product product2 = new Product();
        product2.setPrice(BigDecimal.valueOf(3000));
        product2.setTitle("Samsung S19");
        product2.setCategory(category1);
        productService.save(product2);

        Product product3 = new Product();
        product3.setPrice(BigDecimal.valueOf(4000));
        product3.setTitle("iPhone 14");
        product3.setCategory(category1);
        productService.save(product3);

        Product product4 = new Product();
        product4.setPrice(BigDecimal.valueOf(3000));
        product4.setTitle("SAMSUNG LED ULTRA 999");
        product4.setCategory(category2);
        productService.save(product4);

        return "Done!";
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto productRequestDto) {
        Product product = productMapper.mapToModel(productRequestDto);
        product.setId(id);
        Product save = productService.save(product);
        return productMapper.mapToDto(save);
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        Product product = productService.get(id);
        return productMapper.mapToDto(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping("/byPrice")
    public List<ProductResponseDto> findAllByPriceBetween(@RequestParam BigDecimal from,
                                                          @RequestParam BigDecimal to) {
        List<Product> allByPriceBetween = productService.findAllByPriceBetween(from, to);
        return allByPriceBetween.stream().map(productMapper::mapToDto).collect(Collectors.toList());
    }

    @GetMapping
    public List<ProductResponseDto> findAll(@RequestParam Map<String,String> param) {
        return productService.findAll(param).stream()
                .map(productMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
