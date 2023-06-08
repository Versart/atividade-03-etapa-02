package ifma.com.jogos.locadorajogos.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ifma.com.jogos.locadorajogos.domain.model.Plataforma;

public interface PlataformaRepository extends JpaRepository<Plataforma, Long> {
    
}
