package com.example.springboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductDto {

    @JsonProperty("UUID")
    private UUID idProduct;
    @NotBlank
    private String name;
    @NotNull
    private BigDecimal value;
}
