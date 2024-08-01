package com.ericpinto.catalogms.stub;

import com.ericpinto.catalogms.api.dto.CreateCategoryDTO;
import com.ericpinto.catalogms.model.CategoryModel;

public class CategoryStub {

    private static final String ID = "8c4bec34-5bce-41b2-ad85-c5e38f1d26fb";
    private static final String NAME = "Eletrônicos";

    public static CategoryModel createCategoryStub(){
        return CategoryModel.builder()
                .id(ID)
                .name(NAME)
                .build();
    }

    public static CreateCategoryDTO createCategoryDTOStub(){
        return new CreateCategoryDTO(NAME);
    }

    public static CreateCategoryDTO teste(){
        return new CreateCategoryDTO("");
    }
}
