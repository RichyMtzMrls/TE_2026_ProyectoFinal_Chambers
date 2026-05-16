package mx.unam.aragon.ico.te.h_de_ss_b.Repositorio;

import mx.unam.aragon.ico.te.h_de_ss_b.Modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findByNumero(String numero);
    
}