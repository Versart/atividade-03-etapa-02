package ifma.com.jogos.locadorajogos.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ifma.com.jogos.locadorajogos.domain.model.Jogo;

public interface JogoRepository extends JpaRepository<Jogo,Long> {
    
}
