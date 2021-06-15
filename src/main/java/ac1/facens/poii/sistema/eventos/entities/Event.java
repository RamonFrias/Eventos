package ac1.facens.poii.sistema.eventos.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ac1.facens.poii.sistema.eventos.dto.EventInsertDTO;

@Entity
@Table(name="Tb_event")
public class Event implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "admin_ID")
    private Admin admin;

    @NotEmpty(message = "Please enter a user name")
    @NotNull
    private String name;
    @NotEmpty(message = "Please enter a user description")
    @NotNull
    private String description;
//  DATA E HORA
    private LocalDate start_date;
    private LocalDate end_date;
    private LocalTime start_time;
    private LocalTime end_time;
//  EMAIL PARA CONTATO
    @NotEmpty(message = "Please enter a user email")
    @Email
    private String email_contact;
// Agrecentando Ac2
    private Long amountFreeTickets; //15 /// -- 1 -> variavelja vendidos -> 
    private Long amountPayedTickets;
    private Double priceTicket;

    @OneToMany
    @JoinColumn(name="event_id")
    private List<Ticket> ticket = new ArrayList<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "Tb_event_place",
        joinColumns = @JoinColumn(name = "event_id"),
        inverseJoinColumns = @JoinColumn(name = "place_id")
    )
    private List<Place> places = new ArrayList<>();
  
    public Event(){
        
    }

    public Event(EventInsertDTO dto) {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.start_date = dto.getStart_date();
        this.end_date = dto.getEnd_date();
        this.start_time = dto.getStart_time();
        this.end_time = dto.getEnd_time();
        this.email_contact = dto.getEmail_contact();
        this.amountFreeTickets = dto.getAmountFreeTickets();
        this.amountPayedTickets = dto.getAmountPayedTickets();
        this.priceTicket = dto.getPriceTicket();
    }

    public Event(Event ev) {
        this.id = ev.getId();
        this.admin = ev.getAdmin();
        this.name = ev.getName();
        this.description = ev.getDescription();
        this.start_date = ev.getStart_date();
        this.end_date = ev.getEnd_date();
        this.start_time = ev.getStart_time();
        this.end_time = ev.getEnd_time();
        this.email_contact = ev.getEmail_contact();
        this.amountFreeTickets = ev.getAmountFreeTickets();
        this.amountPayedTickets = ev.getAmountPayedTickets();
        this.priceTicket = ev.getPriceTicket();
        this.places = ev.getPlaces();
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public LocalDate getStart_date() {
        return start_date;
    }
    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }
    public LocalDate getEnd_date() {
        return end_date;
    }
    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }
    public LocalTime  getStart_time() {
        return start_time;
    }
    public void setStart_time(LocalTime start_time) {
        this.start_time = start_time;
    }
    public LocalTime  getEnd_time() {
        return end_time;
    }
    public void setEnd_time(LocalTime end_time) {
        this.end_time = end_time;
    }
    public String getEmail_contact() {
        return email_contact;
    }
    public void setEmail_contact(String email_contact) {
        this.email_contact = email_contact;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getAmountFreeTickets() {
        return amountFreeTickets;
    }

    public void setAmountFreeTickets(Long amountFreeTickets) {
        this.amountFreeTickets = amountFreeTickets;
    }

    public Long getAmountPayedTickets() {
        return amountPayedTickets;
    }

    public void setAmountPayedTickets(Long amountPayedTickets) {
        this.amountPayedTickets = amountPayedTickets;
    }

    public Double getPriceTicket() {
        return priceTicket;
    }

    public void setPriceTicket(Double priceTicket) {
        this.priceTicket = priceTicket;
    }
    

    public List<Place> getPlaces() {
        return places;
    }

    public void addPlaces(Place places) {
        this.places.add(places);
    }

    public void deletePlace(Place eventPlace){
        this.places.remove(eventPlace);
    }

    public void removePlace(Place eventPlace){
        places.remove(eventPlace);
    }
//TICKET
     public List<Ticket> getTicket() {
        return ticket;
    }

    public void addTicket(Ticket ticket) {
        this.ticket.add(ticket);
    }

    public void removeTickets() {
       this.ticket.removeAll(ticket);
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
        Event other = (Event) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
}
