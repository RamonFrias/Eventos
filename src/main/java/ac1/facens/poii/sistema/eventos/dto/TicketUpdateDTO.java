package ac1.facens.poii.sistema.eventos.dto;

import java.time.Instant;

import ac1.facens.poii.sistema.eventos.entities.TicketType;

public class TicketUpdateDTO {

    private TicketType type;
    private Instant date;
    private double price;
    

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


    
}
