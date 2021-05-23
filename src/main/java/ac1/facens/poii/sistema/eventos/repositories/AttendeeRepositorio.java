package ac1.facens.poii.sistema.eventos.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ac1.facens.poii.sistema.eventos.entities.Attendee;

@Repository
public interface AttendeeRepositorio extends JpaRepository<Attendee,Long>{
    //@Query("SELECT at FROM Attendee at ")
    
    @Query("SELECT at FROM Attendee at " + 
        "WHERE " +
        "LOWER(at.name)           LIKE   LOWER(CONCAT('%', :name, '%'))          AND " +
        "LOWER(at.email)          LIKE   LOWER(CONCAT('%', :email, '%'))")

    public Page<Attendee>find(Pageable pageResquest, String name, String email);
}
