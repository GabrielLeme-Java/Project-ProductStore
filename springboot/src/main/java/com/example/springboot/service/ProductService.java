package com.example.springboot.service;

import com.example.springboot.dto.ProductDto;
import com.example.springboot.model.ProductModel;
import com.example.springboot.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    public ProductDto save(ProductDto productDto) {
        ProductModel model = this.modelMapper.map(productDto, ProductModel.class);
        ProductModel modelReturn = this.productRepository.save(model);
        ProductDto map = this.modelMapper.map(modelReturn, ProductDto.class);
        return map;
    }

    public List<ProductDto> getAll() {
        List<ProductModel> listaPrecisaSerRetornada = this.productRepository.findAll();
        List<ProductDto> listaDtoVaziaAserRetornada = new ArrayList<>();

        for (ProductModel p : listaPrecisaSerRetornada){
            ProductDto map = this.modelMapper.map(p, ProductDto.class);
            listaDtoVaziaAserRetornada.add(map);
        }
        return listaDtoVaziaAserRetornada;
    }

    public ProductDto findById(UUID id) {
        Optional<ProductModel> byId = this.productRepository.findById(id);
        if (byId.isEmpty()){
            throw new RuntimeException("Produto não encontrado");
        }
        ProductDto map = this.modelMapper.map(byId, ProductDto.class);
        return map;
    }


    public ProductDto update(ProductDto productDto, UUID id) {
        Optional<ProductModel> byId = this.productRepository.findById(id);
        if (byId.isEmpty()) {
            throw new RuntimeException("Esse produto não existe");
        }
        this.modelMapper.map(productDto, byId);
        ProductModel productModel = this.productRepository.save(byId.get());
        ProductDto productDto1 = this.modelMapper.map(productModel, ProductDto.class);
        return productDto1;


    }

    public void delete(UUID id) {
        Optional<ProductModel> byId = this.productRepository.findById(id);
        if (byId.isEmpty()){
            throw new RuntimeException("Produto não existe");
        }
        this.productRepository.delete(byId.get());
    }










}
