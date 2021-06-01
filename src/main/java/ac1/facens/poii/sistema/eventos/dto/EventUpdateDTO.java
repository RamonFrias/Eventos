package ac1.facens.poii.sistema.eventos.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import ac1.facens.poii.sistema.eventos.entities.Admin;

public class EventUpdateDTO {
    
    private Admin admin; 
    private String name;
    private String description;
    private String place;
//  DATA E HORA
    private LocalDate start_date;
    private LocalDate end_date;
    private LocalTime start_time;
    private LocalTime  end_time;
//  EMAIL PARA CONTATO
    private String email_contact;
// Agrecentando Ac2
    private Long amountFreeTickets;
    private Long amountPayedTickets; 
    private Long freeTickectsSelled; 
    private Long payedTickectsSelled; 
    private Double priceTicket;

    public Long getAmountFreeTickets() {
        return amountFreeTickets;
    }


    public Admin getAdmin() {
        return admin;
    }


    public void setAdmin(Admin admin) {
        this.admin = admin;
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

    public Long getFreeTickectsSelled() {
        return freeTickectsSelled;
    }

    public void setFreeTickectsSelled(Long freeTickectsSelled) {
        this.freeTickectsSelled = freeTickectsSelled;
    }

    public Long getPayedTickectsSelled() {
        return payedTickectsSelled;
    }

    public void setPayedTickectsSelled(Long payedTickectsSelled) {
        this.payedTickectsSelled = payedTickectsSelled;
    }

    public Double getPriceTicket() {
        return priceTicket;
    }

    public void setPriceTicket(Double priceTicket) {
        this.priceTicket = priceTicket;
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
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
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
    
}
