package ifma.com.jogos.locadorajogos.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ifma.com.jogos.locadorajogos.api.model.ItemLocacaoRequest;
import ifma.com.jogos.locadorajogos.api.model.LocacaoRequest;
import ifma.com.jogos.locadorajogos.domain.model.Cliente;
import ifma.com.jogos.locadorajogos.domain.model.ItemLocacao;
import ifma.com.jogos.locadorajogos.domain.model.JogoPlataforma;
import ifma.com.jogos.locadorajogos.domain.model.Locacao;
import ifma.com.jogos.locadorajogos.domain.repository.ClienteRepository;
import ifma.com.jogos.locadorajogos.domain.repository.ItemLocacaoRepository;
import ifma.com.jogos.locadorajogos.domain.repository.JogoPlataformaRepository;
import ifma.com.jogos.locadorajogos.domain.repository.LocacaoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocacaoService {
    
    private final LocacaoRepository locacaoRepository;

    private final ClienteRepository clienteRepository;

    private final JogoPlataformaRepository jogoPlataformaRepository;

    private final ItemLocacaoRepository itemLocacaoRepository;
    @Transactional
    public void saveLocacao(Long idCliente, LocacaoRequest locacaoRequest){
        Cliente clienteLocacao = clienteRepository.findById(idCliente).orElseThrow(() -> new RuntimeException());
        Locacao locacao = new Locacao();
        for(ItemLocacaoRequest item : locacaoRequest.itens()){
           JogoPlataforma jogo = jogoPlataformaRepository.findById(item.idjogoPlataforma()).orElseThrow(() -> new RuntimeException());
           ItemLocacao itemLocacao = new ItemLocacao(item);
           itemLocacao.setJogoPlataforma(jogo);
           

        }

    }
}
