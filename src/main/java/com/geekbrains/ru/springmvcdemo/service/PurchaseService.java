package com.geekbrains.ru.springmvcdemo.service;

import com.geekbrains.ru.springmvcdemo.domain.Category;
import com.geekbrains.ru.springmvcdemo.domain.Customer;
import com.geekbrains.ru.springmvcdemo.domain.Product;

import java.util.List;
import java.util.Set;

public interface PurchaseService {

    Set<Customer> getCustomersOfProduct(Long productId);

    Set<Product> getProductsOfCustomer(Long customerId);
}
