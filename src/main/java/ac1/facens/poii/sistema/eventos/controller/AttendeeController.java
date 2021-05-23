package ac1.facens.poii.sistema.eventos.controller;

import java.net.URI;

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
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ac1.facens.poii.sistema.eventos.dto.AttendeeDTO;
import ac1.facens.poii.sistema.eventos.dto.AttendeeInsertDTO;
import ac1.facens.poii.sistema.eventos.dto.AttendeeUpdateDTO;
import ac1.facens.poii.sistema.eventos.service.AttendeeService;


@RestController
@RequestMapping("/attendees")
public class AttendeeController {
    
    @Autowired
    private AttendeeService serviceAttendee;
    
    @GetMapping
    public ResponseEntity<Page<AttendeeDTO>> getAttendee(

        @RequestParam(value = "page",         defaultValue = "0") Integer page,
        @RequestParam(value = "linesPerPage", defaultValue = "6") Integer linesPerPage,
        @RequestParam(value = "direction",    defaultValue = "ASC") String direction,
        @RequestParam(value = "orderBy",      defaultValue = "id") String orderBy,
        @RequestParam(value = "name",         defaultValue = "") String name,
        @RequestParam(value = "email",        defaultValue = "") String email
    ){

        
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
        
        Page <AttendeeDTO> list = serviceAttendee.getAttendee(pageRequest, name, email);
        
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("{id}")
    public ResponseEntity<AttendeeDTO> getClientById(@PathVariable Long id){
        AttendeeDTO dto = serviceAttendee.getAttendeeById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<AttendeeDTO> insert(@RequestBody AttendeeInsertDTO insertDTO){
        AttendeeDTO dto = serviceAttendee.insert(insertDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        serviceAttendee.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<AttendeeDTO> update(@RequestBody AttendeeUpdateDTO updateDTO, @PathVariable Long id) {
        AttendeeDTO dto = serviceAttendee.update(id, updateDTO);
        
        return ResponseEntity.ok().body(dto);
    }
}
