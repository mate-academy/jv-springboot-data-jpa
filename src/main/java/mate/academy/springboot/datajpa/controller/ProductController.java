package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.dto.mapper.ProductMapper;
import mate.academy.springboot.datajpa.model.Category;
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
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService,
                             CategoryService categoryService,
                             ProductMapper productMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productMapper = productMapper;
    }

    @GetMapping("/inject")
    public void saveProducts() {
        Category categoryPhones = new Category();
        categoryPhones.setName("Phones");
        categoryService.save(categoryPhones);

        Product iphoneX = new Product();
        iphoneX.setTitle("iPhone X");
        iphoneX.setPrice(BigDecimal.valueOf(990));
        iphoneX.setCategory(categoryPhones);
        productService.save(iphoneX);

        Product iphone7 = new Product();
        iphone7.setTitle("iPhone 7");
        iphone7.setPrice(BigDecimal.valueOf(690));
        iphone7.setCategory(categoryPhones);
        productService.save(iphone7);

        Category categoryCar = new Category();
        categoryCar.setName("Phones");
        categoryService.save(categoryCar);

        Product bmwX5 = new Product();
        bmwX5.setTitle("BMW X5");
        bmwX5.setPrice(BigDecimal.valueOf(50000));
        bmwX5.setCategory(categoryCar);
        productService.save(bmwX5);

        Product bmwX6 = new Product();
        bmwX6.setTitle("BMW X6");
        bmwX6.setPrice(BigDecimal.valueOf(60000));
        bmwX6.setCategory(categoryCar);
        productService.save(bmwX6);

        productService.findAll().forEach(System.out::println);
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.save(productMapper.toModel(productRequestDto));
        return productMapper.toResponseDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return productMapper.toResponseDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto productRequestDto) {
        Product product = productMapper.toModel(productRequestDto);
        product.setId(id);
        return productMapper.toResponseDto(productService.save(product));
    }

    @GetMapping("/price-between")
    public List<ProductResponseDto> findAllByPriceBetween(@RequestParam BigDecimal priceFrom,
                                               @RequestParam BigDecimal priceTo) {
        return productService.findAllByPriceBetween(priceFrom, priceTo)
                .stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/category-in")
    public List<ProductResponseDto> findAllByCategoryIn(@RequestParam Map<String, String> params) {
        return productService.findAll(params)
                .stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
