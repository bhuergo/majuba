package com.majuba.majuba.services;

import com.majuba.majuba.entities.Cart;
import com.majuba.majuba.entities.Table;
import com.majuba.majuba.repositories.CartRepository;
import com.majuba.majuba.repositories.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class TableService {

    @Autowired
    private TableRepository tableRepository;
    @Autowired
    private CartRepository cartRepository;

    @Transactional
    public void create(Integer capacity, Boolean available, Boolean pending_payment) {
        Table table = new Table();
        table.setCapacity(capacity);
        table.setAvailable(available);
        table.setPending_payment(pending_payment);
        tableRepository.save(table);
    }

    @Transactional(readOnly = true)
    public List<Cart> findAll() {
        return cartRepository.findAll();
    }

    public void generateAccessCode(Long table_id) {
        Table table = searchById(table_id);
        double code = (Math.random() * (999999-100000) + 100000);
        Long access_code = (Double.valueOf(code)).longValue();
        table.setAccess_code(access_code);
    }

    @Transactional
    public void delete(Long table_id) {
        tableRepository.deleteById(table_id);
    }

    @Transactional(readOnly = true)
    public Table searchById(Long table_id) {
        Optional<Table> tableOptional = tableRepository.findById(table_id);
        return tableOptional.orElse(null);
    }


}
