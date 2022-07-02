package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.request.RequestProduct;
import mate.academy.springboot.datajpa.dto.response.ResponseProduct;
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
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ResponseProduct add(@RequestBody RequestProduct request) {
        return productMapper.toDto(productService.add(productMapper.fromDto(request)));
    }

    @GetMapping("/{id}")
    public ResponseProduct getById(@PathVariable Long id) {
        return productMapper.toDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseProduct update(@PathVariable Long id,
            @RequestBody RequestProduct request) {
        Product product = productMapper.fromDto(request);
        product.setId(id);
        return productMapper.toDto(productService.update(product));
    }

    @GetMapping("/by-price")
    public List<ResponseProduct> getAllByPriceBetween(@RequestParam BigDecimal from,
                                                      BigDecimal to) {
        return productService.getAllPriceBetween(from, to).stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-categories")
    public List<ResponseProduct> getAllByCategories(@RequestParam List<Long> categoryIds) {
        return productService.findAllByCategoryIn(categoryIds)
                .stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }
}
