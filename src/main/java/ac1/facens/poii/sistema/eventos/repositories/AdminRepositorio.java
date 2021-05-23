package ac1.facens.poii.sistema.eventos.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ac1.facens.poii.sistema.eventos.entities.Admin;

@Repository
public interface AdminRepositorio extends JpaRepository<Admin,Long>{
    
    //@Query("SELECT pl FROM Admin pl ")
    
    @Query("SELECT ad FROM Admin ad " + 
           "WHERE " +
           "LOWER(ad.name)           LIKE   LOWER(CONCAT('%', :name, '%'))          AND " +
           "LOWER(ad.email)          LIKE   LOWER(CONCAT('%', :email, '%'))")

    public Page<Admin>find(Pageable pageResquest, String name, String email);
}
