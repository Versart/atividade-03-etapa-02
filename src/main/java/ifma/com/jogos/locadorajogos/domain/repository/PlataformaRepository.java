package ifma.com.jogos.locadorajogos.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ifma.com.jogos.locadorajogos.domain.model.Plataforma;

public interface PlataformaRepository extends JpaRepository<Plataforma, Long> {
    Optional<Plataforma> findPlataformaByNome(String nome);
}
