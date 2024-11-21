package com.example.springboot.service;

import com.example.springboot.dto.ProductDto;
import com.example.springboot.model.ProductModel;
import com.example.springboot.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    public ProductDto save(ProductDto productDto) {
        return this.modelMapper.map(
                this.productRepository.save(
                        this.modelMapper.map(productDto, ProductModel.class)),
                ProductDto.class);
    }

    public List<ProductDto> getAll() {
        return this.productRepository.findAll()
                .stream().map(p -> this.modelMapper.map(p, ProductDto.class)).toList();

    }

    public ProductDto findById(UUID id) {
        return this.modelMapper.map(this.productRepository.findById(id), ProductDto.class);
    }


    public Object update(ProductDto productDto, UUID id) {
        return null;
    }

    public Object delete(UUID id) {
        return null;
    }
}
