package mate.academy.springboot.datajpa.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.RequestProduct;
import mate.academy.springboot.datajpa.dto.ResponseProduct;
import mate.academy.springboot.datajpa.dto.mapper.ProductMapper;
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
    private final ProductService service;
    private final ProductMapper mapper;

    public ProductController(ProductService service,
                             ProductMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseProduct create(@RequestBody RequestProduct req) {
        return mapper.toResponseProduct(service.save(mapper.toModel(req)));
    }

    @GetMapping("/{id}")
    public ResponseProduct get(@PathVariable Long id) {
        return mapper.toResponseProduct(service.get(id));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        String deletedProduct = service.get(id).getTitle();
        service.delete(id);
        return "Product " + deletedProduct + " was deleted";
    }

    @PutMapping("/{id}")
    public ResponseProduct update(@RequestBody RequestProduct req, @PathVariable Long id) {
        return mapper.toResponseProduct(service.update(mapper.toModel(req), id));
    }

    @GetMapping("/price")
    public List<ResponseProduct> findAllByPriceBetween(@RequestParam BigDecimal from,
                                                       BigDecimal to) {
        return service.findAllByPriceBetween(from, to)
                .stream()
                .map(mapper::toResponseProduct)
                .collect(Collectors.toList());
    }

    @GetMapping("/categories")
    public List<ResponseProduct> findAllByCategories(@RequestParam List<Long> ids) {
        return service.findAllByCategories(ids)
                .stream()
                .map(mapper::toResponseProduct)
                .collect(Collectors.toList());
    }
}
