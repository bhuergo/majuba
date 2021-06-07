/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.majuba.entities;

import java.io.Serializable;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Barbara
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carrito implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_elemento;
    
    @ManyToOne
    private Comida comida;
    
    private Long cantidad;
    private Double subtotal;
    
    @ManyToOne
    private Pedido pedido;
    
}
