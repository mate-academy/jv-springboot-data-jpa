package mate.academy.springboot.datajpa.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;
import mate.academy.springboot.datajpa.dto.product.ProductRequestDto;
import mate.academy.springboot.datajpa.dto.product.ProductResponseDto;
import mate.academy.springboot.datajpa.dto.mapper.ProductMapper;
import mate.academy.springboot.datajpa.repository.ProductRepo;
import mate.academy.springboot.datajpa.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepo productRepo, ProductMapper productMapper) {
        this.productRepo = productRepo;
        this.productMapper = productMapper;
    }

    @Override
    public ProductResponseDto save(ProductRequestDto dto) {
        Product prod = productRepo.save(productMapper.toModel(dto));
        return productMapper.toRespDto(prod);
    }

    @Override
    public ProductResponseDto get(Long id) {
        try {
            Product product = productRepo.getReferenceById(id);
            return productMapper.toRespDto(product);
        } catch (RuntimeException e) {
            throw new NoSuchElementException("There is no element with ID " + id, e);
        }
    }

    @Override
    public List<ProductResponseDto> getAll() {
        return productRepo.findAll().stream()
                .map(productMapper::toRespDto)
                .collect(Collectors.toList());
    }

    @Override
    public void remove(Long id) {
        productRepo.deleteById(id);
    }

    @Override
    public List<ProductResponseDto> getAllInCategories(Set<String> categories) {
        List<Product> list = productRepo.findAllByCategoryIn(categories);
        return list.stream()
                .map(productMapper::toRespDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDto> findByPriceBetween(BigDecimal from, BigDecimal to) {
        return productRepo.findByPriceBetween(from, to).stream()
                .map(productMapper::toRespDto)
                .collect(Collectors.toList());
    }

}
