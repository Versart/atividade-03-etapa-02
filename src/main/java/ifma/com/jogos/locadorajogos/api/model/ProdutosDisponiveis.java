package ifma.com.jogos.locadorajogos.api.model;

import ifma.com.jogos.locadorajogos.domain.model.Console;
import ifma.com.jogos.locadorajogos.domain.model.JogoPlataforma;
import ifma.com.jogos.locadorajogos.domain.model.Nomeavel;

public record ProdutosDisponiveis(String nome) {
    public ProdutosDisponiveis(Nomeavel nomeavel){
        this(nomeavel.nome());
    }
}
