
package com.majuba.majuba.services;

import com.majuba.majuba.entities.Waiter;
import com.majuba.majuba.repositories.WaiterRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class WaiterService {
    
        
    @Autowired
    private WaiterRepository waiterRepository;

    @Transactional
    public void create(String name) {
        Waiter waiter = new Waiter();
        waiter.setName(name);
        waiterRepository.save(waiter);
    }

    @Transactional(readOnly = true)
    public List<Waiter> fidAll() {
        return waiterRepository.findAll();
    }

    @Transactional
    public void delete(Long waiter_id) {
         waiterRepository.deleteById(waiter_id);
    }

    
    @Transactional(readOnly = true)
    public Waiter searchById(Long waiter_id) {
        Optional<Waiter> waiterOptional = waiterRepository.findById(waiter_id);
        return waiterOptional.orElse(null);
    }
    
    
}
