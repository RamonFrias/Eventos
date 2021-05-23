package ac1.facens.poii.sistema.eventos.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import ac1.facens.poii.sistema.eventos.entities.Admin;
import ac1.facens.poii.sistema.eventos.entities.Event;

public class EventDTO {

    private Long id;

    private Admin admin;

    private String name;
    private String description;
    private String place;
//  DATA E HORA
    private LocalDate start_date;
    private LocalDate end_date;
    private LocalTime start_time;
    private LocalTime end_time;
//  EMAIL PARA CONTATO
    private String email_contact;
// Agrecentando Ac2
    private Long amountFreeTickets;
    private Long amountPayedTickets;  
    private Double priceTicket;


    public EventDTO(){ //construtor padrao

    }
    
    public EventDTO(Long id, String name, String description, String place, LocalDate start_date, LocalDate end_date, LocalTime  start_time,  LocalTime  end_time, String email_contact, Long amountFreeTickets, Long amountPayedTickets, Double priceTicket){
        //declaração
        this.id = id;
        this.name = name;
        this.description = description;
        this.place = place;
        this.start_date = start_date;
        this.end_date = end_date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.email_contact = email_contact;
        this.amountFreeTickets = amountFreeTickets;
        this.amountPayedTickets = amountPayedTickets;
        this.priceTicket = priceTicket;
    }

    public EventDTO(Event ev) {
        this.id = ev.getId(); 
        this.name = ev.getName();
        this.description = ev.getDescription();
        this.start_date = ev.getStart_date();
        this.end_date = ev.getEnd_date();
        this.start_time = ev.getStart_time();
        this.end_time = ev.getEnd_time();
        this.email_contact = ev.getEmail_contact();
        this.amountFreeTickets = ev.getAmountFreeTickets();
        this.amountPayedTickets = ev.getAmountPayedTickets();
        this.priceTicket = ev.getPriceTicket();
    }

   public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
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

    public Double getPriceTicket() {
        return priceTicket;
    }

    public void setPriceTicket(Double priceTicket) {
        this.priceTicket = priceTicket;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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
