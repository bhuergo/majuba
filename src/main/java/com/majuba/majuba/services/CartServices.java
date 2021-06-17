
package com.majuba.majuba.services;

import com.majuba.majuba.entities.Cart;
import com.majuba.majuba.entities.Food;
import com.majuba.majuba.entities.Order;
import com.majuba.majuba.repositories.CartRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class CartServices {
    
    
    @Autowired
    private CartRepository cartrepository;

    @Transactional
    public void create() {
        Cart cart = new Cart();
        
        cartrepository.save(cart);

    }

    @Transactional(readOnly = true)
    public List<Cart> fidAll() {
        return cartrepository.findAll();
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
    public void delete(Long cart_id) {
        cartrepository.deleteById(cart_id);
    }

    
    @Transactional(readOnly = true)
    public Cart searchById(Long cart_id) {
        Optional<Cart> cartOptional = cartrepository.findById(cart_id);
        return cartOptional.orElse(null);
    }
    
}
