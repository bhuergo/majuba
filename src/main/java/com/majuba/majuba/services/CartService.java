package com.majuba.majuba.services;

import com.majuba.majuba.entities.*;
import com.majuba.majuba.repositories.CartRepository;
import com.majuba.majuba.repositories.FoodRepository;
import com.majuba.majuba.repositories.OrderRepository;
import com.majuba.majuba.repositories.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private TableRepository tableRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public void transformDTO(Long table_id, List<FoodDTO> foodDTOS) throws Exception {
        Order order;
        if (orderRepository.existingTable(table_id) == null) {
            order = new Order();
            Optional<Table> table = tableRepository.findById(table_id);
            order.setTable(table.orElse(null));
            order.setClient(null);
            order = orderRepository.save(order);
        } else {
            order = orderRepository.existingTable(table_id);
        }

        Double tot = order.getTotal(); //5
        Cart cart = null;
        for (FoodDTO foodDTO : foodDTOS) {
            cart = new Cart();
            Optional<Food> foodOptional = foodRepository.findById(foodDTO.getFood_id());
            Food food = foodOptional.orElseThrow(Exception::new);
            cart.setFood(food);
            cart.setAmount(foodDTO.getAmount());
            Double sub = food.getPrice() * foodDTO.getAmount();
            tot = tot + sub;
            cart.setSubtotal(sub);
            cart.setOrder(order);
            cart.setShowFront(true);
            cartRepository.save(cart);
        }
        cart.getOrder().setTotal(tot);
    }

    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    public List<Cart> findByOrder(Long order_id) {
        return cartRepository.findByOrder(order_id);
    }

    @Transactional
    public void hideCart(Long cart_id) {
        cartRepository.hideCart(cart_id);
    }

    @Transactional
    public void deleteAll(Long order_id) {
        cartRepository.deleteAll(order_id);
    }
}
