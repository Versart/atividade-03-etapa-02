package ifma.com.jogos.locadorajogos.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ifma.com.jogos.locadorajogos.domain.model.Acessorio;

public interface AcessorioRepositorio extends JpaRepository<Acessorio, Long> {
    
}
