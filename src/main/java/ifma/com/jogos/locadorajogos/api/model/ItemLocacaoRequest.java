package ifma.com.jogos.locadorajogos.api.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record ItemLocacaoRequest(
    @NotNull
    Long idJogoPlataforma,
    @NotNull
    LocalDate dataLocacao,
    @NotNull
    Integer dias, 
    @NotNull
    Integer quantidade) {
    
}
