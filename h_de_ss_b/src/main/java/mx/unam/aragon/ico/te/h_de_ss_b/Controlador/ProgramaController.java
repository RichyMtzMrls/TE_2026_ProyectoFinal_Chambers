package mx.unam.aragon.ico.te.h_de_ss_b.Controlador;
import mx.unam.aragon.ico.te.h_de_ss_b.Modelo.ProgramaServicioSocial;
import mx.unam.aragon.ico.te.h_de_ss_b.Repositorio.ProgramaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programas")
public class ProgramaController {

    @Autowired
    private ProgramaRepository programaRepository;

    @GetMapping
    public List<ProgramaServicioSocial> listarTodos() {
        return programaRepository.findAll();
    }

    @PostMapping
    public ProgramaServicioSocial guardar(@RequestBody ProgramaServicioSocial programa) {
        return programaRepository.save(programa);
    }

    @GetMapping("/{id}")
    public ProgramaServicioSocial obtenerPorId(@PathVariable Long id) {
        return programaRepository.findById(id).orElse(null);
    }
}