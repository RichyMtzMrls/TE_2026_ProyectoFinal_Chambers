package mx.unam.aragon.ico.te.h_de_ss_b.Controlador;
import mx.unam.aragon.ico.te.h_de_ss_b.Seguridad.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> credenciales) {
        String numero = credenciales.get("numero");
        String password = credenciales.get("password");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(numero, password));
        UserDetails userDetails = userDetailsService.loadUserByUsername(numero);
        String jwt = jwtUtil.generarToken(userDetails);
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("token", jwt);
        return respuesta;
    }
}