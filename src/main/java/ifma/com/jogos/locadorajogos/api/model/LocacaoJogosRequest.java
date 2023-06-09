package ifma.com.jogos.locadorajogos.api.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record LocacaoJogosRequest(
    @NotNull
    @Valid
    List<ItemLocacaoRequest> itens,
    @NotNull
    LocalDate dataLocacao ) {
    
}
