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

import ac1.facens.poii.sistema.eventos.dto.AttendeeDTO;
import ac1.facens.poii.sistema.eventos.dto.AttendeeInsertDTO;
import ac1.facens.poii.sistema.eventos.dto.AttendeeUpdateDTO;
import ac1.facens.poii.sistema.eventos.entities.Attendee;
import ac1.facens.poii.sistema.eventos.repositories.AttendeeRepositorio;

@Service
public class AttendeeService {
    
    @Autowired
    private AttendeeRepositorio repositoryAttendee;
    
    public Page<AttendeeDTO> getAttendee(PageRequest pageRequest, String name, String email) {
        
        Page<Attendee> list = repositoryAttendee.find(pageRequest, name, email);

        return list.map( at -> new AttendeeDTO(at));

    }
    
    public AttendeeDTO getAttendeeById(Long id){

        Optional<Attendee> op = repositoryAttendee.findById(id);

        Attendee at = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Attendee not found"));

        return new AttendeeDTO(at);
    }

    public AttendeeDTO insert(AttendeeInsertDTO dto) {
        Attendee entity = new Attendee(dto);
        if(entity.getBalance() < 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Balance cannot be negative");
        entity = repositoryAttendee.save(entity);
        return new AttendeeDTO(entity);
    }
    public AttendeeDTO update(Long id, AttendeeUpdateDTO dto){
        try{
            Attendee entity = repositoryAttendee.getOne(id);
            entity.setName(dto.getName());
            entity.setEmail(dto.getEmail());
            entity.setBalance(dto.getBalance());
            entity = repositoryAttendee.save(entity);
    
            return new AttendeeDTO(entity);
        }
        catch(EntityNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attendee not found");
        }
    }
    
    public void delete(Long id){
        try {
            repositoryAttendee.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Attendee not found");
        }
    }
}
