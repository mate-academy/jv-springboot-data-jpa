package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.ProductResponseDto;
import mate.academy.springboot.datajpa.mappers.ProductMapper;
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
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;

    @GetMapping("/inject")
    public void inject() {
        for (int i = 0; i < 200; i++) {
            Category category = new Category();
            category.setName("name" + i);
            categoryService.add(category);

            Product product = new Product();
            product.setTitle("title " + i);
            product.setPrice(new BigDecimal(i * 100));
            product.setCategory(category);
            productService.add(product);
        }

    }

    @PostMapping
    public ProductResponseDto create(@RequestBody ProductRequestDto productRequestDto) {
        Product product = productMapper.toModel(productRequestDto);
        productService.add(product);
        return productMapper.toDto(product);
    }

    @PutMapping(value = "/{id}")
    public ProductResponseDto update(
            @RequestBody ProductRequestDto productRequestDto,
            @PathVariable Long id) {
        Product product = productService.getById(id);
        Product updatedProduct = productMapper.toModel(productRequestDto);
        updatedProduct.setId(id);
        productService.update(product);
        return productMapper.toDto(product);
    }

    @GetMapping(value = "/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        Product product = productService.getById(id);
        if (product != null) {
            productService.delete(product);
        }
    }

    @GetMapping
    public List<ProductResponseDto> getAll(@RequestParam Map<String, String> params) {
        List<Product> products = productService.getAll(params);
        return products.stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }
}
