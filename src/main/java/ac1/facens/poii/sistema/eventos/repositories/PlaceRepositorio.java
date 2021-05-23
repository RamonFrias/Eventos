package ac1.facens.poii.sistema.eventos.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ac1.facens.poii.sistema.eventos.entities.Place;



@Repository
public interface PlaceRepositorio extends JpaRepository <Place, Long>{
    
    //@Query("SELECT pl FROM Place pl ")
    
    @Query("SELECT pl FROM Place pl " + 
           "WHERE " +
           "LOWER(pl.name)           LIKE   LOWER(CONCAT('%', :name, '%'))          AND " +
           "LOWER(pl.address)          LIKE   LOWER(CONCAT('%', :address, '%'))")

    public Page<Place>find(Pageable pageResquest, String name, String address);
}
