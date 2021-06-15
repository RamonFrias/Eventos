package ac1.facens.poii.sistema.eventos.dto;

import java.util.List;

import ac1.facens.poii.sistema.eventos.entities.Event;
import ac1.facens.poii.sistema.eventos.entities.Ticket;

public class EventTicketDTO {
    
    private Long amountFreeTickets;
    private Long amountPayedTickets;
    private Long amountFreeTicketsSold;
    private Long amountPayedTicketsSold;  

    private List<Ticket> ticket;

    public EventTicketDTO(){

    }

    // public EventTicketDTO(Long id, Long amountFreeTickets, Long amountPayedTickets, List<Ticket> ticket) {
    //     this.id = id;
    //     this.amountFreeTickets = amountFreeTickets;
    //     this.amountPayedTickets = amountPayedTickets;
    //     this.ticket = ticket;
    // }

    public EventTicketDTO(Event tk, Long free, Long payed) {
        this.amountFreeTicketsSold = free;
        this.amountPayedTicketsSold = payed;
        this.amountFreeTickets = tk.getAmountFreeTickets();
        this.amountPayedTickets = tk.getAmountPayedTickets();
        this.ticket = tk.getTicket();
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

    public List<Ticket> getTicket() {
        return ticket;
    }

    public void setTicket(List<Ticket> ticket) {
        this.ticket = ticket;
    }

    public Long getAmountFreeTicketsSold() {
        return amountFreeTicketsSold;
    }

    public void setAmountFreeTicketsSold(Long amountFreeTicketsSold) {
        this.amountFreeTicketsSold = amountFreeTicketsSold;
    }

    public Long getAmountPayedTicketsSold() {
        return amountPayedTicketsSold;
    }

    public void setAmountPayedTicketsSold(Long amountPayedTicketsSold) {
        this.amountPayedTicketsSold = amountPayedTicketsSold;
    }
     
}
