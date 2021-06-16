
package com.majuba.majuba.repositories;

import com.majuba.majuba.entities.Trolley;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrolleyRepository extends JpaRepository<Trolley, Long>{
    
}
