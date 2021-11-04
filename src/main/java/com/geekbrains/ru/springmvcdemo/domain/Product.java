package com.geekbrains.ru.springmvcdemo.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Имя продукта обязательно")
    private String title;
    private String description;

    @NotNull(message = "Цена продукта обязательна")
    private Integer price;

    @Column(name = "image_link")
    private String imageLink;

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @ToString.Exclude
    private Set<Category> categories = new HashSet<>();
    ;

    //    @ManyToMany
//    @JoinTable(
//            name = "product_customer",
//            joinColumns = @JoinColumn(name = "product_id"),
//            inverseJoinColumns = @JoinColumn(name = "customer_id")
//    )
    @ToString.Exclude
    @OneToMany(mappedBy = "product")
    private Set<PurchaseDetails> customers = new HashSet<>();

}
