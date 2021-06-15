package ac1.facens.poii.sistema.eventos.dto;

import java.time.Instant;

import ac1.facens.poii.sistema.eventos.entities.Attendee;
import ac1.facens.poii.sistema.eventos.entities.TicketType;

public class TicketInsertDTO {
    
    private TicketType type;

    private Attendee atendee;
    
    public TicketType getType() {
        return type;
    }
    public void setType(TicketType type) {
        this.type = type;
    }
    public Attendee getAtendee() {
        return atendee;
    }
    public void setAtendee(Attendee atendee) {
        this.atendee = atendee;
    }
    
}
