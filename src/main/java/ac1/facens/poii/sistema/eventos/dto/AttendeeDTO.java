package ac1.facens.poii.sistema.eventos.dto;

import ac1.facens.poii.sistema.eventos.entities.Attendee;

public class AttendeeDTO {
    
    private Long id;
    private String name;
    private String email;
    private double balance;

    public AttendeeDTO() {

    }

    public AttendeeDTO(Long id, String name, String email, double balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.balance = balance;
    }
    public AttendeeDTO(Attendee at){
        this.id = at.getId();
        this.name = at.getName();
        this.email = at.getEmail();
        this.balance = at.getBalance();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
