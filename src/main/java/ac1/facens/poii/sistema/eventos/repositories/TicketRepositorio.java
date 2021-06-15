package ac1.facens.poii.sistema.eventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ac1.facens.poii.sistema.eventos.entities.Ticket;

@Repository
public interface TicketRepositorio extends JpaRepository<Ticket,Long>{
    
}
