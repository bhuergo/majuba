package com.majuba.majuba.services;

import com.majuba.majuba.entities.Cart;
import com.majuba.majuba.entities.Food;
import com.majuba.majuba.entities.FoodDTO;
import com.majuba.majuba.repositories.CartRepository;
import com.majuba.majuba.repositories.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    FoodRepository foodRepository;
    @Autowired
    CartRepository cartRepository;

    @Transactional
    public List<Cart> transformDTO(List<FoodDTO> foodDTOS) {
        List<Cart> cartList = new ArrayList<>();
        for (FoodDTO foodDTO : foodDTOS) {
            Cart cart = new Cart();
            Optional<Food> foodOptional = foodRepository.findById(foodDTO.getFood_id());
            cart.setFood(foodOptional.orElse(null));
            cart.setAmount(foodDTO.getAmount());
            cart.setSubtotal((foodOptional.get().getPrice()) * foodDTO.getAmount());
            cartRepository.save(cart);
            cartList.add(cart);
        }
        return cartList;
    }
}
