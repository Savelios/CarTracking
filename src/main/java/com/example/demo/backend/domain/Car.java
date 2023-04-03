package com.example.demo.backend.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Table(name = "cars")
@Setter
@Getter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Car extends PersistentObject {
//    @Column(name = "userId")
//    Long userId;
    @Column(name = "brand")
    String brand;
    @Column(name = "model")
    String model;
    @Column(name = "registrationNumber")
    String registrationNumber;
}
