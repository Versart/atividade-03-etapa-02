package ifma.com.jogos.locadorajogos.domain.model;

import java.util.ArrayList;
import java.util.List;

import ifma.com.jogos.locadorajogos.api.model.ClienteRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "clientes")
@Data
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String senha;
    @OneToMany(mappedBy = "cliente")
    private List<Locacao> locacoes = new ArrayList<>();

    public Cliente(ClienteRequest clienteRequest) {
        this.nome = clienteRequest.nome();
        this.email = clienteRequest.email();
        this.telefone = clienteRequest.telefone();
    }

    public Cliente() {
        
    }

}
