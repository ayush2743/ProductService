package com.example.productservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
//This the structure of output we will get from fakestore


//This is the things what company want to expose the data to the clients.
// Like hiding all the irrelevant details and showing only necessary details