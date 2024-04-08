package com.example.productservice.services;

import com.example.productservice.dto.FakeStoreProductDto;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service   //Dependency Injection
public class FakeStoreProductService implements ProductService{

    // Allows to send API requests to any URL
    RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Product> getAllProducts() {

        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                FakeStoreProductDto[].class
        );

        List<Product> products = new ArrayList<>();

        if (fakeStoreProductDtos != null) {
            for(int i = 0; i < fakeStoreProductDtos.length; i++) {
                FakeStoreProductDto fakeStoreProductDto = fakeStoreProductDtos[i];
                Product product = covertJSONToProduct(fakeStoreProductDto);
                products.add(product);
            }

            return products;
        }

        return null;
    }
    @Override
    public Product getSingleProduct(long id) {

        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                FakeStoreProductDto.class
        );
        return covertJSONToProduct(fakeStoreProductDto);
    }


    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto = convertProductToJSON(product);

        FakeStoreProductDto fakeStoreProductDtoOutput = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                fakeStoreProductDto,
                FakeStoreProductDto.class
        );

        return covertJSONToProduct(fakeStoreProductDtoOutput);
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/products/category/" + category,
                FakeStoreProductDto[].class
        );

        if (fakeStoreProductDtos != null) {
            List<Product> products = new ArrayList<>();
            for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
                Product product = covertJSONToProduct(fakeStoreProductDto);
                products.add(product);
            }
            return products;
        }

        return null;


    }

    @Override
    public void deleteProduct(long id) {

    }


    @Override
    public void updateProduct(Product product, long id) {
        FakeStoreProductDto fakeStoreProductDto = convertProductToJSON(product);
        restTemplate.put(
                "https://fakestoreapi.com/products/" + id,
                fakeStoreProductDto,
                FakeStoreProductDto.class
        );

    }

    private Product covertJSONToProduct(FakeStoreProductDto fakeStoreProductDto) {
        if(fakeStoreProductDto == null) return null;

        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProductDto.getCategory());
        product.setImageUrl(fakeStoreProductDto.getImage());

        return product;
    }

    private FakeStoreProductDto convertProductToJSON(Product product) {
        if(product == null) return null;

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();

        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        if(product.getCategory() != null) {
            fakeStoreProductDto.setCategory(product.getCategory().getName());
        }
        fakeStoreProductDto.setImage(product.getImageUrl());

        return fakeStoreProductDto;
    }
}
