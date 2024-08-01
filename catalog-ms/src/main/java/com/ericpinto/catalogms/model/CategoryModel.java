package com.ericpinto.catalogms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CategoryModel {

    @Id
    private String id;
    private String name;

    public static CategoryModel create(String name) {
        CategoryModel model = new CategoryModel();
        model.setName(name);
        return model;
    }

    public void changeName(String name) {
        this.name = name;
    }
}
