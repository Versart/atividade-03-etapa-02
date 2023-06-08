package ifma.com.jogos.locadorajogos.domain.service;

import org.springframework.stereotype.Service;

import ifma.com.jogos.locadorajogos.api.model.JogoRequest;
import ifma.com.jogos.locadorajogos.api.model.JogoResponse;
import ifma.com.jogos.locadorajogos.domain.model.Jogo;
import ifma.com.jogos.locadorajogos.domain.model.JogoPlataforma;
import ifma.com.jogos.locadorajogos.domain.model.Plataforma;
import ifma.com.jogos.locadorajogos.domain.repository.JogoPlataformaRepository;
import ifma.com.jogos.locadorajogos.domain.repository.JogoRepository;
import ifma.com.jogos.locadorajogos.domain.repository.PlataformaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JogoService {
    
    private final JogoRepository jogoRepository;

    private final PlataformaRepository plataformaRepository;

    private final JogoPlataformaRepository jogoPlataformaRepository;

    @Transactional
    public JogoResponse saveJogo(JogoRequest jogoRequest){
        Plataforma plataformaJogo = plataformaRepository.findPlataformaByNome(jogoRequest.nomePlataforma())
        .orElseThrow( () -> new RuntimeException("Plataforma não encontrada"));
        Jogo jogoParaSalvar = new Jogo(jogoRequest);
        Jogo jogoSalvo = jogoRepository.save(jogoParaSalvar);
        JogoPlataforma jogoPlataforma = new JogoPlataforma();
        jogoPlataforma.setJogo(jogoSalvo);
        jogoPlataforma.setPlataforma(plataformaJogo);
        jogoPlataforma.setPrecoDiario(jogoRequest.preco());
        jogoPlataformaRepository.save(jogoPlataforma);
        return new JogoResponse(jogoRequest.titulo(), jogoRequest.nomePlataforma(), jogoRequest.preco());
    }
}
