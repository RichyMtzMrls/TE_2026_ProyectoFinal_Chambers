package mx.unam.aragon.ico.te.h_de_ss_b.Controlador;
import mx.unam.aragon.ico.te.h_de_ss_b.Modelo.Alumno;
import mx.unam.aragon.ico.te.h_de_ss_b.Repositorio.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @GetMapping
    public List<Alumno> listarTodos() {
        return alumnoRepository.findAll();
    }

    @PostMapping
    public Alumno guardar(@RequestBody Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    @GetMapping("/{id}")
    public Alumno obtenerPorId(@PathVariable Long id) {
        return alumnoRepository.findById(id).orElse(null);
    }
}