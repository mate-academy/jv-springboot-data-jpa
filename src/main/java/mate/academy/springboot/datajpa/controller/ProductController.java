package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.request.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.ProductDtoMapperImpl;
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
    private final ProductDtoMapperImpl productDtoMapper;

    public ProductController(ProductService productService, CategoryService categoryService,
                             ProductDtoMapperImpl productDtoMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productDtoMapper = productDtoMapper;
    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setCategory(categoryService.getById(productRequestDto.getCategoryId()));
        product.setTitle(productRequestDto.getTitle());
        product.setPrice(productRequestDto.getPrice());
        return productDtoMapper
                .mapToDto(productService.save(productDtoMapper.mapToModel(productRequestDto)));
    }

    @GetMapping
    public ProductResponseDto findById(@RequestParam Long id) {
        return productDtoMapper.mapToDto(productService.getById(id));
    }

    @DeleteMapping
    public void deleteById(@RequestParam Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto updateById(@PathVariable Long id,
                                         @RequestBody ProductRequestDto productRequestDto) {
        Product product = (productDtoMapper.mapToModel(productRequestDto));
        product.setId(id);
        product.setCategory(categoryService.getById(productRequestDto.getCategoryId()));
        productService.update(product);
        return productDtoMapper.mapToDto(product);
    }

    @GetMapping("/byPriceBetween")
    public List<ProductResponseDto> findAllByPriceBetween(@RequestParam BigDecimal from,
                                           @RequestParam BigDecimal to) {
        return productService.getAllByPriceBetween(from, to).stream()
                .map(productDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/findAll")
    public List<ProductResponseDto> findAll(@RequestParam Map<String, String> params) {
        return productService.findAll(params).stream()
                .map(productDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
