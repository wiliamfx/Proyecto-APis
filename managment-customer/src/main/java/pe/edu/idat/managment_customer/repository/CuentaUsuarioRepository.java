package pe.edu.idat.managment_customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.idat.managment_customer.model.Cliente;
import pe.edu.idat.managment_customer.model.CuentaUsuario;

import java.util.Optional;
@Repository
public interface CuentaUsuarioRepository extends JpaRepository<CuentaUsuario, Long> {

    Optional<CuentaUsuario> findByCliente_ClienteId(Long clienteId);
}
