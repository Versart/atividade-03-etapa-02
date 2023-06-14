package ifma.com.jogos.locadorajogos.domain.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="jogos_plataformas")
@Data
public class JogoPlataforma implements Nomeavel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantidade;

    @JoinColumn(name="jogos_id")
    @ManyToOne
    private Jogo jogo;
    
    @JoinColumn(name="plataformas_id")
    @ManyToOne
    private Plataforma plataforma;
    @Column(name = "preco_diario")
    private BigDecimal precoDiario;
    @Override
    public String nome() {
       return this.jogo.getTitulo();
    }

    
}
