package com.geekbrains.ru.springmvcdemo.service.impl;

import com.geekbrains.ru.springmvcdemo.domain.Customer;
import com.geekbrains.ru.springmvcdemo.domain.Product;
import com.geekbrains.ru.springmvcdemo.domain.PurchaseDetails;
import com.geekbrains.ru.springmvcdemo.repository.CustomerRepository;
import com.geekbrains.ru.springmvcdemo.repository.ProductRepository;
import com.geekbrains.ru.springmvcdemo.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    ProductRepository productRepository;
    CustomerRepository customerRepository;

    @Override
    public Set<Customer> getCustomersOfProduct(Long productId) {
//        return productRepository.get(productId).getCustomers();
        return null;
    }

    @Override
    public Set<Product> getProductsOfCustomer(Long customerId) {
//        return customerRepository.get(customerId).getProducts();
        return null;
    }
}
