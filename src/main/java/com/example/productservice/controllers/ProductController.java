package com.example.productservice.controllers;

import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


// 1. Tell spring this is a special class
//    so automatically create an object of
//    it and inject dependencies if needed
// 2. Tell spring that this class has methods
//    that may need to be called if our server
//    receives a request at particular endpoint
@RestController
public class ProductController {


    private ProductService productService;  //Creating an object of Product Service (Dependency Injection)

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    // Whenever anyone sends a
    // GET request at {MY_SERVER}/hello
    // run this method and return whatever
    // this is returning to client
    // @PostMapping
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World";
    }


    @GetMapping("/products")
    public List<Product> getAllProducts() {

        return productService.getAllProducts();
    }

    // URL Path: /products/1
    // /products/hi
    // /products/51
    //in the path there is the variable id and it will go to the Long id
    @GetMapping("/products/{id}")
    public Product getSingleProduct(@PathVariable("id") Long id) {

        return productService.getSingleProduct(id);
    }

    //RequestBody File which contains all the product details to be add
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {

        return productService.createProduct(product);
    }

    @GetMapping("/products/category/{name}")
    public List<Product> getProductByCategory(@PathVariable("name") String name) {
        return productService.getProductByCategory(name);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }

    @PutMapping("/products/{id}")
    public void updateProduct(@RequestBody Product product, @PathVariable("id") long id) {
        productService.updateProduct(product, id);
    }

}
