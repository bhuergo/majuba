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
public class Table implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long table_id;
    private Integer capacity;
    private Long access_code;
    private Boolean available;
    private Boolean pending_payment;
    @OneToOne
    private Order order;
}
