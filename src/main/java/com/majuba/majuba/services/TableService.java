package com.majuba.majuba.services;

import com.majuba.majuba.entities.Cart;
import com.majuba.majuba.entities.Category;
import com.majuba.majuba.entities.Table;
import com.majuba.majuba.entities.Waiter;
import com.majuba.majuba.repositories.CartRepository;
import com.majuba.majuba.repositories.TableRepository;
import com.majuba.majuba.repositories.WaiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TableService {

    @Autowired
    private TableRepository tableRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private WaiterRepository waiterRepository;

    @Transactional
    public void create(Integer capacity, Boolean available, Boolean pending_payment) {
        Table table = new Table();
        table.setCapacity(capacity);
        table.setAvailable(available);
        table.setPending_payment(pending_payment);
        tableRepository.save(table);
    }


    @Transactional(readOnly = true)
    public List<Table> findAll() {
        return tableRepository.findAll();
    }

    @Transactional
    public Table generateAccessCode(Integer num_guests) {
        Table table = tableRepository.assigned_table(num_guests);
        double code = (Math.random() * (999999-100000) + 100000);
        Long access_code = (Double.valueOf(code)).longValue();
        table.setAccess_code(access_code);
        return table;
    }

    @Transactional
    public Integer checkAvailability(Integer num_guests) {
        return tableRepository.num_tables(num_guests);
    }

    @Transactional
    public Boolean checkAccessCode(Long token, Long access_code) {
        if (token.equals(access_code)){
            return true;
        } else {
            return false;
        }
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

    @Transactional
    public void ocupateTable(Long table_id){
        tableRepository.updateAvailability(table_id);
    }


    //revisar
    @Transactional
    public void assignWaiters(Long table_id, List<Waiter> waiters){
        Table table = tableRepository.findById(table_id).orElse(null);
        table.setWaiters(waiters);
        tableRepository.save(table);

    }

    @Transactional
    public void resetTable(Long table_id) {
        tableRepository.updateAvailability(table_id);
        tableRepository.reset(table_id);
        Optional<Table> tableOptional = tableRepository.findById(table_id);
        Table table = tableOptional.orElse(null);
        table.setWaiters(null);
    }

}
