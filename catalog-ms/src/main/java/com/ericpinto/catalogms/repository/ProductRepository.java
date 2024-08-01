package com.ericpinto.catalogms.repository;

import com.ericpinto.catalogms.model.ProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<ProductModel, String> {

   List<ProductModel> findAllByCategoryModel_Id(String categoryId);
   List<ProductModel> findByNameContainingIgnoreCaseOrCategoryModel_NameContainingIgnoreCase(String name, String categoryId);
}
