package ifma.com.jogos.locadorajogos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ifma.com.jogos.locadorajogos.model.JogoPlataforma;

public interface JogoPlataformaRepository extends JpaRepository<JogoPlataforma, Long> {
    
}
