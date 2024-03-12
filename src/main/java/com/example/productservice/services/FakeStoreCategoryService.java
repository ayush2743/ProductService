package com.example.productservice.services;

import com.example.productservice.models.Category;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreCategoryService implements CategoryService {
    RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Category> getAllCategories() {
        String[] fakeStoreCategories = restTemplate.getForObject(
                "https://fakestoreapi.com/products/categories",
                String[].class
        );
        List<Category> categories = new ArrayList<>();
        if(fakeStoreCategories != null) {

            for(int i=0; i<fakeStoreCategories.length; i++) {
                Category category = new Category();
                category.setName(fakeStoreCategories[i]);
                categories.add(category);
            }

            return categories;
        }

        return null;
    }
}
