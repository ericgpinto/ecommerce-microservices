package com.ericpinto.catalogms.api.dto;

import com.ericpinto.catalogms.model.CategoryModel;

public record ProductDetailsDTO(String name, String description, String avatarUrl, CategoryModel category, Integer price) {
}
