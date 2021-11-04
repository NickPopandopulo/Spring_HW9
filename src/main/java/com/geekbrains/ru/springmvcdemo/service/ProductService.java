package com.geekbrains.ru.springmvcdemo.service;

import com.geekbrains.ru.springmvcdemo.domain.Product;
import com.geekbrains.ru.springmvcdemo.domain.search.ProductSearchConditional;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

    Page<Product> getProducts(ProductSearchConditional searchConditional);

    void saveProductWithImage(Product product, MultipartFile image);

    Product findById(Long id);

    boolean deleteById(Long id);
}
