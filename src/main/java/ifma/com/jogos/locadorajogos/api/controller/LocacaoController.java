package ifma.com.jogos.locadorajogos.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifma.com.jogos.locadorajogos.api.model.LocacaoRequest;
import ifma.com.jogos.locadorajogos.api.model.LocacaoResponse;
import ifma.com.jogos.locadorajogos.domain.service.LocacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/locacoes")
@RequiredArgsConstructor
public class LocacaoController {

    private final LocacaoService locacaoService;
    
    @PostMapping("/{idCliente}")
    public ResponseEntity<LocacaoResponse> saveLocacao(@Valid @RequestBody LocacaoRequest locacaoRequest, @PathVariable Long idCliente){
        return new ResponseEntity<>(locacaoService.saveLocacao(idCliente, locacaoRequest), HttpStatus.CREATED);
    }
}
