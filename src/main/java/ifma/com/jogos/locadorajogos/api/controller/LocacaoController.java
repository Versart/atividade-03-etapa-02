package ifma.com.jogos.locadorajogos.api.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifma.com.jogos.locadorajogos.api.model.LocacaoRequest;
import ifma.com.jogos.locadorajogos.domain.service.LocacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/locacoes")
@RequiredArgsConstructor
public class LocacaoController {

    private final LocacaoService locacaoService;
    
    @PostMapping("/{idCliente}")
    public void saveLocacao(@Valid @RequestBody LocacaoRequest locacaoRequest, @PathVariable Long idCliente){
        locacaoService.saveLocacao(idCliente, locacaoRequest);
    }
}
