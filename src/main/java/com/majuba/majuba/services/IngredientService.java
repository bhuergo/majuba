
package com.majuba.majuba.services;

import com.majuba.majuba.entities.Ingredient;
import com.majuba.majuba.repositories.IngredientRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class IngredientService {
 
        
    @Autowired
    private IngredientRepository ingredientRepository;

    @Transactional
    public void create(String description) {
       Ingredient ingredient = new Ingredient();
       ingredient.setDescription(description);
       
       
       ingredientRepository.save(ingredient);

    }

    @Transactional(readOnly = true)
    public List<Ingredient> fidAll() {
        return ingredientRepository.findAll();
    }
/*
   // @Transactional
   // public List<Cart> buscarPorNombre(String nombre) {
     //   return autorrepositorio.buscarPorNombre(nombre);
    //}
@Transactional
    public void modify(Integer cart_id,Cart cart){
        CartRepository.modify(cart_id,cart);
    }*/
    @Transactional
    public void delete(Long ingredient_id) {
        ingredientRepository.deleteById(ingredient_id);
    }

    
    @Transactional(readOnly = true)
    public Ingredient searchById(Long ingredient_id) {
        Optional<Ingredient> ingredientOptional = ingredientRepository.findById(ingredient_id);
        return ingredientOptional.orElse(null);
    }
    
    
}
