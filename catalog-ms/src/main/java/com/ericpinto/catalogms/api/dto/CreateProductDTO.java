package com.ericpinto.catalogms.api.dto;

public record CreateProductDTO(
        String name,
        String description,
        String categoryId,
        String avatarUrl,
        Integer price
        ) {
}
