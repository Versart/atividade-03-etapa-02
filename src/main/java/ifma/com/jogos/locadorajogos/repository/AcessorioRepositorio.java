package ifma.com.jogos.locadorajogos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ifma.com.jogos.locadorajogos.model.Acessorio;

public interface AcessorioRepositorio extends JpaRepository<Acessorio, Long> {
    
}
