package mx.unam.aragon.ico.te.h_de_ss_b.Servicios;
import mx.unam.aragon.ico.te.h_de_ss_b.Modelo.Usuario;
import mx.unam.aragon.ico.te.h_de_ss_b.Repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String numero) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNumero(numero)
                .orElseThrow(() -> new UsernameNotFoundException("El número " + numero + " no existe."));
        return new User(
                usuario.getNumero(),
                usuario.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(usuario.getRol().name()))
        );
    }
}