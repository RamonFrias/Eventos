package ac1.facens.poii.sistema.eventos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ac1.facens.poii.sistema.eventos.dto.PLaceInsertDTO;

@Entity
@Table(name="Tb_place")
public class Place implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    
    @NotEmpty(message = "Please enter a user name")
    @NotNull
    private String name;
    @NotEmpty(message = "Please enter a user address")
    @NotNull
    private String address;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "Tb_event_place",
        joinColumns = @JoinColumn(name = "place_id"),
        inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> events = new ArrayList<>();

    public Place() {

    }
    public Place(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
    public Place(Place pl){
        this.id = pl.getId();
        this.name = pl.getName();
        this.address = pl.getAddress();
    }
    
    public Place(PLaceInsertDTO dto) {
        this.name = dto.getName();
        this.address = dto.getAddress();
    }

    public List<Event> getEvents() {
        return events;
    }

    public void addEvents(Event events) {
        this.events.add(events);
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public void removePlace(Event eventPlace){
        events.remove(eventPlace);
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Place other = (Place) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
