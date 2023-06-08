package ifma.com.jogos.locadorajogos.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ifma.com.jogos.locadorajogos.domain.model.UtilizacaoConsoleCliente;

public interface UtilizacaoConsoleClienteRepository extends JpaRepository<UtilizacaoConsoleCliente, Long> {
    
}
