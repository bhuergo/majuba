/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.majuba.majuba.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author JP
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Food implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long food_id;
    private byte[] image;
    private String title;
    private String description;
    private Double price;
    @ManyToOne
    private Category category;
    @ManyToMany
    private List<Ingredient> ingredient;
    @ManyToOne
    private Trolley trolley;
}
