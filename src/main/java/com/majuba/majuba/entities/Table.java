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
@javax.persistence.Table(name = "restaurant_table")
public class Table implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long table_id;
    private Integer capacity;//NO
    private Long access_code;
    private Boolean available;//debe mostrarse con un color (verde:disponible, rojo:ocupado, naranja:sin pagar-EFECTIVO-)
    private Boolean pending_payment;
    @OneToOne
    private Order order;//NO
    @ManyToMany
    private List<Waiter> waiters;




}
