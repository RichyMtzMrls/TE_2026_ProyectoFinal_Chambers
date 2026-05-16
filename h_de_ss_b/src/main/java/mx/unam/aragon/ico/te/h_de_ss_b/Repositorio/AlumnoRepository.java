package mx.unam.aragon.ico.te.h_de_ss_b.Repositorio;
import mx.unam.aragon.ico.te.h_de_ss_b.Modelo.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    Optional<Alumno> findByNumeroCuenta(String numeroCuenta);
}