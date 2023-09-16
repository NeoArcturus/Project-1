package com.SpringBootWebApplication.FullStack.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private String id;

    @OneToOne
    @JoinColumn(name = "transaction", referencedColumnName = "id")
    private Transaction transaction;

    private String orderMessage;
    private String orderStatus;
}
