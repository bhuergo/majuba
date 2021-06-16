package com.majuba.majuba.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/*@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_id;
    @OneToOne
    private Table table;
    @OneToMany(mappedBy = "order")
    private List<Cart> cart_elements;
    @ManyToOne
    private Waiter waiter;
    @OneToOne
    private Payment payment;
}*/
