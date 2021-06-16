package com.majuba.majuba.services;

import com.majuba.majuba.entities.Cart;
import com.majuba.majuba.entities.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class TableService {

    @Autowired
    private TableRepository tableRepository;

    @Transactional
    public void create(Integer capacity, Long access_code, Boolean available, Boolean pending_payment) {
        Table table = new Table();
        table.setCapacity(capacity);
        table.setAccess_code(access_code);
        table.setAvailable(available);
        table.setPending_payment(pending_payment);
        tableRepository.save(table);
    }

    //@Transactional(readOnly = true)
    //public List<Cart> buscarTodos() {
      //  return cartrepository.findAll();
    //}



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