package pe.edu.idat.managment_customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteUpdateRequestDTO {
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
}