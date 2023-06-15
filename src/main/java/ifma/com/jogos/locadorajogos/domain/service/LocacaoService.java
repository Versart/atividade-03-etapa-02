package ifma.com.jogos.locadorajogos.domain.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import ifma.com.jogos.locadorajogos.api.model.AcessorioRequest;
import ifma.com.jogos.locadorajogos.api.model.ConsoleLocado;
import ifma.com.jogos.locadorajogos.api.model.ItemLocacaoRequest;
import ifma.com.jogos.locadorajogos.api.model.JogoLocado;
import ifma.com.jogos.locadorajogos.api.model.LocacaoConsoleRequest;
import ifma.com.jogos.locadorajogos.api.model.LocacaoJogosRequest;
import ifma.com.jogos.locadorajogos.api.model.LocacaoResponse;
import ifma.com.jogos.locadorajogos.api.model.ProdutosDisponiveis;
import ifma.com.jogos.locadorajogos.domain.model.Acessorio;
import ifma.com.jogos.locadorajogos.domain.model.Cliente;
import ifma.com.jogos.locadorajogos.domain.model.Console;
import ifma.com.jogos.locadorajogos.domain.model.ItemLocacao;
import ifma.com.jogos.locadorajogos.domain.model.Jogo;
import ifma.com.jogos.locadorajogos.domain.model.JogoPlataforma;
import ifma.com.jogos.locadorajogos.domain.model.Locacao;
import ifma.com.jogos.locadorajogos.domain.model.Nomeavel;
import ifma.com.jogos.locadorajogos.domain.model.UtilizacaoConsoleCliente;
import ifma.com.jogos.locadorajogos.domain.repository.AcessorioRepositorio;
import ifma.com.jogos.locadorajogos.domain.repository.ClienteRepository;
import ifma.com.jogos.locadorajogos.domain.repository.ConsoleComDescontoRepository;
import ifma.com.jogos.locadorajogos.domain.repository.ConsoleRepository;
import ifma.com.jogos.locadorajogos.domain.repository.ItemLocacaoRepository;
import ifma.com.jogos.locadorajogos.domain.repository.JogoComDescontoRepository;
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

    private final JogoComDescontoRepository jogoComDescontoRepository;

    private final ConsoleComDescontoRepository consoleComDescontoRepository;

    @Transactional
    public LocacaoResponse saveLocacaoJogos(Long idCliente, LocacaoJogosRequest locacaoRequest){
        Cliente clienteLocacao = buscarClientePeloId(idCliente);
        Locacao locacao = new Locacao();
        locacao.setCliente(clienteLocacao);
        locacao = locacaoRepository.save(locacao);
        BigDecimal valorTotal = BigDecimal.ZERO;
        for(ItemLocacaoRequest item : locacaoRequest.itens()){
           JogoPlataforma jogo = jogoPlataformaRepository.findById(item.idJogoPlataforma()).orElseThrow(() -> new RuntimeException());
           if(jogo.getQuantidade() >= item.quantidade()){
                int quantidadeNova = jogo.getQuantidade() - item.quantidade();
                jogo.setQuantidade(quantidadeNova);
                ItemLocacao itemLocacao = new ItemLocacao(item);
                itemLocacao.setJogoPlataforma(jogo);
                itemLocacao.setLocacao(locacao);
                itemLocacao.setValor(calcularValorJogoLocacao(itemLocacao));
                itemLocacaoRepository.save(itemLocacao);
                valorTotal = valorTotal.add(itemLocacao.getValor());
           }
           else
                throw new RuntimeException("Quantidade não disponível do jogo");
        }
        return new LocacaoResponse(clienteLocacao.getNome(),LocalDate.now(), valorTotal);
    }
    @Transactional
    public LocacaoResponse saveLocacaoConsole(Long idCliente, LocacaoConsoleRequest locacaoRequest) {
        Cliente clienteLocacao = buscarClientePeloId(idCliente);
        Console console = buscarConsolePeloId(locacaoRequest.idConsole());
        UtilizacaoConsoleCliente utilizacaoConsole = new UtilizacaoConsoleCliente();
        if(console.getQuantidade() < locacaoRequest.quantidade())
            throw new RuntimeException("Quantidade não disponível de console");
        int quantidadeNova = console.getQuantidade() - locacaoRequest.quantidade();
        console.setQuantidade(quantidadeNova);
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

    public List<ProdutosDisponiveis> locacoesDisponiveis(LocalDateTime periodo) {
        List<JogoPlataforma> jogosDisponiveis = jogoPlataformaRepository.findByQuantidadeGreaterThanEqual(1);
        List<Console> consolesDisponiveis = consoleRepository.findByQuantidadeGreaterThanEqual(1);
        List<ProdutosDisponiveis> produtosDisponiveis = new ArrayList<>();
        
        produtosDisponiveis.addAll(preencherProdutosDisponiveis(jogosDisponiveis));
        produtosDisponiveis.addAll(preencherProdutosDisponiveis(consolesDisponiveis));
        return produtosDisponiveis;
    }

    public List<JogoLocado> listarJogosLocados() {
        return itemLocacaoRepository.findAll().stream().map(item -> new JogoLocado(item))
        .toList();
    }

    public List<ConsoleLocado>  listarConsolesLocados() {
        return utilizacaoConsoleClienteRepository.findAll().stream()
            .map(consoleLocado -> new ConsoleLocado(consoleLocado)).toList();
    }

    private List<ProdutosDisponiveis> preencherProdutosDisponiveis(List<? extends Nomeavel> produtoNomeavels) {
       List<ProdutosDisponiveis> produtosDisponiveis = new ArrayList<>();
       for(Nomeavel nomeavel : produtoNomeavels)
            produtosDisponiveis.add(new ProdutosDisponiveis(nomeavel.nome()));
        return produtosDisponiveis;
    }


    private Cliente buscarClientePeloId(Long idCliente) {
        return clienteRepository.findById(idCliente).orElseThrow(() -> new RuntimeException());
    }

    private Console buscarConsolePeloId(Long idConsole){
        return consoleRepository.findById(idConsole).orElseThrow(() -> new RuntimeException());
    }


    private BigDecimal calcularValorJogoLocacao(ItemLocacao itemLocacao){
        BigDecimal dias = new BigDecimal(itemLocacao.getDias());
        BigDecimal desconto = jogoComDescontoRepository.findByJogoPlataformaId
            (itemLocacao.getJogoPlataforma().getId()).getPorcentagemDesconto();
        BigDecimal valorDoDesconto = itemLocacao.getJogoPlataforma().getPrecoDiario()
            .multiply(desconto).divide(new BigDecimal("100"));
        BigDecimal valorItem = itemLocacao.getJogoPlataforma().getPrecoDiario().subtract(valorDoDesconto).multiply(dias);
        
        return valorItem.multiply(new BigDecimal(itemLocacao.getQuantidade()));
    }

    private BigDecimal calcularValorConsoleLocacao(UtilizacaoConsoleCliente utilizacaoConsoleCliente) {
        Double tempo = Duration.between(utilizacaoConsoleCliente.getInicio(),utilizacaoConsoleCliente.getFim())
            .getSeconds()/3600.0;
        return utilizacaoConsoleCliente.getConsole().getPrecoPorHora()
        .multiply(new BigDecimal(tempo.toString()));
    }
}
