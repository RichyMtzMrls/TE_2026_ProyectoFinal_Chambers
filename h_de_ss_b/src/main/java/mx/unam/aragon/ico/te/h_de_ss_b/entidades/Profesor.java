package mx.unam.aragon.ico.te.h_de_ss_b.entidades;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Profesores")
@Data
@NoArgsConstructor
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Llave Primaria para los profesores

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido_paterno", nullable = false, length = 100)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", nullable = false, length = 100)
    private String apellidoMaterno;

    @Column(name = "numero_de_trabajador", nullable = false, unique = true, length = 100)
    private String numeroTrabajador;

    @Column(nullable = false)
    private String carrera;

    @Column(nullable = false)
    private String facultad;

    @Column(name= "correo_electronico", nullable = false, unique = true)
    private String email;

}
