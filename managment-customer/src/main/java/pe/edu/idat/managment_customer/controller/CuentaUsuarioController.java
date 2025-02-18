package pe.edu.idat.managment_customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.managment_customer.dto.CuentaBalanceResponseDTO;
import pe.edu.idat.managment_customer.dto.CuentaUsuarioCreateDTO;
import pe.edu.idat.managment_customer.service.CuentaUsuarioService;

import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaUsuarioController {

    @Autowired
    private CuentaUsuarioService cuentaUsuarioService;

    @PostMapping
    public ResponseEntity<CuentaBalanceResponseDTO> crearCuentaUsuario(@RequestBody CuentaUsuarioCreateDTO cuentaUsuarioCreateDTO) {
        CuentaBalanceResponseDTO nuevaCuenta = cuentaUsuarioService.crearCuentaUsuario(cuentaUsuarioCreateDTO);
        return ResponseEntity.ok(nuevaCuenta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaBalanceResponseDTO> obtenerCuentaUsuarioPorId(@PathVariable Long id) {
        CuentaBalanceResponseDTO cuenta = cuentaUsuarioService.obtenerCuentaUsuarioPorId(id);
        return ResponseEntity.ok(cuenta);
    }

    @GetMapping
    public ResponseEntity<List<CuentaBalanceResponseDTO>> obtenerTodasLasCuentas() {
        List<CuentaBalanceResponseDTO> cuentas = cuentaUsuarioService.obtenerTodasLasCuentas();
        return ResponseEntity.ok(cuentas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaBalanceResponseDTO> actualizarCuentaUsuario(@PathVariable Long id, @RequestBody CuentaUsuarioCreateDTO cuentaUsuarioCreateDTO) {
        CuentaBalanceResponseDTO cuentaActualizada = cuentaUsuarioService.actualizarCuentaUsuario(id, cuentaUsuarioCreateDTO);
        return ResponseEntity.ok(cuentaActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCuentaUsuario(@PathVariable Long id) {
        cuentaUsuarioService.eliminarCuentaUsuario(id);
        return ResponseEntity.noContent().build();
    }
}