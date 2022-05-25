package mate.academy.springboot.datajpa.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.springboot.datajpa.dto.ProductDto;
import mate.academy.springboot.datajpa.facade.ProductFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductFacade facade;

    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto dto) {
        return new ResponseEntity<>(facade.create(dto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> get(@PathVariable Long id) {
        return new ResponseEntity<>(facade.findById(id), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ProductDto> update(@PathVariable Long id, @RequestBody ProductDto dto) {
        return new ResponseEntity<>(facade.update(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> delete(@PathVariable Long id) {
        facade.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/price/between")
    public ResponseEntity<List<ProductDto>> getByPriceBetween(@RequestParam Integer lowerPrice,
                                                              @RequestParam Integer higherPrice) {
        return new ResponseEntity<>(
            facade.getByPriceBetween(lowerPrice, higherPrice), HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<ProductDto>> getByCategories(
            @RequestParam List<String> categories) {
        return new ResponseEntity<>(facade.getByCategories(categories), HttpStatus.OK);
    }
}
