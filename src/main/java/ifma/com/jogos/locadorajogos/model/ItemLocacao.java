package ifma.com.jogos.locadorajogos.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ItemLocacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Locacao locacao;

    @ManyToOne
    private JogoPlataforma jogoPlataforma;

    private Integer dias;

    private Integer quantidade;
}
