package com.bsuir.server.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car carId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status orderStatus;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User sellerId;
}
