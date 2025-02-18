package pe.edu.idat.managment_customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteCreateDTO {
    private String tipoCliente;
    private String nombre;
    private Long documentoId;
    private String direccion;
    private String telefono;
    private String email;
}