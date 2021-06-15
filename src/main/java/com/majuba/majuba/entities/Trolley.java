package com.majuba.majuba.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trolley implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trolley_id;
    @ManyToOne
    private Food food;
    private Long amount;
    private Double subtotal;
    @ManyToOne
    private Order order;
}
