package mx.unam.aragon.ico.te.h_de_ss_b.entidades;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Alumnos")
@Data
@NoArgsConstructor
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Llave Primaria para los alumnos

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido_paterno", nullable = false, length = 100)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", nullable = false, length = 100)
    private String apellidoMaterno;

    @Column(name = "numero_de_cuenta", nullable = false, unique = true, length = 100)
    private String numeroDeCuenta;

    @Column(nullable = false)
    private String carrera;

    @Column(name= "correo_electronico", nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true, length = 10)
    private String telefono;

    @Column(name = "programa_servicio_social", nullable = false)
    private String programaServicioSocial;

    @Column(name = "estado_alumno", nullable = false, length = 100)
    private String estadoAlumno;

    @Column(name = "total_horas")
    private Integer totalHoras;


}
