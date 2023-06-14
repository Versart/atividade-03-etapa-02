package ifma.com.jogos.locadorajogos.api.model;

import ifma.com.jogos.locadorajogos.domain.model.ItemLocacao;

public record JogoLocado(String nomeCliente,String jogo, String plataforma) {
    
    public JogoLocado(ItemLocacao itemLocacao) {
        this(itemLocacao.getLocacao().getCliente().getNome(),
        itemLocacao.getJogoPlataforma().getJogo().getTitulo(),
        itemLocacao.getJogoPlataforma().getPlataforma().getNome()
        );
    }
}
