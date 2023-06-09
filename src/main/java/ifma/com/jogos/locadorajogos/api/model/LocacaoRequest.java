package ifma.com.jogos.locadorajogos.api.model;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record LocacaoRequest(@NotNull Long idCliente,
    @NotNull
    @Valid
    List<ItemLocacaoRequest> itens ) {
    
}
