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

import ac1.facens.poii.sistema.eventos.dto.PLaceInsertDTO;
import ac1.facens.poii.sistema.eventos.dto.PlaceDTO;
import ac1.facens.poii.sistema.eventos.dto.PlaceUpdateDTO;
import ac1.facens.poii.sistema.eventos.entities.Place;
import ac1.facens.poii.sistema.eventos.repositories.PlaceRepositorio;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepositorio repositoryplace;
    
    public Page<PlaceDTO> getPlace(PageRequest pageRequest, String name, String address) {
        
        Page<Place> list = repositoryplace.find(pageRequest, name, address);

        return list.map( pl -> new PlaceDTO(pl));
    }
    
    public PlaceDTO getPlaceById(Long id){

        Optional<Place> op = repositoryplace.findById(id);

        Place pl = op.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found"));

        return new PlaceDTO(pl);
    }

    public PlaceDTO insert(PLaceInsertDTO dto) {
        Place entity = new Place(dto);
        entity = repositoryplace.save(entity);
        return new PlaceDTO(entity);
    }
    public PlaceDTO update(Long id, PlaceUpdateDTO dto){
        try{
            Place entity = repositoryplace.getOne(id);
            entity.setName(dto.getName());
            entity.setAddress(dto.getAddress());
            entity = repositoryplace.save(entity);
    
            return new PlaceDTO(entity);
        }
        catch(EntityNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found");
        }
    }
    
    public void delete(Long id){
        try {
            repositoryplace.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Place not found");
        }
    }
}
