package pe.edu.idat.managment_customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.idat.managment_customer.dto.DocumentoDTO;
import pe.edu.idat.managment_customer.model.Documento;
import pe.edu.idat.managment_customer.repository.DocumentoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    // Crear un nuevo documento
    public DocumentoDTO crearDocumento(DocumentoDTO documentoDTO) {
        Documento documento = new Documento();
        documento.setTipoDocumento(documentoDTO.getTipoDocumento());
        documento.setNumeroDocumento(documentoDTO.getNumeroDocumento());
        documento.setFechaEmision(documentoDTO.getFechaEmision());
        documento.setPais(documentoDTO.getPais());

        Documento documentoGuardado = documentoRepository.save(documento);
        return convertirADTO(documentoGuardado);
    }

    // Obtener un documento por ID
    public DocumentoDTO obtenerDocumentoPorId(Long id) {
        Documento documento = documentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Documento no encontrado"));
        return convertirADTO(documento);
    }

    // Obtener todos los documentos
    public List<DocumentoDTO> obtenerTodosLosDocumentos() {
        return documentoRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // Actualizar un documento existente
    public DocumentoDTO actualizarDocumento(Long id, DocumentoDTO documentoDTO) {
        Documento documentoExistente = documentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Documento no encontrado"));

        documentoExistente.setTipoDocumento(documentoDTO.getTipoDocumento());
        documentoExistente.setNumeroDocumento(documentoDTO.getNumeroDocumento());
        documentoExistente.setFechaEmision(documentoDTO.getFechaEmision());
        documentoExistente.setPais(documentoDTO.getPais());

        Documento documentoActualizado = documentoRepository.save(documentoExistente);
        return convertirADTO(documentoActualizado);
    }


    public void eliminarDocumento(Long id) {
        documentoRepository.deleteById(id);
    }

    private DocumentoDTO convertirADTO(Documento documento) {
        return new DocumentoDTO(
                documento.getDocumentoId(),
                documento.getTipoDocumento(),
                documento.getNumeroDocumento(),
                documento.getFechaEmision(),
                documento.getPais()
        );
    }
}