package ac1.facens.poii.sistema.eventos.dto;

import java.time.Instant;

import ac1.facens.poii.sistema.eventos.entities.Ticket;
import ac1.facens.poii.sistema.eventos.entities.TicketType;

public class TicketDTO {

    private long id;
    private TicketType type;
    private Instant date;
    private double price;
    
    public TicketDTO() {

    }
    public TicketDTO(long id, TicketType type, Instant date, double price) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.price = price;
    }
    public TicketDTO(Ticket tk) {
        this.id = tk.getId();
        this.type = tk.getType();
        this.date = tk.getDate();
        this.price = tk.getPrice();
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
    public TicketType getType() {
        return type;
    }
    public void setType(TicketType type) {
        this.type = type;
    }
    
    
}
