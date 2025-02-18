package pe.edu.idat.managment_customer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;


@Entity
@Table(name = "CuentasUsuario")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CuentaUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CuentaID")
    private Long cuentaId;



    @Column(name = "NumeroCuenta", nullable = false, length = 30)
    private String numeroCuenta;

    @Column(name = "TipoCuenta", nullable = false, length = 30)
    private String tipoCuenta;

    @Column(name = "Banco", nullable = false, length = 50)
    private String banco;

    @Column(name = "Saldo", nullable = false, precision = 12, scale = 2)
    private BigDecimal saldo;

    @Column(name = "CreatedAt", nullable = false)
    private Timestamp createdAt;

    @Column(name = "UpdatedAt", nullable = false)
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ClienteID", referencedColumnName = "ClienteId")
    private Cliente cliente;

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
