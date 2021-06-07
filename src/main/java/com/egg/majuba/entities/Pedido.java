/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.majuba.entities;

import java.io.Serializable;
import java.util.List;
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
public class Pedido implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pedido;
    
    @OneToOne
    private Mesa mesa;
    
    @OneToMany(mappedBy="pedido", fetch = FetchType.EAGER)
    private List<Carrito> elementos_carrito;
    
    @ManyToOne
    private Mesero mesero;
    
    @OneToOne
    private Pago pago;
}
