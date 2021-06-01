package ac1.facens.poii.sistema.eventos.service;

import java.time.Instant;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;
import ac1.facens.poii.sistema.eventos.dto.TicketDTO;
import ac1.facens.poii.sistema.eventos.dto.TicketInsertDTO;
import ac1.facens.poii.sistema.eventos.dto.TicketUpdateDTO;
import ac1.facens.poii.sistema.eventos.entities.Ticket;
import ac1.facens.poii.sistema.eventos.repositories.TicketRepositorio;

@Service
public class TicketService {
    
    @Autowired
    private TicketRepositorio repositoryticket;
    
    
    public Page<TicketDTO> getTicket(PageRequest pageRequest, Instant date, double price) {
        
        Page<Ticket> list = repositoryticket.find(pageRequest, date, price);

        return list.map( tk -> new TicketDTO(tk));
    }
    
    public TicketDTO getTicketById(Long id){

        Optional<Ticket> op = repositoryticket.findById(id);

        Ticket tk = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found"));

        return new TicketDTO(tk);
    }

    public TicketDTO insert(TicketInsertDTO dto) {
        Ticket entity = new Ticket(dto);
        entity = repositoryticket.save(entity);
        return new TicketDTO(entity);
    }

    public TicketDTO update(Long id, TicketUpdateDTO dto){
        try{
            Ticket entity = repositoryticket.getOne(id);
            entity.setDate(dto.getDate());
            entity.setPrice(dto.getPrice());
            entity = repositoryticket.save(entity);
    
            return new TicketDTO(entity);
        }
        catch(EntityNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found");
        }
    }
    public void delete(Long id){
        try {
            repositoryticket.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket not found");
        }
    }

}
