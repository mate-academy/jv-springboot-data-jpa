package mate.academy.springboot.datajpa.conroller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.request.CategoryRequestDto;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.mapper.CategoryMapper;
import mate.academy.springboot.datajpa.mapper.ProductMapper;
import mate.academy.springboot.datajpa.model.Product;
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
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;
    private final ProductService productService;

    public ProductController(ProductMapper productMapper,
                             CategoryMapper categoryMapper, ProductService productService) {
        this.productMapper = productMapper;
        this.categoryMapper = categoryMapper;
        this.productService = productService;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.create(productMapper.toModel(productRequestDto));
        return productMapper.toDto(product);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        return productMapper.toDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping
    public ProductResponseDto update(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productService.update(productMapper.toModel(productRequestDto));
        return productMapper.toDto(product);
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> getAllByPrice(@RequestParam BigDecimal from,
                                                  @RequestParam BigDecimal to) {
        List<Product> allBetween = productService
                .getAllBetween(from, to);
        List<ProductResponseDto> collect = allBetween.stream()
                .map(m -> productMapper.toDto(m))
                .collect(Collectors.toList());
        return collect;
    }

    @GetMapping("/by-category")
    public List<ProductResponseDto> getAllByCategories(
            @RequestParam CategoryRequestDto categoryRequestDto) {
        List<Product> allInCategories = productService
                .getAllInCategories(categoryMapper.toModel(categoryRequestDto));
        List<ProductResponseDto> collect = allInCategories.stream()
                .map(s -> productMapper.toDto(s))
                .collect(Collectors.toList());
        return collect;
    }
}
