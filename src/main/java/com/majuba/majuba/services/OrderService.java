
package com.majuba.majuba.services;

import com.majuba.majuba.entities.Order;
import com.majuba.majuba.entities.Table;
import com.majuba.majuba.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class OrderService {
    
        
    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public void create(Long order_id, Table table, List cart_elements) {
        Order order = new Order();
        order.setCart_elements(cart_elements);
        order.setOrder_id(order_id);
        order.setTable(table);
        orderRepository.save(order);

    }

    @Transactional(readOnly = true)
    public List<Order> fidAll() {
        return orderRepository.findAll();
    }

    @Transactional
    public void delete(Long order_id) {
        orderRepository.deleteById(order_id);
    }

    
    @Transactional(readOnly = true)
    public Order searchById(Long order_id) {
        Optional<Order> orderOptional = orderRepository.findById(order_id);
        return orderOptional.orElse(null);
    }
    
    
}
