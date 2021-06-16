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
    private List<Ingredient> ingredients;
    @ManyToOne
    private Cart cart;
}
