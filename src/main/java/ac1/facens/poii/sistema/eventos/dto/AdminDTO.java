package ac1.facens.poii.sistema.eventos.dto;

import ac1.facens.poii.sistema.eventos.entities.Admin;

public class AdminDTO {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    
    public AdminDTO() {

    }
    public AdminDTO(Long id, String name, String email, String plhoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = plhoneNumber;
    }
    public AdminDTO(Admin ad) {
        this.id = ad.getId();
        this.name = ad.getName();
        this.email= ad.getEmail();
        this.phoneNumber = ad.getPhoneNumber();
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
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
