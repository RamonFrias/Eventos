package ac1.facens.poii.sistema.eventos.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import ac1.facens.poii.sistema.eventos.dto.AdminInsertDTO;


@Entity
@Table(name="Tb_adimin")
@PrimaryKeyJoinColumn(name="BASEUSER_ID")
public class Admin extends BaseUser{


    @NotEmpty(message = "Please enter a user phoneNumber")
    @NotNull
    private String phoneNumber;

    @OneToMany(mappedBy = "admin")
    private List<Event> events = new ArrayList<>();


    public Admin() {

    }
    public Admin(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Admin(Long id, String name, String email, String phoneNumber) {
        super(id, name, email);
        this.phoneNumber = phoneNumber;
    }
    
    public Admin(AdminInsertDTO dto) {
        super.setName(dto.getName());
        super.setEmail(dto.getEmail());
        this.phoneNumber = dto.getPhoneNumber();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
}
