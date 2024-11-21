package com.example.springboot.controller;

import com.example.springboot.dto.ProductDto;
import com.example.springboot.model.ProductModel;
import com.example.springboot.repository.ProductRepository;
import com.example.springboot.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("product")
public class ProductController {

    private final ProductRepository productRepository;

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> save(@RequestBody @Valid ProductDto productDto) {
          return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productDto));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll() {
        return ResponseEntity.ok(this.productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable(value="id") UUID id) {
        return ResponseEntity.ok(this.productService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@RequestBody @Valid ProductDto productDto,
                                                 @PathVariable(value="id") UUID id) {
        Optional<ProductModel> productO = productRepository.findById(id);
        if (productO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        var productModel = productO.get();
        BeanUtils.copyProperties(productDto, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value="id") UUID id) {
        Optional<ProductModel> productO = productRepository.findById(id);
        if (productO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        productRepository.delete(productO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted sucessfully");
    }
}
