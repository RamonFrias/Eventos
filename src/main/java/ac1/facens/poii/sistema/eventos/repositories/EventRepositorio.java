package ac1.facens.poii.sistema.eventos.repositories;


import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ac1.facens.poii.sistema.eventos.entities.Event;


@Repository
public interface EventRepositorio extends JpaRepository <Event,Long>{

    // @Query("SELECT ev FROM Event ev ")
    
     
        @Query("SELECT ev FROM Event ev " + 
           "WHERE " +
           "LOWER(ev.name)           LIKE   LOWER(CONCAT('%', :name, '%'))          AND " +
           "LOWER(ev.description)    LIKE   LOWER(CONCAT('%', :description, '%'))   AND " +
           "ev.start_date >= :start_date"
    )

    public Page<Event>find(Pageable pageResquest, String name, LocalDate start_date,String description);
}
 