package ifma.com.jogos.locadorajogos.api.model;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotNull;

public record LocacaoConsoleRequest(@NotNull Long idConsole,List<AcessorioRequest> acessorios,
@NotNull LocalDateTime dataEntrega,
@NotNull LocalDateTime dataInicio,
@NotNull
Integer quantidade) {
    
}
