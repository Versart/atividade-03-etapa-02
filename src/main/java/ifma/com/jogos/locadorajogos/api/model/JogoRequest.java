package ifma.com.jogos.locadorajogos.api.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record JogoRequest(
    @NotNull
    @NotBlank
    String titulo, 
    @NotNull
    @NotBlank
    String nomePlataforma, 
    @NotNull
    BigDecimal preco,
    @NotNull
    Integer quantidade) {
    
}
