package mate.academy.springboot.datajpa.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.mapper.CategoryMapper;
import mate.academy.springboot.datajpa.dto.mapper.ProductMapper;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.models.Product;
import mate.academy.springboot.datajpa.services.CategoryService;
import mate.academy.springboot.datajpa.services.ProductService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductControllers {
    private final ProductService productService;
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;
    private final CategoryService categoryService;

    public ProductControllers(ProductService productService,
                              ProductMapper mapper,
                              CategoryMapper categoryMapper,
                              CategoryService categoryService) {
        this.productService = productService;
        this.productMapper = mapper;
        this.categoryMapper = categoryMapper;
        this.categoryService = categoryService;
    }

    @GetMapping("/inject")
    public void create() {
        Product product = new Product();
        product.setTitle("cheese");
        product.setPrice(BigDecimal.valueOf(100));
        product.setCategory(categoryService.findById(1L));
        productService.save(product);

        Product product1 = new Product();
        product1.setTitle("apple");
        product1.setPrice(BigDecimal.valueOf(30));
        product1.setCategory(categoryService.findById(2L));
        productService.save(product1);

        Product product2 = new Product();
        product2.setTitle("tomato");
        product2.setPrice(BigDecimal.valueOf(60));
        product2.setCategory(categoryService.findById(3L));
        productService.save(product2);
    }

    @PostMapping
    public ProductResponseDto create(ProductRequestDto productRequestDto) {
        return productMapper.toResponseDto(productService.save(productMapper
                .toModel(productRequestDto)));
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return productMapper.toResponseDto(productService.findById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                       ProductRequestDto requestDto) {
        Product product = productMapper.toModel(requestDto);
        product.setId(id);
        return productMapper.toResponseDto(productService.update(product));
    }

    @GetMapping("/findByPrice")
    public List<ProductResponseDto> getAllByPrice(BigDecimal from, BigDecimal to) {
        return productService.findAllByPricePriceBetween(from, to)
                .stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/findBy")
    public List<ProductResponseDto> findAllByCategories(@RequestParam
                                                        Map<String, String> params) {
        return productService.findAll(params)
                .stream()
                .map(productMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
