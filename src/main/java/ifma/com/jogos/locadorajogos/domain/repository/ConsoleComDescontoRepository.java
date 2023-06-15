package ifma.com.jogos.locadorajogos.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ifma.com.jogos.locadorajogos.domain.model.ConsoleComDesconto;

public interface ConsoleComDescontoRepository extends JpaRepository<ConsoleComDesconto, Long> {
    ConsoleComDesconto findByConsoleId(Long id);
}
