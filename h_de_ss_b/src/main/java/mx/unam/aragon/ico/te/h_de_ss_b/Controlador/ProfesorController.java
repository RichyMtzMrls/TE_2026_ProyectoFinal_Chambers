package mx.unam.aragon.ico.te.h_de_ss_b.Controlador;

import mx.unam.aragon.ico.te.h_de_ss_b.Modelo.Profesor;
import mx.unam.aragon.ico.te.h_de_ss_b.Repositorio.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorRepository profesorRepository;

    @GetMapping
    public List<Profesor> listarTodos() {
        return profesorRepository.findAll();
    }

    @PostMapping
    public Profesor guardar(@RequestBody Profesor profesor) {
        return profesorRepository.save(profesor);
    }
    
    @GetMapping("/{id}")
    public Profesor obtenerPorId(@PathVariable Long id) {
        return profesorRepository.findById(id).orElse(null);
    }
}