package ifma.com.jogos.locadorajogos.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ifma.com.jogos.locadorajogos.api.model.ConsoleLocado;
import ifma.com.jogos.locadorajogos.api.model.JogoLocado;
import ifma.com.jogos.locadorajogos.api.model.LocacaoConsoleRequest;
import ifma.com.jogos.locadorajogos.api.model.LocacaoJogosRequest;
import ifma.com.jogos.locadorajogos.api.model.LocacaoResponse;
import ifma.com.jogos.locadorajogos.api.model.ProdutosDisponiveis;
import ifma.com.jogos.locadorajogos.domain.service.LocacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/locacoes")
@RequiredArgsConstructor
public class LocacaoController {

    private final LocacaoService locacaoService;
    
    @PostMapping("/{idCliente}/jogos")
    public ResponseEntity<LocacaoResponse> saveLocacaojogos(@Valid @RequestBody LocacaoJogosRequest locacaoRequest, @PathVariable Long idCliente){
        return new ResponseEntity<>(locacaoService.saveLocacaoJogos(idCliente, locacaoRequest), HttpStatus.CREATED);
    }

    @PostMapping("/{idCliente}/consoles")
    public ResponseEntity<LocacaoResponse> savelocacaoConsole(@Valid @RequestBody LocacaoConsoleRequest locacaoRequest, @PathVariable Long idCliente) {
        return new ResponseEntity<>(locacaoService.saveLocacaoConsole(idCliente,locacaoRequest),HttpStatus.CREATED);
    }

    @GetMapping("/disponiveis")
    public ResponseEntity<List<ProdutosDisponiveis>> getLocacoesDisponiveis(@RequestParam LocalDateTime periodo ) {
        return ResponseEntity.ok(locacaoService.locacoesDisponiveis(periodo));
    }

    @GetMapping("/jogos")
    public ResponseEntity<List<JogoLocado>> getJogosLocados(){
        return ResponseEntity.ok(locacaoService.listarJogosLocados());
    }

    @GetMapping("/consoles")
    public ResponseEntity<List<ConsoleLocado>> getConsolesLocados(){
        return ResponseEntity.ok(locacaoService.listarConsolesLocados());
    }
}
