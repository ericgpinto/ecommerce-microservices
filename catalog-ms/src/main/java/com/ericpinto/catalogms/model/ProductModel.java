package com.ericpinto.catalogms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductModel {

    @Id
    private String id;
    private String name;
    private String description;
    private String avatarUrl;
    private Integer price;
    @DBRef(lazy = true)
    private CategoryModel categoryModel;
}
