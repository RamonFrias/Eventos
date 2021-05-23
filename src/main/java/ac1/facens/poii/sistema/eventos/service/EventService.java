package ac1.facens.poii.sistema.eventos.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ac1.facens.poii.sistema.eventos.dto.EventDTO;
import ac1.facens.poii.sistema.eventos.dto.EventInsertDTO;
import ac1.facens.poii.sistema.eventos.dto.EventUpdateDTO;
import ac1.facens.poii.sistema.eventos.entities.Event;
import ac1.facens.poii.sistema.eventos.repositories.EventRepositorio;

@Service
public class EventService {
    
    @Autowired
    private EventRepositorio repository;

    public Page<EventDTO> getEvento(PageRequest pageRequest, String name, LocalDate start_date, String description) {
        
        Page<Event> list = repository.find(pageRequest, name, start_date, description);

        return list.map( ev -> new EventDTO(ev));
    }
   
    /*public List<EventoDTO> getEvetos(PageRequest pageRequest, String nome) {

        List<Evento> list = repository.findAll();
        return toDTOList(list);
    }*/

    public EventDTO getEventoById(Long id){

        Optional<Event> op = repository.findById(id);

        Event ev = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));

        return new EventDTO(ev);
    }
    
    /*public List<EventoDTO> toDTOList(List<Evento> list) {

        List<EventoDTO> listDto = new ArrayList<>();

        for (Evento c : list) {
            EventoDTO dto = new EventoDTO(c.getId(),c.getName(), c.getDescription(), c.getPlace(), c.getStart_date(),
                    c.getEnd_date(), c.getStart_time(), c.getEnd_time(), c.getEmail_contact());  
            listDto.add(dto);
        }
        return listDto;
    }*/

    public EventDTO insert(EventInsertDTO dto) {
        Event entity = new Event(dto);
        if (entity.getStart_date().isAfter(entity.getEnd_date())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The start date cannot be greater than the end date");
        else if (entity.getStart_date().isEqual(entity.getEnd_date()) && entity.getStart_time().isAfter(entity.getEnd_time())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The start time cannot be greater than the end time");
        else if (entity.getAmountPayedTickets() > 0 && entity.getPriceTicket() <= 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket value must be greater than 0");
        else if (entity.getAmountPayedTickets() == 0 && entity.getPriceTicket() >= 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ticket value must be greater than 0");
        else if (entity.getAmountPayedTickets() <= 0 && entity.getAmountFreeTickets() <= 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You must have 1 ticket to have an event and the value of the ticket cannot be negative");
        entity = repository.save(entity);
        return new EventDTO(entity);
    }

    public EventDTO update(Long id, EventUpdateDTO dto){
        try{
            Event entity = repository.getOne(id);
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
            entity.setStart_date(dto.getStart_date());
            entity.setEnd_date(dto.getEnd_date());
            entity.setStart_time(dto.getStart_time());
            entity.setEnd_time(dto.getEnd_time());
            entity.setEmail_contact(dto.getEmail_contact());
            entity.setAmountFreeTickets(dto.getAmountFreeTickets());
            entity.setAmountPayedTickets(dto.getAmountPayedTickets());
            entity.setPriceTicket(dto.getPriceTicket());
            entity = repository.save(entity);
    
            return new EventDTO(entity);
        }
        catch(EntityNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }
    }

    public void delete(Long id){
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }
    }
     
}
