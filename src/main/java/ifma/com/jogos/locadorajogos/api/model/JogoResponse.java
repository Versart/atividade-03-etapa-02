package ifma.com.jogos.locadorajogos.api.model;

import java.math.BigDecimal;

import ifma.com.jogos.locadorajogos.domain.model.Nomeavel;

public record JogoResponse(String nome, String nomePlataforma,BigDecimal preco){
    
}
