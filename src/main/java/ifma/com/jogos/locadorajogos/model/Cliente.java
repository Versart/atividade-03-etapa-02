package ifma.com.jogos.locadorajogos.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "clientes")
@Data
public class Cliente {
    
    @Id
    @GeneratedValue
    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String senha;
    @OneToMany(mappedBy = "cliente")
    private List<Locacao> locacoes = new ArrayList<>();

}
