package ac1.facens.poii.sistema.eventos.entities;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ac1.facens.poii.sistema.eventos.dto.TicketInsertDTO;

@Entity
@Table(name="tb_ticket")
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private TicketType type; // depende do evento
    private Instant date;
    private double price;
    
    public Ticket(long id, TicketType type, Instant date, double price) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.price = price;
    }
    public Ticket(TicketInsertDTO dto) {
        this.type = dto.getType();
        this.date = dto.getDate();
        this.price = dto.getPrice();
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public Instant getDate() {
        return date;
    }
    public void setDate(Instant date) {
        this.date = date;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public TicketType getType() {
        return type;
    }
    public void setType(TicketType type) {
        this.type = type;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
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
        Ticket other = (Ticket) obj;
        if (id != other.id)
            return false;
        return true;
    }
    
}
