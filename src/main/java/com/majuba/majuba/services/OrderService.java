
package com.majuba.majuba.services;

import com.majuba.majuba.entities.Cart;
import com.majuba.majuba.entities.Order;
import com.majuba.majuba.entities.Table;
import com.majuba.majuba.repositories.OrderRepository;
import com.majuba.majuba.repositories.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    
        
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private TableRepository tableRepository;

    @Transactional
    public void createOrder(Long table_id, List<Cart> carts) {
        Order order = new Order();
        Optional<Table> table = tableRepository.findById(table_id);
        order.setTable(table.orElse(null));
        order.setCart_elements(carts);
        order.setClient(null);
    }

    @Transactional
    public void setEmail(Long order_id, String name, String email) {
        Order order = (Order) orderRepository.findById(order_id).orElse(null);
        order.getClient().setName(name);
        order.getClient().setEmail(email);
    }

}
