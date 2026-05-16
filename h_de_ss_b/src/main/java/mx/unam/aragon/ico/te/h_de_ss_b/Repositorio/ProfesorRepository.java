package mx.unam.aragon.ico.te.h_de_ss_b.Repositorio;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import mx.unam.aragon.ico.te.h_de_ss_b.Modelo.Profesor;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> { 
    Optional<Profesor> findByNumeroTrabajador(String numeroTrabajador);
}