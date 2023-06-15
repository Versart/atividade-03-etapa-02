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
@Table(name="consoles_com_desconto")
@Data
public class ConsoleComDesconto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "porcentagem_desconto")
    private BigDecimal porcentagemDesconto;

    @JoinColumn(name = "console_id")
    @ManyToOne
    private Console console;
}
