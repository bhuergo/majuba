package com.majuba.majuba.repositories;

import com.majuba.majuba.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
    @Modifying
    @Query("SELECT a FROM User a WHERE a.username LIKE :username")
    List<User> searchForUsername (@Param("username") String username);
}
