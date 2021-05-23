package ac1.facens.poii.sistema.eventos.controller;

import java.net.URI;
import java.time.Instant;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ac1.facens.poii.sistema.eventos.dto.TicketDTO;
import ac1.facens.poii.sistema.eventos.dto.TicketInsertDTO;
import ac1.facens.poii.sistema.eventos.dto.TicketUpdateDTO;
import ac1.facens.poii.sistema.eventos.service.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    
    @Autowired
    private TicketService serviceticket;
    
    @GetMapping
    public ResponseEntity<Page<TicketDTO>> getTicket(

        @RequestParam(value = "page",         defaultValue = "0") Integer page,
        @RequestParam(value = "linesPerPage", defaultValue = "6") Integer linesPerPage,
        @RequestParam(value = "direction",    defaultValue = "ASC") String direction,
        @RequestParam(value = "orderBy",      defaultValue = "id") String orderBy,
        @RequestParam(value = "type",         defaultValue = "") Enum type,
        @RequestParam(value = "date",        defaultValue = "") Instant date,
        @RequestParam(value = "price",        defaultValue = "") double price
    ){

        
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
        
        Page <TicketDTO> list = serviceticket.getTicket(pageRequest, type, date, price);
        
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("{id}")
    public ResponseEntity<TicketDTO> getClientById(@PathVariable Long id){
        TicketDTO dto = serviceticket.getTicketById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<TicketDTO> insert(@RequestBody TicketInsertDTO insertDTO){
        TicketDTO dto = serviceticket.insert(insertDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        serviceticket.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<TicketDTO> update(@RequestBody TicketUpdateDTO updateDTO, @PathVariable Long id) {
        TicketDTO dto = serviceticket.update(id, updateDTO);
        
        return ResponseEntity.ok().body(dto);
    }
}
