package ifma.com.jogos.locadorajogos.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ifma.com.jogos.locadorajogos.domain.model.JogoComDesconto;

public interface JogoComDescontoRepository extends JpaRepository<JogoComDesconto, Long> {
    
    JogoComDesconto findByJogoPlataformaId(Long id);
}
