package mx.unam.aragon.ico.te.h_de_ss_b.Modelo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alumnos")
@Data
@NoArgsConstructor
public class Alumno {

    @Column(nullable = false, length = 100)
    private String nombre; 

    @Column(name = "apellido_paterno", nullable = false, length = 100)
    private String apellidoPaterno; 

    @Column(name = "apellido_materno", length = 100)
    private String apellidoMaterno; 

    @Id
    @Column(name = "numero_cuenta", nullable = false, unique = true, length = 10)
    private long numeroCuenta;

    @Column(nullable = false)
    private String carrera; 

    @Column(nullable = false, unique = true)
    private String correo; 

    private String telefono; 

    @Column(nullable = false)
    private String estado;

    @Column(name = "horas_acumuladas", length = 10)
    private String horasAcumuladas = "0:00";

    @ManyToOne
    @JoinColumn(name = "id_programa", nullable = false)
    private ProgramaServicioSocial programa;

}
