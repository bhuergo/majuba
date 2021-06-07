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
public class Comida implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_comida;
    private byte[] imagen;
    private String titulo;
    private String descripcion;
    private Double precio;
    
    @ManyToOne
    private Categoria categoria;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Insumo> insumos;
    
    @OneToMany(mappedBy="comida", fetch = FetchType.EAGER)
    private List<Carrito> carrito;
}
