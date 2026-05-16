package mx.unam.aragon.ico.te.h_de_ss_b.Controlador;
import mx.unam.aragon.ico.te.h_de_ss_b.DTO.UsuarioInfoDTO;
import mx.unam.aragon.ico.te.h_de_ss_b.Modelo.Alumno;
import mx.unam.aragon.ico.te.h_de_ss_b.Modelo.Profesor;
import mx.unam.aragon.ico.te.h_de_ss_b.Modelo.Usuario;
import mx.unam.aragon.ico.te.h_de_ss_b.Repositorio.AlumnoRepository;
import mx.unam.aragon.ico.te.h_de_ss_b.Repositorio.ProfesorRepository;
import mx.unam.aragon.ico.te.h_de_ss_b.Repositorio.UsuarioRepository;
import mx.unam.aragon.ico.te.h_de_ss_b.Roles.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @PostMapping
    public Usuario guardar(@RequestBody Usuario usuario) {
        String passwordEncriptada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passwordEncriptada);
        
        return usuarioRepository.save(usuario);
    }

    @GetMapping("/me")
    public UsuarioInfoDTO obtenerMiInformacion(Authentication authentication) {
        String numeroAutenticado = authentication.getName();
        Usuario usuario = usuarioRepository.findByNumero(numeroAutenticado)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        UsuarioInfoDTO respuesta = new UsuarioInfoDTO();
        respuesta.setIdentificador(usuario.getNumero());
        respuesta.setRol(usuario.getRol().name());

        if (usuario.getRol() == Rol.ROLE_ALUMNO) {
            Alumno alumno = alumnoRepository.findByNumeroCuenta(numeroAutenticado).orElse(null);
            if (alumno != null) {
                respuesta.setMensajeBienvenida("¡Bienvenido, alumno " + alumno.getNombre() + "!");
            } else {
                respuesta.setMensajeBienvenida("Falta registro en tabla Alumnos.");
            }
        } 
        else if (usuario.getRol() == Rol.ROLE_PROFESOR || usuario.getRol() == Rol.ROLE_ADMIN) {
            Profesor profesor = profesorRepository.findByNumeroTrabajador(numeroAutenticado).orElse(null);
            if (profesor != null) {
                respuesta.setMensajeBienvenida("¡Bienvenido, profesor " + profesor.getNombre() + "!");
            } else {
                respuesta.setMensajeBienvenida("Falta registro en tabla Profesores.");
            }
        }

        return respuesta;
    }
}