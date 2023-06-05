package ifma.com.jogos.locadorajogos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ifma.com.jogos.locadorajogos.model.Jogo;

public interface JogoRepository extends JpaRepository<Jogo,Long> {
    
}
