package ifma.com.jogos.locadorajogos.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ifma.com.jogos.locadorajogos.domain.model.JogoPlataforma;

public interface JogoPlataformaRepository extends JpaRepository<JogoPlataforma, Long> {
    
    List<JogoPlataforma> findByQuantidadeGreaterThanEqual(Integer quantidade);
}
