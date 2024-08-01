package com.ericpinto.catalogms.repository;

import com.ericpinto.catalogms.model.CategoryModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<CategoryModel, String> {
    Boolean existsByName(String name);
}
