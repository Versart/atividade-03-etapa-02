package ifma.com.jogos.locadorajogos.api.model;

import java.math.BigDecimal;

public record JogoResponse(String nome, String nomePlataforma,BigDecimal preco) {
    
}
