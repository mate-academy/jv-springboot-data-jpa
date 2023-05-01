package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.dto.mapper.ResponseDtoMapper;
import mate.academy.springboot.datajpa.dto.request.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.response.ProductResponseDto;
import mate.academy.springboot.datajpa.model.Category;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.CategoryService;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final RequestDtoMapper<Product, ProductRequestDto> requestDtoMapper;
    private final ResponseDtoMapper<Product, ProductResponseDto> responseDtoMapper;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService,
                             RequestDtoMapper<Product, ProductRequestDto> requestDtoMapper,
                             ResponseDtoMapper<Product, ProductResponseDto> responseDtoMapper,
                             CategoryService categoryService) {
        this.productService = productService;
        this.requestDtoMapper = requestDtoMapper;
        this.responseDtoMapper = responseDtoMapper;
        this.categoryService = categoryService;
    }

    @PostMapping
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto) {
        return responseDtoMapper.mapToDto(productService.add(
                requestDtoMapper.mapToModel(productRequestDto)));
    }

    @GetMapping("/inject")
    public String inject() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Fish");
        categoryService.add(category);

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("Meat");
        categoryService.add(category2);

        Category category3 = new Category();
        category3.setId(3L);
        category3.setName("Bread");
        categoryService.add(category3);

        Product product1 = new Product();
        product1.setTitle("Shark");
        product1.setPrice(new BigDecimal(150));
        product1.setCategory(categoryService.getById(1L));

        Product product2 = new Product();
        product2.setTitle("Okyn");
        product2.setPrice(new BigDecimal(200));
        product2.setCategory(categoryService.getById(1L));

        Product product3 = new Product();
        product3.setTitle("Dorado");
        product3.setPrice(new BigDecimal(250));
        product3.setCategory(categoryService.getById(1L));

        Product product4 = new Product();
        product4.setTitle("Lamb");
        product4.setPrice(new BigDecimal(100));
        product4.setCategory(categoryService.getById(2L));

        productService.add(product1);
        productService.add(product2);
        productService.add(product3);
        productService.add(product4);
        return "Done!!!!";
    }

    @GetMapping("/{id}")
    public ProductResponseDto get(@PathVariable Long id) {
        return responseDtoMapper.mapToDto(productService.getById(id));
    }

    @GetMapping("/find-by-price")
    public List<ProductResponseDto> getAllByPriceBetween(@RequestParam BigDecimal from,
                                                         @RequestParam BigDecimal to) {
        return productService.getAllByPriceBetween(from, to)
                .stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ProductResponseDto update(@RequestBody ProductRequestDto requestDto,
                                     @PathVariable Long id) {
        Product product = productService.update(id, requestDtoMapper.mapToModel(requestDto));
        return responseDtoMapper.mapToDto(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @GetMapping("/find-by-categories")
    public List<ProductResponseDto> getAllProductsByCategories(
            @RequestParam List<Long> categoriesIds) {
        return productService.findAllByCategories(categoriesIds).stream()
                .map(responseDtoMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
