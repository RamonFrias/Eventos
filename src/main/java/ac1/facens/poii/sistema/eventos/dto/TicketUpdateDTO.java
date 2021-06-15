package ac1.facens.poii.sistema.eventos.dto;

import java.time.Instant;

import ac1.facens.poii.sistema.eventos.entities.TicketType;

public class TicketUpdateDTO {
   
    private TicketType type;
    private Instant date;
    private Double price;

    public TicketType getType() {
        return type;
    }
    public void setType(TicketType type) {
        this.type = type;
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
}
