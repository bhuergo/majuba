
package com.majuba.majuba.services;

import com.majuba.majuba.entities.Client;
import com.majuba.majuba.entities.Order;
import com.majuba.majuba.entities.Payment;
import com.majuba.majuba.repositories.PaymentRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class PaymentService {
    
        
    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public void create(Long payment_id, Date date, Order order, Client client) {
            Payment payment = new Payment();
            payment.setClient(client);
            payment.setDate(date);
            payment.setOrder(order);
            payment.setPayment_id(payment_id);
       paymentRepository.save(payment);

    }

    @Transactional(readOnly = true)
    public List<Payment> fidAll() {
        return paymentRepository.findAll();
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
    public void delete(Long payment_id) {
        paymentRepository.deleteById(payment_id);
    }

    
    @Transactional(readOnly = true)
    public Payment searchById(Long payment_id) {
        Optional<Payment> paymentOptional = paymentRepository.findById(payment_id);
        return paymentOptional.orElse(null);
    }
    
    
}
