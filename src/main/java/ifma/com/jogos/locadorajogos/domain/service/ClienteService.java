package ifma.com.jogos.locadorajogos.domain.service;

import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import ifma.com.jogos.locadorajogos.api.model.ClienteRequest;
import ifma.com.jogos.locadorajogos.api.model.ClienteResponse;
import ifma.com.jogos.locadorajogos.domain.model.Cliente;
import ifma.com.jogos.locadorajogos.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {
    
    private final ClienteRepository clienteRepository;

    public ClienteResponse saveCliente(ClienteRequest clienteRequest){
        Cliente cliente = new Cliente(clienteRequest);
        String bCrypt = BCrypt.withDefaults().hashToString(12, clienteRequest.senha().toCharArray());
        cliente.setSenha(bCrypt);
        return new ClienteResponse(clienteRepository.save(cliente));
    }
}
