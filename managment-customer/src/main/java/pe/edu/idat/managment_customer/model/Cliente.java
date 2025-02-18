package pe.edu.idat.managment_customer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "Clientes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="ClienteId", nullable = false)
    private Long clienteId;

    @Column(name ="tipoCliente", nullable = false, length = 20)
    private String tipoCliente;

    @Column(name ="Nombre", nullable = false, length = 100)
    private String nombre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DocumentoID", referencedColumnName = "DocumentoID")
    private Documento documento;

    @Column(name ="Direccion", nullable = false, length = 255)
    private String direccion;

    @Column(name ="Telefono", nullable = false, length = 20)
    private String telefono;

    @Column(name ="Email", nullable = false, length = 100)
    private String email;

    @Column(name ="CreatedAt", nullable = false)
    private Timestamp createdAt;

    @Column(name ="UpdatedAt", nullable = false)
    private Timestamp updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = new Timestamp(System.currentTimeMillis());
        updatedAt = createdAt;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }
}