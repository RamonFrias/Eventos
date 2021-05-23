package ac1.facens.poii.sistema.eventos.controller;

import java.net.URI;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ac1.facens.poii.sistema.eventos.dto.AdminDTO;
import ac1.facens.poii.sistema.eventos.dto.AdminInsertDTO;
import ac1.facens.poii.sistema.eventos.dto.AdminUpdateDTO;
import ac1.facens.poii.sistema.eventos.service.AdminService;

@RestController
@RequestMapping("/admins")
public class AdminController {
    
    @Autowired
    private AdminService serviceadmin;
    
    @GetMapping
    public ResponseEntity<Page<AdminDTO>> getAdmin(

        @RequestParam(value = "page",         defaultValue = "0") Integer page,
        @RequestParam(value = "linesPerPage", defaultValue = "6") Integer linesPerPage,
        @RequestParam(value = "direction",    defaultValue = "ASC") String direction,
        @RequestParam(value = "orderBy",      defaultValue = "id") String orderBy,
        @RequestParam(value = "name",         defaultValue = "") String name,
        @RequestParam(value = "email",        defaultValue = "") String email
    ){

        
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
        
        Page <AdminDTO> list = serviceadmin.getAdmin(pageRequest, name, email);
        
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("{id}")
    public ResponseEntity<AdminDTO> getClientById(@PathVariable Long id){
        AdminDTO dto = serviceadmin.getAdminById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<AdminDTO> insert(@Valid @RequestBody AdminInsertDTO insertDTO){
        AdminDTO dto = serviceadmin.insert(insertDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        serviceadmin.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<AdminDTO> update(@RequestBody AdminUpdateDTO updateDTO, @PathVariable Long id) {
        AdminDTO dto = serviceadmin.update(id, updateDTO);
        
        return ResponseEntity.ok().body(dto);
    }
    
}
