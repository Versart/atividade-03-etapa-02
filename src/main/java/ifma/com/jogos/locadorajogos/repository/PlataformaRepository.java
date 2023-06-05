package ifma.com.jogos.locadorajogos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ifma.com.jogos.locadorajogos.model.Plataforma;

public interface PlataformaRepository extends JpaRepository<Plataforma, Long> {
    
}
