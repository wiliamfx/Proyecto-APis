package pe.edu.idat.managment_customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.idat.managment_customer.model.Documento;
@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {

}
