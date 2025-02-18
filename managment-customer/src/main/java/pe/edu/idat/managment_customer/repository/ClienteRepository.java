package pe.edu.idat.managment_customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.idat.managment_customer.model.Cliente;
import pe.edu.idat.managment_customer.model.CuentaUsuario;
import pe.edu.idat.managment_customer.model.Documento;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByDocumento_DocumentoId(Long documentoId);
}