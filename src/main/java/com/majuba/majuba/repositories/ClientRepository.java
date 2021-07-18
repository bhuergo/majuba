
package com.majuba.majuba.repositories;

import com.majuba.majuba.entities.Client;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
    
    @Query("SELECT a FROM Client a WHERE a.name LIKE :name")
    List<Client> searchForName(@Param("name") String name);

    @Modifying
    @Query("UPDATE Client c SET c.name = :name, c.email = :email WHERE c.client_id = :client_id")
    public void updateClient(@Param("name") String name, @Param("email") String email, @Param("client_id") Long client_id);
}
