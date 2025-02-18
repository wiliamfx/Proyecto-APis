package pe.edu.idat.managment_customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDTO {
    private Long clienteId;
    private String tipoCliente;
    private String nombre;
    private DocumentoDTO documento;
    private String direccion;
    private String telefono;
    private String email;
}