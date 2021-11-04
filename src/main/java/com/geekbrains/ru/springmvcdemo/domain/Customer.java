package com.geekbrains.ru.springmvcdemo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@Entity
@ToString(exclude = "products")
@EqualsAndHashCode(exclude = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //    @ManyToMany
//    @JoinTable(
//            name = "product_customer",
//            joinColumns = @JoinColumn(name = "customer_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id")
//    )
    @OneToMany(mappedBy = "product")
    private Set<PurchaseDetails> products = new HashSet<>();

}
