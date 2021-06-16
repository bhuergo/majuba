
package com.majuba.majuba.repositories;

import com.majuba.majuba.entities.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<Table, Long>{
    
}
