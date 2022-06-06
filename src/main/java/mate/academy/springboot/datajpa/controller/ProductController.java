package mate.academy.springboot.datajpa.controller;

import mate.academy.springboot.datajpa.dto.RequestProductDto;
import mate.academy.springboot.datajpa.dto.ResponseProductDto;
import mate.academy.springboot.datajpa.dto.mapper.ProductMapper;
import mate.academy.springboot.datajpa.model.Product;
import mate.academy.springboot.datajpa.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService,
                             ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }


    @PostMapping("/create")
    public ResponseProductDto create(RequestProductDto requestProductDto) {
        return productMapper.toDto(productService.create(productMapper
                .toModel(requestProductDto)));

    }

    @GetMapping("/{id}")
    public ResponseProductDto getById(@PathVariable Long id) {
        return productMapper.toDto(productService.getById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseProductDto update(@PathVariable Long id, RequestProductDto requestProductDto) {
        Product product = productMapper.toModel(requestProductDto);
        return productMapper.toDto(productService.update(product));
    }

}
