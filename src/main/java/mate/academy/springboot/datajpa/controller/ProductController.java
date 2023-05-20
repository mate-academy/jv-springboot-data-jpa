package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.springboot.datajpa.dto.request.RequestProductDto;
import mate.academy.springboot.datajpa.dto.response.ResponseProductDto;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import mate.academy.springboot.datajpa.service.mapper.DtoMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final DtoMapper<Product, RequestProductDto, ResponseProductDto> productMapper;

    public ProductController(
            ProductService productService,
            DtoMapper<Product, RequestProductDto, ResponseProductDto> productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ResponseProductDto create(@RequestBody RequestProductDto productDto) {
        Product product = productMapper.toModel(productDto);
        return productMapper.toDto(productService.save(product));
    }

    @GetMapping("/{id}")
    public ResponseProductDto getById(@PathVariable Long id) {
        return productMapper.toDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseProductDto update(@PathVariable Long id,
                                     @RequestBody RequestProductDto productDto ) {
        Product product = productMapper.toModel(productDto);
        product.setId(id);
        return productMapper.toDto(productService.update(product));
    }

    @GetMapping("/inject")
    public void saveProduct() {
        Product product = new Product();
        product.setTitle("Lenovo");
        product.setPrice(BigDecimal.valueOf(1000));

        productService.save(product);
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.findAll();
    }
}
