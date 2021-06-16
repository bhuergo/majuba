
package com.majuba.majuba.repositories;

import com.majuba.majuba.entities.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<Table, Long>{

    @Query("SELECT COUNT (t) FROM Table t WHERE t.capacity >= :num_guests")
    Integer num_tables(@Param("num_guests") Integer num_guests);

}
