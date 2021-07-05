
package com.majuba.majuba.services;

import com.majuba.majuba.entities.Order;
import com.majuba.majuba.entities.Payment;
import com.majuba.majuba.entities.Table;
import com.majuba.majuba.entities.Waiter;
import com.majuba.majuba.repositories.OrderRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class OrderService {
    
        
    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public void create(Long order_id, Table table, List cart_elements,Payment payment) {
        Order order = new Order();
        order.setCart_elements(cart_elements);
        order.setOrder_id(order_id);
        order.setPayment(payment);
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
