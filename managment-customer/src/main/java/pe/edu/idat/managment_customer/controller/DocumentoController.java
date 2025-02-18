package pe.edu.idat.managment_customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.managment_customer.dto.DocumentoDTO;
import pe.edu.idat.managment_customer.service.DocumentoService;

import java.util.List;

@RestController
@RequestMapping("/api/documentos")
public class DocumentoController {

    @Autowired
    private DocumentoService documentoService;

    @PostMapping
    public ResponseEntity<DocumentoDTO> crearDocumento(@RequestBody DocumentoDTO documentoDTO) {
        DocumentoDTO nuevoDocumento = documentoService.crearDocumento(documentoDTO);
        return ResponseEntity.ok(nuevoDocumento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentoDTO> obtenerDocumentoPorId(@PathVariable Long id) {
        DocumentoDTO documentoDTO = documentoService.obtenerDocumentoPorId(id);
        return ResponseEntity.ok(documentoDTO);
    }

    @GetMapping
    public ResponseEntity<List<DocumentoDTO>> obtenerTodosLosDocumentos() {
        List<DocumentoDTO> documentos = documentoService.obtenerTodosLosDocumentos();
        return ResponseEntity.ok(documentos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DocumentoDTO> actualizarDocumento(@PathVariable Long id, @RequestBody DocumentoDTO documentoDTO) {
        DocumentoDTO documentoActualizado = documentoService.actualizarDocumento(id, documentoDTO);
        return ResponseEntity.ok(documentoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDocumento(@PathVariable Long id) {
        documentoService.eliminarDocumento(id);
        return ResponseEntity.noContent().build();
    }
}