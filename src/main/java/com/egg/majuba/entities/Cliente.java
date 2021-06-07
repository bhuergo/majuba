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
public class Cliente implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cliente;
    private String nombre;
    private String documento;
    private String email;
    private String telefono;
    
    @OneToMany(mappedBy="cliente", fetch = FetchType.EAGER)
    private List<Pago> pagos;
}
