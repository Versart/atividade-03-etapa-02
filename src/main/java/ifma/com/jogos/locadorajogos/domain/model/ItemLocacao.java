package ifma.com.jogos.locadorajogos.domain.model;

import ifma.com.jogos.locadorajogos.api.model.ItemLocacaoRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="item_locacao")
public class ItemLocacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "locacao_id")
    private Locacao locacao;

    @ManyToOne
    @JoinColumn(name="jogos_plataformas_id")
    private JogoPlataforma jogoPlataforma;

    private Integer dias;

    private Integer quantidade;

    public ItemLocacao() {
        
    }

    public ItemLocacao(ItemLocacaoRequest itemLocacaoRequest){
        this.dias = itemLocacaoRequest.dias();
        this.quantidade = itemLocacaoRequest.quantidade();
    }
}
