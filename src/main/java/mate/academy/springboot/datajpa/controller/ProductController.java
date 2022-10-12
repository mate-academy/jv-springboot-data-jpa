package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.mapper.DtoRequestMapper;
import mate.academy.springboot.datajpa.mapper.DtoResponseMapper;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.model.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.model.dto.ProductResponseDto;
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
    private final CategoryService categoryService;
    private final ProductService productService;
    private final DtoRequestMapper<Product, ProductRequestDto> requestMapper;
    private final DtoResponseMapper<Product, ProductResponseDto> responseMapper;

    public ProductController(CategoryService categoryService, ProductService productService,
                             DtoRequestMapper<Product, ProductRequestDto> requestMapper,
                             DtoResponseMapper<Product, ProductResponseDto> responseMapper) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.requestMapper = requestMapper;
        this.responseMapper = responseMapper;
    }

    @GetMapping("/inject")
    public String inject() {
        Product iphone = new Product();
        iphone.setTitle("iphone 12");
        iphone.setPrice(BigDecimal.valueOf(1299));
        iphone.setCategory(categoryService.get(1L));
        productService.save(iphone);

        Product ipad = new Product();
        ipad.setTitle("ipad Air 2");
        ipad.setPrice(BigDecimal.valueOf(1699));
        ipad.setCategory(categoryService.get(2L));
        productService.save(ipad);
        return "Products were injected!";
    }

    @PostMapping
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto) {
        Product product = requestMapper.toModel(productRequestDto);
        Product productInDB = productService.save(product);
        return responseMapper.toDto(productInDB);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProduct(@PathVariable Long id) {
        Product product = productService.get(id);
        return responseMapper.toDto(product);
    }

    @PutMapping("/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id,
                                            @RequestBody ProductRequestDto requestDto) {
        Product product = requestMapper.toModel(requestDto);
        product.setId(id);
        return responseMapper.toDto(productService.update(product));
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return "Product by id: " + id + " was deleted!";
    }

    @GetMapping("/by-price")
    public List<ProductResponseDto> getAllProductsByPriceBetween(@RequestParam BigDecimal from,
                                                                 @RequestParam BigDecimal to) {
        return productService.findAllByPriceBetween(from, to)
                .stream().map(responseMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<ProductResponseDto> getAllProductsByCategories(
            @RequestParam List<String> categories) {
        return productService.findAllByCategories(categories).stream()
                .map(responseMapper::toDto)
                .collect(Collectors.toList());
    }
}
