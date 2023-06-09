package ifma.com.jogos.locadorajogos.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Locacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_locacao")
    private LocalDate dataLocacao =  LocalDate.now();
    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Cliente cliente;
    @OneToMany(mappedBy = "locacao", cascade = CascadeType.PERSIST)
    private List<ItemLocacao> itensLocacoes = new ArrayList<>();
}
