package com.inventoryserver.core.dtos;

public class CategoryDTO {
	
	private String categoryName;

	CategoryDTO(){}
    // Constructor
    public CategoryDTO(String categoryName) {
        this.categoryName = categoryName;
    }

    // Getters and setters
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
