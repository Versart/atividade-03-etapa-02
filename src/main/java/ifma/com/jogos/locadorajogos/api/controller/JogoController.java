package ifma.com.jogos.locadorajogos.api.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ifma.com.jogos.locadorajogos.api.model.JogoRequest;
import ifma.com.jogos.locadorajogos.api.model.JogoResponse;
import ifma.com.jogos.locadorajogos.domain.service.JogoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/jogos")
@RequiredArgsConstructor
public class JogoController {

    private final JogoService jogoService;

    @PostMapping
    public ResponseEntity<JogoResponse> saveJogo(@Valid @RequestBody JogoRequest jogoRequest) {
        return new ResponseEntity<>(jogoService.saveJogo(jogoRequest), HttpStatus.CREATED);
    }
}