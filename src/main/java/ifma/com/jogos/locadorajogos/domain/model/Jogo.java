package ifma.com.jogos.locadorajogos.domain.model;



import java.util.ArrayList;
import java.util.List;

import ifma.com.jogos.locadorajogos.api.model.JogoRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="jogos")
@Data
public class Jogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @OneToMany(mappedBy = "jogo")
    private List<JogoPlataforma> jogosPlataformas = new ArrayList<>();


    public Jogo(JogoRequest jogoRequest) {
        this.titulo = jogoRequest.titulo();
    }

    

}
