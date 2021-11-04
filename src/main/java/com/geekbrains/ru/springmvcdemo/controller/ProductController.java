package com.geekbrains.ru.springmvcdemo.controller;

import com.geekbrains.ru.springmvcdemo.domain.Product;
import com.geekbrains.ru.springmvcdemo.domain.search.ProductSearchConditional;
import com.geekbrains.ru.springmvcdemo.service.CategoryService;
import com.geekbrains.ru.springmvcdemo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    private final Validator validator;

    @GetMapping("/list")
    public ModelAndView getProducts(@RequestParam(required = false) String alias, HttpServletRequest request) {
        ProductSearchConditional searchConditional = new ProductSearchConditional();
        searchConditional.setCategory(categoryService.findByAlias(alias));

        searchConditional.handleRequest(request);

        ModelAndView modelAndView = new ModelAndView("product/list");
        modelAndView.addObject("products", productService.getProducts(searchConditional));
        modelAndView.addObject("categoryTree", categoryService.getCategoryTree());

        return modelAndView;
    }

    @GetMapping("/form")
    public String getProductForm(@RequestParam(required = false) Long id, Model model,
                                 @ModelAttribute(value = "violations") String violations) {

        Product product = new Product();
        if (id != null) {
            product = productService.findById(id);
        }

        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.findAll());

        return "product/form";
    }

    @GetMapping( "/delete")
    public RedirectView deleteProduct(@RequestParam Long id) {
        productService.deleteById(id);
        return new RedirectView("/product/list");
    }

    @PostMapping
    public RedirectView saveProduct(@ModelAttribute Product product,
                                    @RequestParam(required = false) MultipartFile image,
                                    RedirectAttributes attributes) {

        Set<ConstraintViolation<Product>> validationResult = validator.validate(product);
        if (!validationResult.isEmpty()) {
            String violations = validationResult.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining("\n"));

            attributes.addFlashAttribute("violations", violations);

            return new RedirectView("/product/form");
        }

        productService.saveProductWithImage(product, image);
        return new RedirectView("/product/list");
    }

}
