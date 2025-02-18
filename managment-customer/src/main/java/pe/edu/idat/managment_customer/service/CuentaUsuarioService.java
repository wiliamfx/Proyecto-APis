package pe.edu.idat.managment_customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.idat.managment_customer.dto.CuentaBalanceResponseDTO;
import pe.edu.idat.managment_customer.dto.CuentaUsuarioCreateDTO;
import pe.edu.idat.managment_customer.model.CuentaUsuario;
import pe.edu.idat.managment_customer.model.Cliente;
import pe.edu.idat.managment_customer.repository.CuentaUsuarioRepository;
import pe.edu.idat.managment_customer.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CuentaUsuarioService {

    @Autowired
    private CuentaUsuarioRepository cuentaUsuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    public CuentaBalanceResponseDTO crearCuentaUsuario(CuentaUsuarioCreateDTO cuentaUsuarioCreateDTO) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(cuentaUsuarioCreateDTO.getClienteId());
        if (!clienteOpt.isPresent()) {
            throw new RuntimeException("El cliente no existe");
        }

        CuentaUsuario cuentaUsuario = new CuentaUsuario();
        cuentaUsuario.setNumeroCuenta(cuentaUsuarioCreateDTO.getNumeroCuenta());
        cuentaUsuario.setTipoCuenta(cuentaUsuarioCreateDTO.getTipoCuenta());
        cuentaUsuario.setBanco(cuentaUsuarioCreateDTO.getBanco());
        cuentaUsuario.setSaldo(cuentaUsuarioCreateDTO.getSaldo());
        cuentaUsuario.setCliente(clienteOpt.get());

        CuentaUsuario nuevaCuenta = cuentaUsuarioRepository.save(cuentaUsuario);
        return convertirADTO(nuevaCuenta);
    }

    public CuentaBalanceResponseDTO obtenerCuentaUsuarioPorId(Long id) {
        CuentaUsuario cuenta = cuentaUsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        return convertirADTO(cuenta);
    }

    public List<CuentaBalanceResponseDTO> obtenerTodasLasCuentas() {
        return cuentaUsuarioRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public CuentaBalanceResponseDTO actualizarCuentaUsuario(Long id, CuentaUsuarioCreateDTO cuentaUsuarioCreateDTO) {
        CuentaUsuario cuentaExistente = cuentaUsuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        cuentaExistente.setNumeroCuenta(cuentaUsuarioCreateDTO.getNumeroCuenta());
        cuentaExistente.setTipoCuenta(cuentaUsuarioCreateDTO.getTipoCuenta());
        cuentaExistente.setBanco(cuentaUsuarioCreateDTO.getBanco());
        cuentaExistente.setSaldo(cuentaUsuarioCreateDTO.getSaldo());

        return convertirADTO(cuentaUsuarioRepository.save(cuentaExistente));
    }

    public void eliminarCuentaUsuario(Long id) {
        cuentaUsuarioRepository.deleteById(id);
    }

    public CuentaBalanceResponseDTO convertirADTO(CuentaUsuario cuentaUsuario) {
        return new CuentaBalanceResponseDTO(
                cuentaUsuario.getCuentaId(),
                cuentaUsuario.getNumeroCuenta(),
                cuentaUsuario.getTipoCuenta(),
                cuentaUsuario.getBanco(),
                cuentaUsuario.getSaldo(),
                cuentaUsuario.getCliente().getClienteId()
        );
    }
}