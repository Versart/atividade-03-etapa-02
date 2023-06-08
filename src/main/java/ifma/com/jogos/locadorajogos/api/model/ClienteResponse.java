package ifma.com.jogos.locadorajogos.api.model;

import ifma.com.jogos.locadorajogos.domain.model.Cliente;

public record ClienteResponse(Long id,
String nome,
String email,
String telefone) {
    public ClienteResponse(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getTelefone());
    }
}
