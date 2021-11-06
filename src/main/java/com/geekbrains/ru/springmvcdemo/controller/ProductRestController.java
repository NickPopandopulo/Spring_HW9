package com.geekbrains.ru.springmvcdemo.controller;

import com.geekbrains.ru.springmvcdemo.domain.Product;
import com.geekbrains.ru.springmvcdemo.domain.search.ProductSearchConditional;
import com.geekbrains.ru.springmvcdemo.service.CategoryService;
import com.geekbrains.ru.springmvcdemo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductRestController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/list/category/{alias}")
    public List<Product> getProductsWithAlias(@PathVariable String alias, HttpServletRequest request) {
        ProductSearchConditional searchConditional = new ProductSearchConditional();
        searchConditional.setCategory(categoryService.findByAlias(alias));
        searchConditional.handleRequest(request);

        return productService.getProducts(searchConditional).getContent();
    }

    @GetMapping("/list")
    public List<Product> getProducts(HttpServletRequest request) {
        ProductSearchConditional searchConditional = new ProductSearchConditional();
        searchConditional.handleRequest(request);

        return productService.getProducts(searchConditional).getContent();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping( "/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        if (productService.deleteById(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = { POST, PUT }, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void saveProduct(@RequestPart Product product,
                            @RequestPart(required = false) MultipartFile image) {

        productService.saveProductWithImage(product, image);
    }

}
