package ifma.com.jogos.locadorajogos.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LocacaoResponse(
    String nomeCliente, 
    LocalDate dataLocacao, 
    BigDecimal valorTotal
) {
    
}
