package mx.unam.aragon.ico.te.h_de_ss_b.Servicios;
import mx.unam.aragon.ico.te.h_de_ss_b.Modelo.Alumno;
import mx.unam.aragon.ico.te.h_de_ss_b.Modelo.RegistroHoras;
import mx.unam.aragon.ico.te.h_de_ss_b.Repositorio.AlumnoRepository;
import mx.unam.aragon.ico.te.h_de_ss_b.Repositorio.RegistroHorasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Duration;
import java.time.LocalTime;

@Service
public class RegistroHorasService {

    @Autowired
    private RegistroHorasRepository registroRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;
    private static final long TOPE_MINUTOS_SERVICIO = 28800;

    @Transactional
    public RegistroHoras registrarHorasCompletas(RegistroHoras registro) {
        validarHorarios(registro.getHoraEntrada(), registro.getHoraSalida());
        
        long minutosTrabajados = calcularYValidarMinutos(registro.getHoraEntrada(), registro.getHoraSalida());
        registro.setHorasCalculadas(formatearMinutosAHora(minutosTrabajados));
        registro.setEstadoRegistro("CERRADO");
        actualizarHorasAlumno(registro.getAlumno().getNumeroCuenta(), minutosTrabajados);
        return registroRepository.save(registro);
    }

    public RegistroHoras registrarEntrada(RegistroHoras registro) {
        registro.setHoraSalida(null); 
        registro.setHorasCalculadas("0:00");
        registro.setEstadoRegistro("ABIERTO");
        return registroRepository.save(registro);
    }

    @Transactional
    public RegistroHoras registrarSalida(Long idRegistro, LocalTime horaSalida, String actividades) {
        RegistroHoras registro = registroRepository.findById(idRegistro)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
        if (registro.getEstadoRegistro().equals("CERRADO")) {
            throw new RuntimeException("Este registro ya tiene hora de salida");
        }

        validarHorarios(registro.getHoraEntrada(), horaSalida);
        registro.setHoraSalida(horaSalida);
        registro.setActividadesRealizadas(actividades);
        long minutosTrabajados = calcularYValidarMinutos(registro.getHoraEntrada(), horaSalida);
        registro.setHorasCalculadas(formatearMinutosAHora(minutosTrabajados));
        registro.setEstadoRegistro("CERRADO");
        actualizarHorasAlumno(registro.getAlumno().getNumeroCuenta(), minutosTrabajados);
        return registroRepository.save(registro);
    }

    private void validarHorarios(LocalTime entrada, LocalTime salida) {
        if (salida != null && (salida.isBefore(entrada) || salida.equals(entrada))) {
            throw new RuntimeException("Error: La hora de salida debe ser mayor a la hora de entrada.");
        }
    }

    private long calcularYValidarMinutos(LocalTime entrada, LocalTime salida) {
        long minutos = Duration.between(entrada, salida).toMinutes();
        
        if (minutos < 1) {
            throw new RuntimeException("El registro debe ser de al menos 1 minuto.");
        }
        if (minutos > 300) {
            throw new RuntimeException("Violación de regla: No se pueden registrar más de 5 horas continuas.");
        }
        return minutos;
    }

    private String formatearMinutosAHora(long minutosTotales) {
        long horas = minutosTotales / 60;
        long minutosRestantes = minutosTotales % 60;
        // Formato HH:mm asegurando que los minutos tengan dos dígitos (ej. 1:05)
        return String.format("%d:%02d", horas, minutosRestantes);
    }

    private void actualizarHorasAlumno(long idAlumno, long nuevosMinutos) {
        Alumno alumno = alumnoRepository.findById(idAlumno)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        long minutosActuales = 0;
        
        if (alumno.getHorasAcumuladas() != null && !alumno.getHorasAcumuladas().equals("0:00")) {
            String[] partes = alumno.getHorasAcumuladas().split(":");
            minutosActuales = (Long.parseLong(partes[0]) * 60) + Long.parseLong(partes[1]);
        }

        long totalMinutos = minutosActuales + nuevosMinutos;

        if (totalMinutos > TOPE_MINUTOS_SERVICIO) {
            throw new RuntimeException("Error: Con este registro el alumno superaría el tope oficial de 480 horas.");
        }

        alumno.setHorasAcumuladas(formatearMinutosAHora(totalMinutos));

        if (totalMinutos == TOPE_MINUTOS_SERVICIO) {
            alumno.setEstado("Concluido");
        }
        
        alumnoRepository.save(alumno);
    }
}