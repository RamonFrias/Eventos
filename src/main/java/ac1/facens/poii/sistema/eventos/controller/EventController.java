package ac1.facens.poii.sistema.eventos.controller;

import java.net.URI;
import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ac1.facens.poii.sistema.eventos.dto.EventInsertDTO;
import ac1.facens.poii.sistema.eventos.dto.EventTicketDTO;
import ac1.facens.poii.sistema.eventos.dto.EventUpdateDTO;
import ac1.facens.poii.sistema.eventos.dto.TicketInsertDTO;
import ac1.facens.poii.sistema.eventos.entities.Event;
import ac1.facens.poii.sistema.eventos.dto.EventDTO;
import ac1.facens.poii.sistema.eventos.service.EventService;


import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/events")
public class EventController {
    
    @Autowired
    private EventService service;

    @Autowired 
    private EventService eventPlaceList;
    //page
    @GetMapping
    public ResponseEntity<Page<EventDTO>> getEvento(

        @RequestParam(value = "page",         defaultValue = "0") Integer page,
        @RequestParam(value = "linesPerPage", defaultValue = "6") Integer linesPerPage,
        @RequestParam(value = "direction",    defaultValue = "ASC") String direction,
        @RequestParam(value = "orderBy",      defaultValue = "id") String orderBy,
        @RequestParam(value = "name",         defaultValue = "") String name,
        @RequestParam(value = "start_date",   defaultValue = "01/01/1000") LocalDate start_date, //foi colocado uma data de molde para ter uma seguencia
        @RequestParam(value = "description",  defaultValue = "") String description

    ){

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
        
        Page <EventDTO> list = service.getEvento(pageRequest, name, start_date, description);
        
        return ResponseEntity.ok().body(list);
    }

    /*@GetMapping
    public ResponseEntity<List<EventoDTO>> getEventos(){
        List<EventoDTO> list = service.getEvetos();
        
        return ResponseEntity.ok(list);
    }*/

    @GetMapping("{id}")
    public ResponseEntity<EventDTO> getEventsById(@PathVariable Long id){
        EventDTO dto = service.getEventoById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<EventDTO> insert(@RequestBody EventInsertDTO insertDTO){
        EventDTO dto = service.insert(insertDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<EventDTO> update(@RequestBody EventUpdateDTO updateDTO, @PathVariable Long id) {
        EventDTO dto = service.update(id, updateDTO);
        
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping("{ide}/places/{idp}")
    public ResponseEntity<Event> addPlace(@PathVariable Long ide, @PathVariable Long idp){
        Event events = service.addEventPlace(ide, idp); 
        return ResponseEntity.ok(events);
    }

    @DeleteMapping("{ide}/places/{idp}")
    public ResponseEntity<Void> deletePlace(@PathVariable Long ide, @PathVariable Long idp){
        eventPlaceList.deletePlace(ide, idp);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("{id}/tickets")
    public ResponseEntity<EventTicketDTO> getEventTicketsById(@PathVariable Long id){
        EventTicketDTO dto = service.getEventTicketById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("{ide}/tickets")
    public ResponseEntity<Void> addTicket(@PathVariable Long ide, @RequestBody TicketInsertDTO dto) {
        service.addTicket(ide, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{ide}/tickets")
    public ResponseEntity<Void> deleteTickets(@PathVariable Long ide){
        service.deleteTickets(ide);
        return ResponseEntity.noContent().build();
    }
}
