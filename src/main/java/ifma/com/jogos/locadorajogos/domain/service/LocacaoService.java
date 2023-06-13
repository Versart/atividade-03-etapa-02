package ifma.com.jogos.locadorajogos.domain.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import ifma.com.jogos.locadorajogos.api.model.AcessorioRequest;
import ifma.com.jogos.locadorajogos.api.model.ItemLocacaoRequest;
import ifma.com.jogos.locadorajogos.api.model.LocacaoConsoleRequest;
import ifma.com.jogos.locadorajogos.api.model.LocacaoJogosRequest;
import ifma.com.jogos.locadorajogos.api.model.LocacaoResponse;
import ifma.com.jogos.locadorajogos.domain.model.Acessorio;
import ifma.com.jogos.locadorajogos.domain.model.Cliente;
import ifma.com.jogos.locadorajogos.domain.model.Console;
import ifma.com.jogos.locadorajogos.domain.model.ItemLocacao;
import ifma.com.jogos.locadorajogos.domain.model.JogoPlataforma;
import ifma.com.jogos.locadorajogos.domain.model.Locacao;
import ifma.com.jogos.locadorajogos.domain.model.UtilizacaoConsoleCliente;
import ifma.com.jogos.locadorajogos.domain.repository.AcessorioRepositorio;
import ifma.com.jogos.locadorajogos.domain.repository.ClienteRepository;
import ifma.com.jogos.locadorajogos.domain.repository.ConsoleRepository;
import ifma.com.jogos.locadorajogos.domain.repository.ItemLocacaoRepository;
import ifma.com.jogos.locadorajogos.domain.repository.JogoPlataformaRepository;
import ifma.com.jogos.locadorajogos.domain.repository.LocacaoRepository;
import ifma.com.jogos.locadorajogos.domain.repository.UtilizacaoConsoleClienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocacaoService {
    
    private final LocacaoRepository locacaoRepository;

    private final ClienteRepository clienteRepository;

    private final JogoPlataformaRepository jogoPlataformaRepository;

    private final ConsoleRepository consoleRepository;

    private final ItemLocacaoRepository itemLocacaoRepository;

    private final AcessorioRepositorio acessorioRepositorio;

    private final UtilizacaoConsoleClienteRepository utilizacaoConsoleClienteRepository;

    @Transactional
    public LocacaoResponse saveLocacaoJogos(Long idCliente, LocacaoJogosRequest locacaoRequest){
        Cliente clienteLocacao = buscarClientePeloId(idCliente);
        Locacao locacao = new Locacao();
        locacao.setCliente(clienteLocacao);
        locacao = locacaoRepository.save(locacao);
        BigDecimal valorTotal = BigDecimal.ZERO;
        for(ItemLocacaoRequest item : locacaoRequest.itens()){
           JogoPlataforma jogo = jogoPlataformaRepository.findById(item.idJogoPlataforma()).orElseThrow(() -> new RuntimeException());
           ItemLocacao itemLocacao = new ItemLocacao(item);
           itemLocacao.setJogoPlataforma(jogo);
           itemLocacao.setLocacao(locacao);
           itemLocacao.setValor(calcularValorJogoLocacao(itemLocacao));
           itemLocacaoRepository.save(itemLocacao);
           valorTotal = valorTotal.add(itemLocacao.getValor());
        }
        return new LocacaoResponse(clienteLocacao.getNome(),LocalDate.now(), valorTotal);
    }
    @Transactional
    public LocacaoResponse saveLocacaoConsole(Long idCliente, LocacaoConsoleRequest locacaoRequest) {
        Cliente clienteLocacao = buscarClientePeloId(idCliente);
        Console console = buscarConsolePeloId(locacaoRequest.idConsole());
        UtilizacaoConsoleCliente utilizacaoConsole = new UtilizacaoConsoleCliente();
        utilizacaoConsole.setCliente(clienteLocacao);
        utilizacaoConsole.setConsole(console);
        utilizacaoConsole.setFim(locacaoRequest.dataEntrega());
        utilizacaoConsole.setInicio(LocalDateTime.now());
        if(locacaoRequest.acessorios() != null){
        for(AcessorioRequest acessorio : locacaoRequest.acessorios()){
            Acessorio acessorioBuscado = acessorioRepositorio.findById(acessorio.idAcessorio()).orElseThrow(() -> new RuntimeException());
            console.getAcessorios().add(acessorioBuscado);
        }
        }
        BigDecimal valorAluguel = calcularValorConsoleLocacao(utilizacaoConsole).setScale(2,RoundingMode.UP);
        utilizacaoConsole.setValor(valorAluguel);
        var consoleAlugado = utilizacaoConsoleClienteRepository.save(utilizacaoConsole);
        
        return new LocacaoResponse(consoleAlugado.getCliente().getNome(),LocalDate.now(),valorAluguel);
    }




    private Cliente buscarClientePeloId(Long idCliente) {
        return clienteRepository.findById(idCliente).orElseThrow(() -> new RuntimeException());
    }

    private Console buscarConsolePeloId(Long idConsole){
        return consoleRepository.findById(idConsole).orElseThrow(() -> new RuntimeException());
    }

    

    private BigDecimal calcularValorJogoLocacao(ItemLocacao itemLocacao){
        BigDecimal dias = new BigDecimal(itemLocacao.getDias());
        BigDecimal valorItem = itemLocacao.getJogoPlataforma().getPrecoDiario().multiply(dias);
        return valorItem.multiply(new BigDecimal(itemLocacao.getQuantidade()));
    }

    private BigDecimal calcularValorConsoleLocacao(UtilizacaoConsoleCliente utilizacaoConsoleCliente) {
        Double tempo = Duration.between(utilizacaoConsoleCliente.getInicio(),utilizacaoConsoleCliente.getFim())
            .getSeconds()/3600.0;
        return utilizacaoConsoleCliente.getConsole().getPrecoPorHora()
        .multiply(new BigDecimal(tempo.toString()));
    }
}
