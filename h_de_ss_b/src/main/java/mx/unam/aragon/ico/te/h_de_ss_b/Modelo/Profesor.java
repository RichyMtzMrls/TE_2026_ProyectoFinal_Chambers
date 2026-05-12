package mx.unam.aragon.ico.te.h_de_ss_b.Modelo;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "profesores")
@Data
@NoArgsConstructor
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido_paterno", nullable = false, length = 100)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", length = 100)
    private String apellidoMaterno;

    @Column(name = "numero_trabajador", nullable = false, unique = true, length = 20)
    private String numeroTrabajador;

    @Column(nullable = false)
    private String carrera; 

    @Column(nullable = false)
    private String facultad;

    @Column(nullable = false, unique = true)
    private String correo;

    @OneToMany(mappedBy = "profesorResponsable") 
    private List<ProgramaServicioSocial> programas;
}