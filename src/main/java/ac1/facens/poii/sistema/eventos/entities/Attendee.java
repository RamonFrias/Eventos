package ac1.facens.poii.sistema.eventos.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import ac1.facens.poii.sistema.eventos.dto.AttendeeInsertDTO;

@Entity
@Table(name="Tb_attendee")
@PrimaryKeyJoinColumn(name="BASEUSER_ID")
public class Attendee extends BaseUser{
    
    private Double balance;

    @OneToMany(mappedBy = "atendee")
    private List<Ticket> tickets = new ArrayList<>();
    
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Ticket tickets) {
        this.tickets.add(tickets);
    }

    public Attendee(){

    }

    public Attendee(Double balance) {
        this.balance = balance;
    }

    public Attendee(Long id, String name, String email, Double balance) {
       super(id, name, email);
       this.balance = balance;
   }
    
    public Attendee(AttendeeInsertDTO dto) {
        super.setName(getName());
        super.setEmail(getEmail());
        this.balance = dto.getBalance();
    }

    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    
}
