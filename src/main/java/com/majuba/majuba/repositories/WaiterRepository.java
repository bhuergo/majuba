
package com.majuba.majuba.repositories;

import com.majuba.majuba.entities.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaiterRepository extends JpaRepository<Waiter, Long>{
    
}
