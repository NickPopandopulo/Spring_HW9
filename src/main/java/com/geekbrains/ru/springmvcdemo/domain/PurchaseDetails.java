package com.geekbrains.ru.springmvcdemo.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "purchase_details")
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDetails {

    @EmbeddedId
    private PurchaseDetailsId id = new PurchaseDetailsId();

    @ManyToOne
    @MapsId("customerId")
    private Customer customer;

    @ManyToOne
    @MapsId("productId")
    private Product product;

    @Column(name = "purchase_price")
    private String purchasePrice;

    @Embeddable
    class PurchaseDetailsId implements Serializable {
        private Long customerId;
        private Long productId;
    }
}






