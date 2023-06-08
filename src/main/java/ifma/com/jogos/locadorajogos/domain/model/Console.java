package ifma.com.jogos.locadorajogos.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Console {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    @Column(name = "preco_por_hora")
    private BigDecimal precoPorHora;
    @ManyToMany
    @JoinTable(name = "console_acessorios",
    joinColumns= @JoinColumn(name="console_id"
    ), inverseJoinColumns = @JoinColumn(name="acessorio_id"))
    private List<Acessorio> acessorios = new ArrayList<>();
}