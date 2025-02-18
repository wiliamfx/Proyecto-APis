package pe.edu.idat.managment_customer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.idat.managment_customer.dto.ClienteCreateDTO;
import pe.edu.idat.managment_customer.dto.ClienteResponseDTO;
import pe.edu.idat.managment_customer.dto.DocumentoDTO;
import pe.edu.idat.managment_customer.model.Cliente;
import pe.edu.idat.managment_customer.model.Documento;
import pe.edu.idat.managment_customer.repository.ClienteRepository;
import pe.edu.idat.managment_customer.repository.DocumentoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final DocumentoRepository documentoRepository;

    public List<ClienteResponseDTO> getAllClientes() {
        return clienteRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public ClienteResponseDTO createCliente(ClienteCreateDTO clienteCreateDTO) {
        // Verificar si el documento existe
        Optional<Documento> documentoOptional = documentoRepository.findById(clienteCreateDTO.getDocumentoId());
        if (documentoOptional.isEmpty()) {
            throw new RuntimeException("Documento no encontrado con ID: " + clienteCreateDTO.getDocumentoId());
        }

        // Crear el cliente
        Cliente cliente = new Cliente();
        cliente.setTipoCliente(clienteCreateDTO.getTipoCliente());
        cliente.setNombre(clienteCreateDTO.getNombre());
        cliente.setDocumento(documentoOptional.get());
        cliente.setDireccion(clienteCreateDTO.getDireccion());
        cliente.setTelefono(clienteCreateDTO.getTelefono());
        cliente.setEmail(clienteCreateDTO.getEmail());

        Cliente savedCliente = clienteRepository.save(cliente);
        return mapToDTO(savedCliente);
    }

    public ClienteResponseDTO getClienteById(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + clienteId));
        return mapToDTO(cliente);
    }

    public ClienteResponseDTO updateCliente(Long clienteId, ClienteCreateDTO clienteDetails) {
        Cliente existingCliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + clienteId));

        // Verificar si el documento existe
        Optional<Documento> documentoOptional = documentoRepository.findById(clienteDetails.getDocumentoId());
        if (documentoOptional.isEmpty()) {
            throw new RuntimeException("Documento no encontrado con ID: " + clienteDetails.getDocumentoId());
        }

        existingCliente.setNombre(clienteDetails.getNombre());
        existingCliente.setDireccion(clienteDetails.getDireccion());
        existingCliente.setTelefono(clienteDetails.getTelefono());
        existingCliente.setEmail(clienteDetails.getEmail());
        existingCliente.setDocumento(documentoOptional.get());

        Cliente updatedCliente = clienteRepository.save(existingCliente);
        return mapToDTO(updatedCliente);
    }

    public void deleteCliente(Long clienteId) {
        Cliente existingCliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + clienteId));
        clienteRepository.delete(existingCliente);
    }

    private ClienteResponseDTO mapToDTO(Cliente cliente) {
        DocumentoDTO documentoDTO = null;
        if (cliente.getDocumento() != null) {
            Documento doc = cliente.getDocumento();
            documentoDTO = new DocumentoDTO(
                    doc.getDocumentoId(),
                    doc.getTipoDocumento(),
                    doc.getNumeroDocumento(),
                    doc.getFechaEmision(),
                    doc.getPais());
        }
        return new ClienteResponseDTO(
                cliente .getClienteId(),
                cliente.getTipoCliente(),
                cliente.getNombre(),
                documentoDTO,
                cliente.getDireccion(),
                cliente.getTelefono(),
                cliente.getEmail());
    }
}