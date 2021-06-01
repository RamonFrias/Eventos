package ac1.facens.poii.sistema.eventos.repositories;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ac1.facens.poii.sistema.eventos.entities.Ticket;

@Repository
public interface TicketRepositorio extends JpaRepository<Ticket,Long>{
    
    //@Query("SELECT pl FROM Place pl ")
    
    @Query("SELECT tk FROM Ticket tk " + 
           "WHERE " +
           "LOWER(tk.date)          LIKE   LOWER(CONCAT('%', :date, '%'))           AND " +
           "LOWER(tk.price)          LIKE   LOWER(CONCAT('%', :price, '%'))")

    public Page<Ticket>find(Pageable pageResquest, Instant date, double price);
}
