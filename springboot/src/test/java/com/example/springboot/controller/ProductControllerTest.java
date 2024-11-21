package com.example.springboot.controller;

import com.example.springboot.dto.ProductDto;
import com.example.springboot.model.ProductModel;
import com.example.springboot.repository.ProductRepository;
import com.example.springboot.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void getAllProducts() {
        UUID uuid = UUID.randomUUID();
        ProductModel productModel = new ProductModel();
        productModel.setIdProduct(uuid);
        productModel.setName("Product Name");
        productModel.setValue(new BigDecimal("12.34"));

        ProductModel productMode2l = new ProductModel();
        productModel.setIdProduct(uuid);
        productModel.setName("Product Name");
        productModel.setValue(new BigDecimal("12.34"));

        List<ProductModel> products = new ArrayList<>();

        products.add(productModel);
        products.add(productMode2l);

        when(productRepository.findAll()).thenReturn(products);
        ProductController productController = new ProductController(productRepository, productService);

        ResponseEntity<List<ProductDto>> allProducts = productController.getAll();

       assertEquals(2, Objects.requireNonNull(allProducts.getBody()).size());
    }
}