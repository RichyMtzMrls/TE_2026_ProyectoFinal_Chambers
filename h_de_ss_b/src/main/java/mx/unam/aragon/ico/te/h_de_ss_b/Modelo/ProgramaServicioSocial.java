package mx.unam.aragon.ico.te.h_de_ss_b.Modelo;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "programas_servicio")
@Data  
@NoArgsConstructor 
public class ProgramaServicioSocial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String nombrePrograma; 

    @Column(columnDefinition = "TEXT")
    private String descripcion; 

    @Column(name = "area_responsable", length = 100)
    private String areaResponsable; 

    @Column(name = "fecha_inicio", nullable = false)
    private LocalDate fechaInicio; 

    @Column(name = "fecha_termino")
    private LocalDate fechaTermino; 

    @Column(name = "horas_requeridas", nullable = false)
    private Integer horasRequeridas; 

    @Column(nullable = false, length = 20)
    private String estado; 
    // --- Relaciones ---
    // Requerimiento: Profesor o profesores asignados (Relación Muchos a Muchos o Uno a Muchos dependiendo de tu diseño)
    // Dejaremos esto pendiente hasta crear la entidad Profesor, pero aquí iría la anotación de relación, por ejemplo:
    // @ManyToOne
    // @JoinColumn(name = "profesor_id")
    // private Profesor profesorAsignado;

}
