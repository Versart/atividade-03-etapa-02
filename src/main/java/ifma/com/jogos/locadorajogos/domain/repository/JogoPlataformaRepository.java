package ifma.com.jogos.locadorajogos.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ifma.com.jogos.locadorajogos.domain.model.JogoPlataforma;

public interface JogoPlataformaRepository extends JpaRepository<JogoPlataforma, Long> {
    
}
