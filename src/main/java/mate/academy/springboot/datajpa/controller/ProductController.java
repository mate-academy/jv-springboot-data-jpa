package mate.academy.springboot.datajpa.controller;

import com.sun.istack.FinalArrayList;
import mate.academy.springboot.datajpa.dto.request.RequestProductDto;
import mate.academy.springboot.datajpa.dto.response.ResponseProductDto;
import mate.academy.springboot.datajpa.mapper.ProductMapper;
import mate.academy.springboot.datajpa.mapper.RequestDtoMapper;
import mate.academy.springboot.datajpa.mapper.ResponseDtoMapper;
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

    public ProductController(ProductService productService,
                             ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ResponseProductDto create(@RequestBody RequestProductDto requestDto) {
        Product product = productService.save(
                productMapper.mapToModel(requestDto));
        return productMapper.mapToDto(product);
    }

    @GetMapping("/{id}")
    public ResponseProductDto getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        return productMapper.mapToDto(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping
    public ResponseProductDto update(@PathVariable Long id,
                                     @RequestParam RequestProductDto requestProductDto) {
        Product product = productMapper.mapToModel(requestProductDto);
        product.setId(id);
        return productMapper.mapToDto(product);
    }


}
