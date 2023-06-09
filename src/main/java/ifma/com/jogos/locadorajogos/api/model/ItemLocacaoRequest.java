package ifma.com.jogos.locadorajogos.api.model;

import jakarta.validation.constraints.NotNull;

public record ItemLocacaoRequest(
    @NotNull
    Long idJogoPlataforma, 
    @NotNull
    Integer dias, 
    @NotNull
    Integer quantidade) {
    
}
