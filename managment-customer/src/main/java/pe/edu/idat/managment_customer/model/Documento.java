package pe.edu.idat.managment_customer.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Documentos", uniqueConstraints = @UniqueConstraint(columnNames = {"TipoDocumento", "NumeroDocumento"}))
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Documento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DocumentoID")
    private Long documentoId;

    @Column(name = "TipoDocumento", length = 20, nullable = false)
    private String tipoDocumento;

    @Column(name = "NumeroDocumento", length = 30, nullable = false)
    private String numeroDocumento;

    @Column(name = "FechaEmision")
    private Date fechaEmision;

    @Column(name = "Pais", length = 50)
    private String pais;
}