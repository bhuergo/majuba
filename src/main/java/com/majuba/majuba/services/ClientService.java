
package com.majuba.majuba.services;

import com.majuba.majuba.entities.Client;
import com.majuba.majuba.repositories.ClientRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ClientService {
    
        
    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public void create(Long client_id, String name, String document, String email, Long phone, List payments ) {
        Client client = new Client();
        client.setClient_id(client_id);
        client.setDocument(document);
        client.setEmail(email);
        client.setName(name);
        client.setPayments(payments);
        client.setPhone(phone);
        clientRepository.save(client);

    }

    @Transactional(readOnly = true)
    public List<Client> fidAll() {
        return clientRepository.findAll();
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
    public void delete(Long client_id) {
        clientRepository.deleteById(client_id);
    }

    
    @Transactional(readOnly = true)
    public Client searchById(Long client_id) {
        Optional<Client> clientOptional = clientRepository.findById(client_id);
        return clientOptional.orElse(null);
    }
    
    
}
