
package com.majuba.majuba.services;

import com.majuba.majuba.entities.Cart;
import com.majuba.majuba.entities.Client;
import com.majuba.majuba.entities.Order;
import com.majuba.majuba.entities.Table;
import com.majuba.majuba.repositories.ClientRepository;
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
    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public void createOrder(Long table_id, List<Cart> carts) {
        Order order = new Order();
        Optional<Table> table = tableRepository.findById(table_id);
        order.setTable(table.orElse(null));
        order.setCart_elements(carts);
        order.setClient(null);
        orderRepository.save(order);
    }

    @Transactional
    public void setEmail(Long order_id, String name, String email) {
        Order order = orderRepository.findById(order_id).orElse(null);
        Client client = new Client();
        order.setClient(client);
        orderRepository.setClient(client,order_id);
        order.getClient().setName(name);
        order.getClient().setEmail(email);
        Long client_id = order.getClient().getClient_id();
        clientRepository.updateClient(name,email,client_id);
    }

    public Order showOrder(Long table_id) {
        Order order = orderRepository.retrieveOrder(table_id);
        return order;
    }


    public Order findOrderById(Long order_id) {
        Optional<Order> optionalOrder = orderRepository.findById(order_id);
        Order order = optionalOrder.orElse(null);
        return order;
    }

    public void deleteOrder(Order order) {
        orderRepository.delete(order);
    }

}
