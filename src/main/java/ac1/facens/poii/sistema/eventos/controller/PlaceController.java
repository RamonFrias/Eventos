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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ac1.facens.poii.sistema.eventos.dto.PLaceInsertDTO;
import ac1.facens.poii.sistema.eventos.dto.PlaceDTO;
import ac1.facens.poii.sistema.eventos.dto.PlaceUpdateDTO;
import ac1.facens.poii.sistema.eventos.service.PlaceService;

@RestController
@RequestMapping("/places")
public class PlaceController {
    
    @Autowired
    private PlaceService serviceplace;
    //page
    @GetMapping
    public ResponseEntity<Page<PlaceDTO>> getPace(

        @RequestParam(value = "page",         defaultValue = "0") Integer page,
        @RequestParam(value = "linesPerPage", defaultValue = "6") Integer linesPerPage,
        @RequestParam(value = "direction",    defaultValue = "ASC") String direction,
        @RequestParam(value = "orderBy",      defaultValue = "id") String orderBy,
        @RequestParam(value = "name",         defaultValue = "") String name,
        @RequestParam(value = "address",        defaultValue = "") String address
    ){

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
        
        Page <PlaceDTO> list = serviceplace.getPlace(pageRequest, name, address);
        
        return ResponseEntity.ok().body(list);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<PlaceDTO> getClientById(@PathVariable Long id){
        PlaceDTO dto = serviceplace.getPlaceById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PlaceDTO> insert(@RequestBody PLaceInsertDTO insertDTO){
        PlaceDTO dto = serviceplace.insert(insertDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        serviceplace.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<PlaceDTO> update(@RequestBody PlaceUpdateDTO updateDTO, @PathVariable Long id) {
        PlaceDTO dto = serviceplace.update(id, updateDTO);
        
        return ResponseEntity.ok().body(dto);
    }
}
