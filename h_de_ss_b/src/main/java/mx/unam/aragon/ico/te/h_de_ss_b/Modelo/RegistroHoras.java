package mx.unam.aragon.ico.te.h_de_ss_b.Modelo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "registros_horas")
@Data
@NoArgsConstructor
public class RegistroHoras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(name = "hora_entrada", nullable = false)
    private LocalTime horaEntrada;

    @Column(name = "hora_salida")
    private LocalTime horaSalida;

    @Column(name = "actividades_realizadas", columnDefinition = "TEXT")
    private String actividadesRealizadas; 

    @Column(name = "horas_calculadas", length = 10)
    private String horasCalculadas;

    @Column(name = "estado_registro", nullable = false, length = 20)
    private String estadoRegistro; 

    @ManyToOne
    @JoinColumn(name = "id_alumno", nullable = false)
    private Alumno alumno;
}