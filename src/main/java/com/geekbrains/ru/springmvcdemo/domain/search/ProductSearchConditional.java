package com.geekbrains.ru.springmvcdemo.domain.search;

import com.geekbrains.ru.springmvcdemo.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchConditional {

    private Integer minPrice;
    private Integer maxPrice;
    private Category category;
    private int page = 0;
    private int size = 8;

    public void handleRequest(HttpServletRequest request) {

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
    }

}
