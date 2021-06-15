package ac1.facens.poii.sistema.eventos.entities;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ac1.facens.poii.sistema.eventos.dto.TicketInsertDTO;

@Entity
@Table(name = "Tb_ticket")
public class Ticket implements Serializable{
    
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TicketType type;
    private Instant date;
    private Double price;

    // @ManyToOne
    // @JoinColumn(name = "TicketFREE_id")
    // private Event eventFREE;

    @JsonIgnore
    @ManyToOne
    // @JoinTable(
    //     name = "Tb_event_Tickets",
    //     joinColumns = @JoinColumn(name = "FREE_ID"),
    //     inverseJoinColumns = @JoinColumn(name = "PAYED_ID")
    // )
    private Event event;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "atendee_ID")
    private Attendee atendee;

    // @OneToMany(cascade = CascadeType.PERSIST)
    // @JoinColumn(name = "TicketFREE_id")
    // private List<Event> freeTickets = new ArrayList<>();

    // @OneToMany(cascade = CascadeType.PERSIST)
    // @JoinColumn(name = "TicketPAYED_id")
    // private List<Event> payedTickets = new ArrayList<>();
    
    

    public Ticket(){

    }

    public Ticket(TicketInsertDTO dto, Double price, Event event) {
        this.type = dto.getType();
        this.date = Instant.now();
        this.price = price;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Instant getDate() {
        return date;
    }
    public void setDate(Instant date) {
        this.date = date;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public static Long getSerialversionuid() {
        return serialVersionUID;
    }
    public TicketType getType() {
        return type;
    }
    public void setType(TicketType type) {
        this.type = type;
    }
    //ticket
    // public List<Event> getFreeTickets() {
    //     return freeTickets;
    // }
    public Event getEvent() {
        return event;
    }
    public void setEvent(Event event) {
        this.event = event;
    }

    public Attendee getAtendee() {
        return atendee;
    }
    
    public void setAtendee(Attendee atendee) {
        this.atendee = atendee;
    }

    // public void addFreeTickets(Event freeTickets) {
    //     if(freeTickets.getType() == TicketType.FREE){
    //         this.freeTickets.add(freeTickets);
    //     }
    // }

    // public List<Event> getPaidTickets() {
    //     return payedTickets;
    // }

    // public void addPayedTickets(Event payedTickets) {
    //     Ticket ticket = ;
    //     if(payedTickets.getType() == TicketType.PAYED){
    //         this.payedTickets.add(payedTickets);
    //     }
    // }    
    
}
