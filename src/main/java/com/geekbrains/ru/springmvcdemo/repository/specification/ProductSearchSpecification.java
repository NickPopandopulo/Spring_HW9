package com.geekbrains.ru.springmvcdemo.repository.specification;

import com.geekbrains.ru.springmvcdemo.domain.Category;
import com.geekbrains.ru.springmvcdemo.domain.Product;
import com.geekbrains.ru.springmvcdemo.domain.search.ProductSearchConditional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ProductSearchSpecification implements Specification<Product> {

    ProductSearchConditional searchConditional;

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        if (searchConditional.getMaxPrice() != null || searchConditional.getMinPrice() != null) {

            if (searchConditional.getMaxPrice() != null && searchConditional.getMinPrice() != null) {
                predicates.add(builder.between(root.get("price"),
                        searchConditional.getMinPrice(), searchConditional.getMaxPrice()));
            } else if (searchConditional.getMinPrice() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("price"), searchConditional.getMinPrice()));
            } else {
                predicates.add(builder.lessThanOrEqualTo(root.get("price"), searchConditional.getMaxPrice()));
            }

        }

        if (searchConditional.getCategory() != null) {
            Join<Product, Category> categories = root.join("categories");
            predicates.add(builder.equal(categories.get("id"),searchConditional.getCategory().getId()));
        }

        return builder.and(predicates.toArray(Predicate[]::new));
    }
}
