package com.bsuir.server.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cars")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private int carId;
    @Column(name = "car_mark")
    private String mark;
    @Column(name = "car_model")
    private String model;
    @Column(name = "car_year")
    private Integer year;
    @Column(name = "car_color")
    private String color;
    @Column(name = "car_price")
    private Integer price;
}
