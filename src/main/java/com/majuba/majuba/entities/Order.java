package com.majuba.majuba.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@javax.persistence.Table(name ="customer_order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;
    @OneToOne
    private Table table;
    @OneToMany(mappedBy = "order")
    private List<Cart> cart_elements;
    @OneToOne(cascade = {CascadeType.ALL})
    private Client client;
    private Double total = 0.0;
}
