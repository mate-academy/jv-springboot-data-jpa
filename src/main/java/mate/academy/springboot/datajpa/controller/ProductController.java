package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.mapper.impl.ProductDtoMapper;
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
    private final ProductDtoMapper productDtoMapper;

    public ProductController(ProductService productService,
                             CategoryService categoryService, ProductDtoMapper productDtoMapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productDtoMapper = productDtoMapper;
    }

    @GetMapping("/inject")
    public void saveProduct() {
        Product s21 = new Product();
        s21.setTitle("S21 Samsung");
        s21.setPrice(BigDecimal.valueOf(900));
        s21.setCategory(categoryService.get(1L));
        productService.save(s21);

        Product a12 = new Product();
        a12.setTitle("Apple 12");
        a12.setPrice(BigDecimal.valueOf(800));
        a12.setCategory(categoryService.get(1L));
        productService.save(a12);

        Product nvidia = new Product();
        nvidia.setTitle("3090");
        nvidia.setPrice(BigDecimal.valueOf(1600));
        nvidia.setCategory(categoryService.get(2L));
        productService.save(nvidia);

        Product nvidia2 = new Product();
        nvidia2.setTitle("3080");
        nvidia2.setPrice(BigDecimal.valueOf(800));
        nvidia2.setCategory(categoryService.get(2L));
        productService.save(nvidia);
    }

    @PostMapping
    public ProductResponseDto createProduct(ProductRequestDto dto) {
        return productDtoMapper.toDto(productService.save(productDtoMapper.toModel(dto)));
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable Long id) {
        return productDtoMapper.toDto(productService.get(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@PathVariable Long id,
                                     @RequestBody ProductRequestDto requestDto) {
        Product product = productDtoMapper.toModel(requestDto);
        product.setId(id);
        productService.update(product);
        return productDtoMapper.toDto(product);
    }

    @GetMapping("/findAll/betweenPrice")
    public List<ProductResponseDto> findAllProductBetweenPrices(@RequestParam Long from,
                                                                @RequestParam Long to) {
        return productService.findAllByPriceBetween(BigDecimal.valueOf(from),
                        BigDecimal.valueOf(to)).stream()
                .map(productDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/findAll")
    public List<ProductResponseDto> findAllInCategories(@RequestParam List<Long> categories) {
        List<ProductResponseDto> responseDtos = new ArrayList<>();
        List<Product> products = productService.findAll();
        for (Long id : categories) {
            Category category = categoryService.get(id);
            for (Product product : products) {
                if (product.getCategory().equals(category)) {
                    responseDtos.add(productDtoMapper.toDto(product));
                }
            }
        }
        return responseDtos;
    }
}
