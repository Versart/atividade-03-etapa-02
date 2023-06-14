package ifma.com.jogos.locadorajogos.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ifma.com.jogos.locadorajogos.domain.model.Console;

public interface ConsoleRepository extends JpaRepository<Console, Long>{
    List<Console> findByQuantidadeGreaterThanEqual(Integer quantidade);
}
