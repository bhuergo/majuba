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
public class Mesa implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_mesa;
    private Long capacidad;
    private Long codigo_acceso;
    private boolean disponible;
    private boolean pago_pendiente;
    
    @OneToOne
    private Pedido pedido;
}
