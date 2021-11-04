package com.geekbrains.ru.springmvcdemo.service.impl;

import com.geekbrains.ru.springmvcdemo.domain.Category;
import com.geekbrains.ru.springmvcdemo.domain.Product;
import com.geekbrains.ru.springmvcdemo.domain.search.ProductSearchConditional;
import com.geekbrains.ru.springmvcdemo.repository.ProductRepository;
import com.geekbrains.ru.springmvcdemo.repository.specification.ProductSearchSpecification;
import com.geekbrains.ru.springmvcdemo.service.ProductService;
import com.geekbrains.ru.springmvcdemo.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Page<Product> getProducts(ProductSearchConditional searchConditional) {

        Pageable pageable = PageRequest.of(searchConditional.getPage(),
                searchConditional.getSize(), Sort.by(Sort.Direction.ASC, "title"));

        ProductSearchSpecification specification = new ProductSearchSpecification(searchConditional);
        return productRepository.findAll(specification, pageable);

    }

    @Override
    public void saveProductWithImage(Product product, MultipartFile image) {
        if (image != null && !image.isEmpty()) {
            Path pathImage = FileUtils.saveProductImage(image);
            product.setImageLink(pathImage.toString());
        }

        productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public boolean deleteById(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}