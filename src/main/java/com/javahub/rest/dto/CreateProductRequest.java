package com.javahub.rest.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateProductRequest(
    @NotBlank(message = "Product name must not be blank")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    String name,

    @NotBlank(message = "SKU must not be blank")
    String sku,

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than zero")
    Double price
) {}
