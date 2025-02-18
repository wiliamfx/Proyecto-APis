package pe.edu.idat.managment_customer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentoDTO {
    private Long documentoId;
    private String tipoDocumento;
    private String numeroDocumento;
    private Date fechaEmision;
    private String pais;
}