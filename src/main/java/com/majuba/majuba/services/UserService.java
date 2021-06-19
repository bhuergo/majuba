
package com.majuba.majuba.services;


import com.majuba.majuba.entities.Role;
import com.majuba.majuba.entities.User;
import com.majuba.majuba.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UserService {
    
        
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void create(Long user_id, String username, String password, Role role) {
       User user = new User();
        user.setPassword(password);
        user.setRole(role);
        user.setUser_id(user_id);
        user.setUsername(username);
        userRepository.save(user);

    }

    @Transactional(readOnly = true)
    public List<User> fidAll() {
        return userRepository.findAll();
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
    public void delete(Long user_id) {
        userRepository.deleteById(user_id);
    }

    
    @Transactional(readOnly = true)
    public User searchById(Long user_id) {
        Optional<User> userOptional = userRepository.findById(user_id);
        return userOptional.orElse(null);
    }
    
    
    
    
}
