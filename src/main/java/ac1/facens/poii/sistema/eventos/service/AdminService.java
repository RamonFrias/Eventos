package ac1.facens.poii.sistema.eventos.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;
import ac1.facens.poii.sistema.eventos.dto.AdminDTO;
import ac1.facens.poii.sistema.eventos.dto.AdminInsertDTO;
import ac1.facens.poii.sistema.eventos.dto.AdminUpdateDTO;
import ac1.facens.poii.sistema.eventos.entities.Admin;
import ac1.facens.poii.sistema.eventos.repositories.AdminRepositorio;

@Service
public class AdminService {
    
    @Autowired
    private AdminRepositorio repositoryadmin;
    
    
    public Page<AdminDTO> getAdmin(PageRequest pageRequest, String name, String address) {
        
        Page<Admin> list = repositoryadmin.find(pageRequest, name, address);

        return list.map( ad -> new AdminDTO(ad));
    }
    
    public AdminDTO getAdminById(Long id){

        Optional<Admin> op = repositoryadmin.findById(id);

        Admin ad = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found"));

        return new AdminDTO(ad);
    }

    public AdminDTO insert(AdminInsertDTO dto) {
        Admin entity = new Admin(dto);
        entity = repositoryadmin.save(entity);
        return new AdminDTO(entity);
    }

    public AdminDTO update(Long id, AdminUpdateDTO dto){
        try{
            Admin entity = repositoryadmin.getOne(id);
            entity.setName(dto.getName());
            entity.setEmail(dto.getEmail());
            entity.setPhoneNumber(dto.getPhoneNumber());
            entity = repositoryadmin.save(entity);
    
            return new AdminDTO(entity);
        }
        catch(EntityNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found");
        }
    }
    public void delete(Long id){
        try {
            repositoryadmin.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found");
        }
    }

}
