package mx.unam.aragon.ico.te.h_de_ss_b.Controlador;
import mx.unam.aragon.ico.te.h_de_ss_b.Modelo.RegistroHoras;
import mx.unam.aragon.ico.te.h_de_ss_b.Servicios.RegistroHorasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registros")
public class RegistroHorasController {

    @Autowired
    private RegistroHorasService registroService;

    @PostMapping("/completos")
    public RegistroHoras registrarCompletos(@RequestBody RegistroHoras registro) {
        return registroService.registrarHorasCompletas(registro);
    }

    @PostMapping("/entrada")
    public RegistroHoras registrarEntrada(@RequestBody RegistroHoras registro) {
        return registroService.registrarEntrada(registro);
    }

    @PutMapping("/{id}/salida")
    public RegistroHoras registrarSalida(@PathVariable Long id, @RequestBody RegistroHoras detallesSalida) {
        return registroService.registrarSalida(id, detallesSalida.getHoraSalida(), detallesSalida.getActividadesRealizadas());
    }
}