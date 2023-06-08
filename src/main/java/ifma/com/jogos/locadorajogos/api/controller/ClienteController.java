package ifma.com.jogos.locadorajogos.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ifma.com.jogos.locadorajogos.api.model.ClienteRequest;
import ifma.com.jogos.locadorajogos.api.model.ClienteResponse;
import ifma.com.jogos.locadorajogos.domain.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClienteResponse> saveCliente(@RequestBody @Valid ClienteRequest clienteRequest) {
        return ResponseEntity.ok(clienteService.saveCliente(clienteRequest));
    }

    @GetMapping
    public ResponseEntity<String> teste() {
        return ResponseEntity.ok(new String( "Olá Mundo!"));
    }
}