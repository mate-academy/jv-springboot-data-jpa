package mate.academy.springboot.datajpa.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.service.mapper.ResponseDtoMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final RequestDtoMapper<ProductRequestDto, Product> productRequestDtoMapper;
    private final ResponseDtoMapper<ProductResponseDto, Product> productResponseDtoMapper;

    public ProductController(ProductService productService,
                             CategoryService categoryService,
                             RequestDtoMapper<ProductRequestDto, Product>
                                     productRequestDtoMapper,
                             ResponseDtoMapper<ProductResponseDto, Product>
                                     productResponseDtoMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productRequestDtoMapper = productRequestDtoMapper;
        this.productResponseDtoMapper = productResponseDtoMapper;
    }

    @GetMapping("/inject")
    public String injectProducts() {
        Product apple = new Product();
        apple.setTitle("apple");
        apple.setPrice(1L);
        apple.setCategory(categoryService.get(1L));
        productService.create(apple);

        Product meat = new Product();
        meat.setTitle("meat");
        meat.setPrice(2L);
        meat.setCategory(categoryService.get(1L));
        productService.create(meat);

        Product spoon = new Product();
        spoon.setTitle("spoon");
        spoon.setPrice(1L);
        spoon.setCategory(categoryService.get(2L));
        productService.create(spoon);

        Product fork = new Product();
        fork.setTitle("fork");
        fork.setPrice(2L);
        fork.setCategory(categoryService.get(2L));
        productService.create(fork);

        return "Success!";
    }

    @GetMapping
    public ProductResponseDto get(@RequestParam Long id) {
        return productResponseDtoMapper.mapToDto(productService.get(id));
    }

    @GetMapping("/by-category")
    public List<ProductResponseDto> getByCategory(@RequestParam(name = "id") Long categoryId) {
        return productService.getByCategory(categoryService.get(categoryId))
                .stream()
                .map(productResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-price-between")
    public List<ProductResponseDto> getByPriceBetween(@RequestParam Long from,
                                                      @RequestParam Long to) {
        return productService.getByPriceBetween(from, to)
                .stream()
                .map(productResponseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto productRequestDto) {
        return productResponseDtoMapper.mapToDto(
                productService.create(
                        productRequestDtoMapper.mapToModel(productRequestDto)));
    }

    @PutMapping
    public ProductResponseDto update(@RequestParam Long id,
            @RequestBody ProductRequestDto productRequestDto) {
        Product product = productRequestDtoMapper.mapToModel(productRequestDto);
        product.setId(id);
        return productResponseDtoMapper.mapToDto(
                productService.update(product));
    }

    @DeleteMapping
    public String delete(@RequestParam Long id) {
        productService.delete(productService.get(id));
        return "Product with id %s has been deleted".formatted(id);
    }
}
