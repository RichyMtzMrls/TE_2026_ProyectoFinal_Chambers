package mx.unam.aragon.ico.te.h_de_ss_b.Modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alumnos")
@Data
@NoArgsConstructor
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre; 

    @Column(name = "apellido_paterno", nullable = false, length = 100)
    private String apellidoPaterno; 

    @Column(name = "apellido_materno", length = 100)
    private String apellidoMaterno; 

    @Column(name = "numero_cuenta", nullable = false, unique = true, length = 10)
    private String numeroCuenta; 

    @Column(nullable = false)
    private String carrera; 

    @Column(nullable = false, unique = true)
    private String correo; 

    private String telefono; 

    @Column(nullable = false)
    private String estado;

    @Column(name = "horas_acumuladas")
    private Integer horasAcumuladas = 0; 

    @ManyToOne
    @JoinColumn(name = "id_programa", nullable = false)
    private ProgramaServicioSocial programa;

}
