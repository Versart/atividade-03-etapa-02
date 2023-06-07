package ifma.com.jogos.locadorajogos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ifma.com.jogos.locadorajogos.model.UtilizacaoConsoleCliente;

public interface UtilizacaoConsoleClienteRepository extends JpaRepository<UtilizacaoConsoleCliente, Long> {
    
}
