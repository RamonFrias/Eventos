package ac1.facens.poii.sistema.eventos.dto;

import java.time.Instant;

public class TicketUpdateDTO {

    private Enum type;
    private Instant date;
    private double price;
    
    public Enum getType() {
        return type;
    }
    public void setType(Enum type) {
        this.type = type;
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


    
}
