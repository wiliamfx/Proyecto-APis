package pe.edu.idat.managment_customer.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaBalanceResponseDTO {
    private Long cuentaId;
    private String numeroCuenta;
    private String tipoCuenta;
    private String banco;
    private BigDecimal saldo;
    private Long clienteId;
}
